import cwitter.Groupe
import cwitter.Message
import cwitter.Utilisateur

class BootStrap {

    def init = { servletContext ->


        Utilisateur admin = new Utilisateur(login: "admin", password: "admin", nom: "Jean", prenom: "Bon").save(flush: true, failOnError: true)
        Utilisateur u1 = new Utilisateur(login: "user1", password: "user1", nom: "Anne", prenom: "Orak").save(flush: true, failOnError: true)
        Utilisateur u2 = new Utilisateur(login: "user2", password: "user2", nom: "Ozzy", prenom: "Osbourne").save(flush: true, failOnError: true)
        Utilisateur u3 = new Utilisateur(login: "user3", password: "user3", nom: "Iggy", prenom: "Pop").save(flush: true, failOnError: true)
        Utilisateur u4 = new Utilisateur(login: "user4", password: "user4", nom: "Young", prenom: "Angus").save(flush: true, failOnError: true)

        for (Utilisateur u : Utilisateur.all) {
            for (int i = 0; i < 5; i++) {
                new Message(text: "Message " + i, user: u).save()
            }
        }

        Groupe gAdmin = new Groupe(nom: "Groupe administrateur")
        admin.addToGroupes(gAdmin).save(flush: true, failOnError: true)
        gAdmin.addToUsers(admin)

        Groupe gUser = new Groupe(nom: "Groupe utilisateur 1", owner:admin).save()
        gUser.addToUsers(u1)
        gUser.addToUsers(u2)
        gUser.addToUsers(admin)

        Groupe gUser2 = new Groupe(nom: "Groupe utilisateur 2", owner:u3).save()
        gUser2.addToUsers(u3)
        gUser2.addToUsers(u4)
        gUser.addToUsers(admin)
    }
    def destroy = {
    }
}
