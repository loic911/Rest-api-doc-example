package org.restapidoc.sample

import grails.converters.JSON
import org.restapidoc.annotation.RestApiBodyObject
import org.restapidoc.annotation.RestApiError
import org.restapidoc.annotation.RestApiErrors
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.annotation.RestApiResponseObject
import org.restapidoc.annotation.RestApi
import org.restapidoc.pojo.RestApiParamType
import org.restapidoc.pojo.RestApiVerb


@RestApi(name = "author services", description = "Methods for managing authors")
class AuthorController {

    //This is a demo for more advanced doc description. Look to BookController for a basic and simple doc

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

    @RestApiMethod(description="Get stats data for an author", path="/a_custom_path/{max}.json", verb = RestApiVerb.PUT) //path and verb are stupid...just for the example...
        @RestApiParams(params=[
        @RestApiParam(name="max", type="int", paramType = RestApiParamType.PATH, description = "Max number of author to retrieve")
    ])
    @RestApiBodyObject(name = "nothing")
    @RestApiResponseObject(objectIdentifier = "[stats]")
    @RestApiErrors(apierrors=[
        @RestApiError(code="400",description="A custom error!")
    ])
    def stats() {
        def author = Author.read(params.id)
        render ([fullname:author + " " + author.lastname, numberOfBook: author.book.size()] as JSON).toString()
    }

    @RestApiMethod(description="Get author avatar", extensions = ["jpg","png"])
    @RestApiParams(params=[
        @RestApiParam(name="id", type="long", paramType = RestApiParamType.PATH, description = "The author id"),
        @RestApiParam(name="size", type="long", paramType = RestApiParamType.QUERY, description = "Width size for avatar")
    ])
    def avatar() {

    }

    @RestApiMethod(description="Get a authors with this lastname and firstname")
    @RestApiParams(params=[
    @RestApiParam(name="lastname",  paramType = RestApiParamType.PATH, description = "The lastname criteria"),
    @RestApiParam(name="firstname", type = "String",  paramType = RestApiParamType.PATH, description = "The lastname criteria"),
    @RestApiParam(name="age", paramType = RestApiParamType.PATH, description = "The lastname criteria")
    ])
    def search(String lastname,String firstname, Integer age) {

    }
}
