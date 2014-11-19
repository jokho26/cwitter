package cwitter

class UtilisateurController {

    def index() {
        ["user" : cwitter.Utilisateur.get(params.get("id"))]
    }
}
