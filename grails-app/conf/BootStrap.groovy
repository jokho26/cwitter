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


        admin.addToUtilisateursSuiveurs(u1)
        u1.addToUtilisateursSuivis(admin)

        admin.addToUtilisateursSuiveurs(u2)
        u2.addToUtilisateursSuivis(admin)

        admin.addToUtilisateursSuivis(u3)
        u3.addToUtilisateursSuiveurs(admin)

        u1.addToUtilisateursSuiveurs(u2)
        u2.addToUtilisateursSuivis(u1)

        u2.addToUtilisateursSuiveurs(u1)
        u1.addToUtilisateursSuivis(u2)

        u4.addToUtilisateursSuiveurs(u3)
        u3.addToUtilisateursSuivis(u4)

        u3.addToUtilisateursSuiveurs(u2)
        u2.addToUtilisateursSuivis(u3)

    }
    def destroy = {
    }
}
