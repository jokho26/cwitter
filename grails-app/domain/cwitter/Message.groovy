package cwitter

class Message {

    String text

    static belongsTo = [user: Utilisateur]

    static constraints = {
        text size: 0..140, blank: false
    }
}
