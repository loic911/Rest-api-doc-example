class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }


        //Only define rules for books, rules for author used Grails auto urlmapping builder
        "/api/book.$format"(controller:"book"){
            action = [POST:"save"]
        }
        "/api/book/$id.$format"(controller:"book"){
            action = [GET:"show",PUT:"update", DELETE:"delete"]
        }
        "/api/author/$id/book.$format"(controller:"book"){
            action = [GET:"listByAuthor"]
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
