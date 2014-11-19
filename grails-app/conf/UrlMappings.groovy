class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //"/"(view:"/index")
        "500"(view:'/error')

        "/"(view:"/utilisateur/listeUser")
        "/home_user/$id" (controller: 'utilisateur', action: 'index')
	}
}
