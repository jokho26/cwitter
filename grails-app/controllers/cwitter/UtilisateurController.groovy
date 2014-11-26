package cwitter

class UtilisateurController {

    def index() {
        ["user" : cwitter.Utilisateur.get(params.get("id"))]
    }

    public static List<Utilisateur> getListeUtilisateursASuivre(Utilisateur utilisateurSuiveur) {
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

         redirect( action: "index", params: [id: params.get("idSuiveur")])
    }

    def plusSuivre() {
        Utilisateur suiveur = Utilisateur.get(params.get("idSuiveur"))
        Utilisateur suivi = Utilisateur.get(params.get("idSuivi"))

        suiveur.removeFromUtilisateursSuivis(suivi).save(flush: true, failOnError: true)
        suivi.removeFromUtilisateursSuiveurs(suiveur).save(flush: true, failOnError: true)

        redirect( action: "index", params: [id: params.get("idSuiveur")])
    }

    def inscription() {
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

        // TODO connexion et rediriger vers son mur
        //redirect( action: "index", params: [id: params.get(user.getId())])
    }
}
