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
}
