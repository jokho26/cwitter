package cwitter

class Utilisateur {

    String nom
    String prenom
    String login
    String password

    static hasMany = [messages: Message, groupes:Groupe, utilisateursSuivis: Utilisateur, utilisateursSuiveurs: Utilisateur]

    static mappedBy = [groupes:"owner"]

    static constraints = {

        nom blank: false, nullable: false
        prenom blank: false, nullable: false
        login size: 5..15, blank: false, nullable: false
        password size: 5..15, blank: false, nullable: false
    }
}
