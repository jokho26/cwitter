package cwitter

class Groupe {

    String nom

    static hasMany = [users:Utilisateur]

    static belongsTo = [owner:Utilisateur]

    static constraints = {
        nom size: 3..25, blank: false
    }
}
