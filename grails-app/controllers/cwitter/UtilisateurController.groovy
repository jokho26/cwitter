package cwitter

import org.apache.commons.codec.digest.DigestUtils

class UtilisateurController {

    def index() {
        ["user" : cwitter.Utilisateur.get(params.get("id"))]
    }

    def mur() {
        ["user" : cwitter.Utilisateur.get(params.get("id"))]
    }

    def monMur() {
        ["user" : cwitter.Utilisateur.get(session["utilisateur"])]
    }

    public static List<Utilisateur> getListeUtilisateursASuivre(long idUtilisateurSuiveur) {
        Utilisateur utilisateurSuiveur = Utilisateur.get(idUtilisateurSuiveur);
        List<Utilisateur> listeUtilisateurASuivre = Utilisateur.getAll().clone();
        listeUtilisateurASuivre.removeAll(utilisateurSuiveur.getUtilisateursSuivis());

        // On retire l'utilisateir pour ne pas qu'il se suive lui même
        listeUtilisateurASuivre.remove(utilisateurSuiveur)
        return listeUtilisateurASuivre
    }

    def suivre() {
        Utilisateur suiveur = Utilisateur.get(params.get("idSuiveur"))
        Utilisateur suivi = Utilisateur.get(params.get("idSuivi"))

        suiveur.addToUtilisateursSuivis(suivi).save(flush: true, failOnError: true)
        suivi.addToUtilisateursSuiveurs(suiveur).save(flush: true, failOnError: true)

         redirect( action: "mur", params: [id: params.get("idSuiveur")])
    }

    def plusSuivre() {
        Utilisateur suiveur = Utilisateur.get(params.get("idSuiveur"))
        Utilisateur suivi = Utilisateur.get(params.get("idSuivi"))

        suiveur.removeFromUtilisateursSuivis(suivi).save(flush: true, failOnError: true)
        suivi.removeFromUtilisateursSuiveurs(suiveur).save(flush: true, failOnError: true)

        redirect( action: "mur", params: [id: params.get("idSuiveur")])
    }

    def inscription() {
        String actionForm = params.get("actionForm");

        if (actionForm != null && actionForm.equals("creerUtilisateur")) {

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


    def connection() {

        String mdp = params.get("mdp");
        String login = params.get("login");

        if(mdp == null || mdp.equals("") || login == null || login.equals("")){
            redirect(uri: "/", params: [messageErreur: "Champs vide."]);
            return;
        }

        Utilisateur user = Utilisateur.findByLogin(login)

        if(user == null){
            redirect(uri: "/", params: [messageErreur: "Utilisateur inconnu."]);
            return;
        }

        mdp = DigestUtils.shaHex(mdp);

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

    def deconnection() {
        session["utilisateur"] = null;
        session["estConnecte"] = false;
        redirect(uri: "/", params: [messageErreur: "Deconnexion réussie."]);
    }
}
