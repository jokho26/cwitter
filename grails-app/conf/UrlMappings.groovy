class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "500"(view:'/error')

        "/"(view:"/utilisateur/index")
        "/home_user/$id" (controller: 'utilisateur', action: 'index')
        "/inscription" (view:"/utilisateur/inscription")
        "/mur" (view:"/utilisateur/mur")
        "/utilisateur/listeUser" (view:"/utilisateur/listeUser")
        "/utilisateur/modifierUtilisateur" (controller: 'utilisateur', action: 'modifierUtilisateur')

	}
}
