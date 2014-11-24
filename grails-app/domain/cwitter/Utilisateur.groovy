package cwitter

class Utilisateur {

    String nom
    String prenom
    String login
    String password

    // Pour trier les messages
    List messages

    static hasMany = [messages: Message, utilisateursSuivis: Utilisateur, utilisateursSuiveurs: Utilisateur]

    static constraints = {

        nom blank: false, nullable: false
        prenom blank: false, nullable: false
        login size: 5..15, blank: false, nullable: false
        password size: 5..15, blank: false, nullable: false
    }
}
