package cwitter

import org.apache.commons.codec.digest.DigestUtils

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
        login size: 1..15, blank: false, nullable: false
    }

    def beforeInsert() {
        this.password = DigestUtils.shaHex(this.password)
    }
}
