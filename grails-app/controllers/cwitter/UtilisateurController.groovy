package cwitter

import org.apache.commons.codec.digest.DigestUtils

/**
 * Controleur principal de l'application
 */
class UtilisateurController {

    /**
     * Redirection vers le mur d'un utilisateur
     * @return
     */
    def mur() {
        ["user" : cwitter.Utilisateur.get(params.get("id"))]
    }

    /**
     * Redirection vers le fil d'actualité : tous les messages des utilisateurs que le compte
     * connecté suit.
     * @return
     */
    def actualite() {
        Utilisateur user = cwitter.Utilisateur.get(session["utilisateur"]);

        //Création de la liste des messages des personnes que l'utilisateur connecté suit
        ArrayList<Message> listeMessagesActu = new ArrayList<Message>() {};
        for (Utilisateur userSuivi : user.getUtilisateursSuivis()) {
            listeMessagesActu.addAll(userSuivi.getMessages());
        }
        listeMessagesActu.addAll(user.getMessages());

        // Tris par date
        listeMessagesActu.sort(new MessageDateComparator());

        request.setAttribute("messages", listeMessagesActu)
    }

    /**
     * Méthode lors de la modification des informations d'un utilisateur
     * @return
     */
    def modifierUtilisateur() {
        Utilisateur user = cwitter.Utilisateur.get(session["utilisateur"]);

        // Récupération des paramètres du formulaire
        String nom = params.get("nom");
        String prenom = params.get("prenom");
        String mdp = params.get("mdp");

        boolean isModifie = false;

        // Si le nom est changé
        if (nom != null && !nom.equals("")) {
            if (!user.getNom().equals(nom)) {
                user.setNom(nom)
                isModifie = true;
            }
        }
        // Si le prénom est changé
        if (prenom != null && !prenom.equals("")) {
            if (!user.getPrenom().equals(prenom)) {
                user.setPrenom(prenom)
                isModifie = true;
            }
        }
        // Si le mot de passe est changé
        if (mdp != null && !mdp.equals("")) {
            mdp = DigestUtils.shaHex(mdp);
            if (!user.getPassword().equals(mdp)) {
                user.setPassword(mdp)
                isModifie = true;
            }

        }

        // Si il y a une modification sur le compte, on met à jour l'utilisateur et on le redirige vers son mur
        if (isModifie) {
            user.save(flush: true, failOnError: true)
            redirect(action: "monMur", controller: "utilisateur", params: [id: user.getId()]);
        }
        // Sinon, redirection vers la page de modification du compte
        else {
            ["userAModifier" : user]
        }
    }

    /**
     * Redirection vers le mur de l'utilisateur
     * @return
     */
    def monMur() {
        ["user" : cwitter.Utilisateur.get(session["utilisateur"])]
    }

    /**
     * Méthode permettant de récuperer la liste des autres utilisateurs que l'on peut suivre
     * @param idUtilisateurSuiveur
     * @return
     */
    public static List<Utilisateur> getListeUtilisateursASuivre(long idUtilisateurSuiveur) {
        Utilisateur utilisateurSuiveur = Utilisateur.get(idUtilisateurSuiveur);

        // On récupere tous les utilisateurs
        List<Utilisateur> listeUtilisateurASuivre = Utilisateur.getAll().clone();

        // On enleve les utilisateurs qu'on suit déjà
        listeUtilisateurASuivre.removeAll(utilisateurSuiveur.getUtilisateursSuivis());

        // On retire l'utilisateur connecté pour ne pas qu'il se suive lui même
        listeUtilisateurASuivre.remove(utilisateurSuiveur)
        return listeUtilisateurASuivre
    }

    /**
     * Méthode invoquée lorsque l'utilisateur veut suivre une nouvelle personne
     * @return
     */
    def suivre() {
        Utilisateur suiveur = Utilisateur.get(params.get("idSuiveur"))
        Utilisateur suivi = Utilisateur.get(params.get("idSuivi"))

        // Ajout des suiveurs/suivis dans les listes
        suiveur.addToUtilisateursSuivis(suivi).save(flush: true, failOnError: true)
        suivi.addToUtilisateursSuiveurs(suiveur).save(flush: true, failOnError: true)

        // Redirection vers le mur de l'utilisateur
        redirect( action: "monMur")
    }

