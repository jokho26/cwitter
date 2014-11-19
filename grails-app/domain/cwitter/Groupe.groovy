package cwitter

class Groupe {

    static hasMany = [users:Utilisateur]

    static belongsTo = [owner:Utilisateur]

    static constraints = {
    }
}
