package org.restapidoc.sample

import grails.converters.JSON
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.annotation.RestApi
import org.restapidoc.annotation.RestApiResponseObject
import org.restapidoc.pojo.RestApiParamType
import org.restapidoc.pojo.RestApiVerb

@RestApi(name = "book services", description = "Methods for managing books")
class BookController {

    @RestApiMethod(description="Get a book")
        @RestApiParams(params=[
        @RestApiParam(name="id", type="long", paramType = RestApiParamType.PATH, description = "The book id")
    ])
    def show() {
        def book = Book.read(params.id)
        if (!book) {
            response.status = 404
            render "Not found!"
        } else {
            render (book as JSON).toString()
        }

    }

    @RestApiMethod(description="List books written by the author", listing = true)
    @RestApiParams(params=[
        @RestApiParam(name="id", type="long", paramType = RestApiParamType.PATH, description = "The author id")
    ])
    def listByAuthor() {
        def author = Author.read(params.id)
        if(!author) {
            response.status = 404
            render "Not found!"
        } else {
            render (Book.findAllByAuthor(author) as JSON).toString()
        }
    }

    @RestApiMethod(description="Add a book on the store")
    def save() {
        def book = new Book(params)
        if (!book.save(flush: true)) {
            response.status = 404
            render book.errors.toString()
        } else {
            render (book as JSON).toString()
        }
    }

    @RestApiMethod(description="Edit a book")
        @RestApiParams(params=[
        @RestApiParam(name="id", type="long", paramType = RestApiParamType.PATH, description = "The book id")
    ])
    def update() {
        def book = Book.get(params.id)
        if (!book) {
            response.status = 404
            return
        }
        book.properties = params
        if (!book.save(flush: true)) {
            response.status = 400
            render book.errors.toString()
        } else {
            render (book as JSON).toString()
        }
    }

    @RestApiMethod(description="Delete a book")
        @RestApiParams(params=[
        @RestApiParam(name="id", type="long", paramType = RestApiParamType.PATH, description = "The book id")
    ])
    def delete() {
        def book = Book.get(params.id)
        if (!book) {
            response.status = 404
            render "Not found!"
        } else {
            String deletedValue = (book as JSON).toString()
            book.delete(flush: true)
            render deletedValue
        }
    }

}