    /**
     * Méthode invoquée lorsque l'utilisateur ne veut plus suivre une personne
     * @return
     */
    def plusSuivre() {
        Utilisateur suiveur = Utilisateur.get(params.get("idSuiveur"))
        Utilisateur suivi = Utilisateur.get(params.get("idSuivi"))

        // Suppression des suiveurs/suivis dans les listes
        suiveur.removeFromUtilisateursSuivis(suivi).save(flush: true, failOnError: true)
        suivi.removeFromUtilisateursSuiveurs(suiveur).save(flush: true, failOnError: true)

        // Redirection vers le mur de l'utilisateur
        redirect( action: "monMur")
    }

    /**
     * Méthode invoquée lorsque l'utilisateur veut supprimer un message
     * @return
     */
    def supprimerMessage() {
        Utilisateur utilisateur = Utilisateur.get(params.get("idUtilisateur"))

        // Suppression du message
        utilisateur.removeFromMessages(Message.get(params.get("idMessage"))).save(flush: true, failOnError: true)

        // Redirection vers le mur de l'utilisateur
        redirect( action: "monMur")
    }

    /**
     * Méthode invoquée lors de l'inscription d'un utilisateur
     * @return
     */
    def inscription() {
        String actionForm = params.get("actionForm");
        if (actionForm != null && actionForm.equals("creerUtilisateur")) {

            // Récupération des informations du formulaire
            String nom = params.get("nom");
            String prenom = params.get("prenom");
            String mdp = params.get("mdp");
            String login = params.get("login");

            // Verification que les champs sont bien remplis, sinon redirection
            if (nom == null || nom.equals("") ||
                    prenom == null || prenom.equals("") ||
                    mdp == null || mdp.equals("") ||
                    login == null || login.equals("")) {
                redirect(uri: "/inscription", params: [messageErreur: "Champs vide. Veuillez remplir tout le formulaire"]);
                return;
            }

            // Vérification si le login est déjà utilisé
            if (Utilisateur.findByLogin(login) != null) {
                redirect(uri: "/inscription", params: [messageErreur: "Login déjà utilisé. Veuillez en choisir un nouveau."]);
                return;
            }

            // On créer l'utilisateur
            Utilisateur user = new Utilisateur(login: login, password: mdp, nom: nom, prenom: prenom).save(flush: true, failOnError: true)

            // Redirection vers la connexion
            redirect( action: "connection", params: [login:login, mdp:mdp])
        }
    }

    /**
     * Méthode invoquée lors de la connexion d'un utilisateur
     * @return
     */
    def connection() {

        String mdp = params.get("mdp");
        String login = params.get("login");

        // Vérification des champs du formulaire
        if(mdp == null || mdp.equals("") || login == null || login.equals("")){
            redirect(uri: "/", params: [messageErreur: "Champs vide."]);
            return;
        }

        // On récupere l'utilisateur lié au login
        Utilisateur user = Utilisateur.findByLogin(login)

        // Si il n'existe pas, redirection
        if(user == null){
            redirect(uri: "/", params: [messageErreur: "Utilisateur inconnu."]);
            return;
        }

        // Hashage du MDP
        mdp = DigestUtils.shaHex(mdp);

        // Si le mot de passe est correct, on connecte l'utilisateur
        if(mdp.equals(user.getPassword())){
            session["utilisateur"] = user.getId();
            session["estConnecte"] = true;

            redirect(action: "monMur", controller: "utilisateur", params: [id: user.getId()]);
        }
        else {
            redirect(uri: "/", params: [messageErreur: "Mot de passe incorrect."]);
            return;
        }
    }

    /**
     * Méthode invoquée lors de la déconnexion de l'utilisateur
     * @return
     */
    def deconnection() {
        session["utilisateur"] = null;
        session["estConnecte"] = false;
        redirect(uri: "/", params: [messageErreur: "Deconnexion réussie."]);
    }

    /**
     * Méthode invoquée lors de l'ajout d'un nouveau message
     * @return
     */
    def nouveauMessage() {
        String message = params.get("message");

        if (message != null && !message.equals("")) {
            new Message(text: message, user: Utilisateur.get(session["utilisateur"]), date: new Date()).save(flush: true, failOnError: true)
        }

        redirect(action: "monMur", controller: "utilisateur");
    }
}
