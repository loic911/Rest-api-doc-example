package org.restapidoc.sample

import grails.converters.JSON
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.annotation.RestApiResponseObject
import org.restapidoc.annotation.RestApi
import org.restapidoc.pojo.RestApiParamType


@RestApi(name = "author services", description = "Methods for managing authors")
class AuthorController {

    //If AuthorController was called FooController, you must implement this method to return the domain name (Author)
    //    def currentDomainName() {

//        return "Author"
//    }

    @RestApiMethod(description="Get an author")
    @RestApiParams(params=[
        @RestApiParam(name="id", type="long", paramType = RestApiParamType.PATH, description = "The author id")
    ])
    //add a custom exception
    def show() {
        def author = Author.read(params.id)
        if (!author) {
			response.status = 404
            render "Not Found!"
        } else {
            render (author as JSON).toString()
        }
    }

    @RestApiMethod(description="List all authors", listing = true)
    @RestApiParams(params=[
        @RestApiParam(name="max", type="int", paramType = RestApiParamType.PATH, description = "Max number of author to retrieve")
    ])
    def list() {
        render (Author.list([max:params.max]) as JSON).toString()
    }

    @RestApiMethod(description="Get stats data for an author")
        @RestApiParams(params=[
        @RestApiParam(name="max", type="int", paramType = RestApiParamType.PATH, description = "Max number of author to retrieve")
    ])
    @RestApiResponseObject(objectIdentifier = "[stats]")
    def stats() {
        def author = Author.read(params.id)
        render ([fullname:author + " " + author.lastname, numberOfBook: author.book.size()] as JSON).toString()
    }
}
