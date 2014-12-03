package cwitter

class Message {

    String text

    Date date

    static belongsTo = [user: Utilisateur]

    static constraints = {
        text size: 0..140, blank: false
    }

    def beforeInsert() {
        this.date = new Date();
    }
}
