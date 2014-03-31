package org.restapidoc.sample

import org.restapidoc.annotation.RestApiObjectField
import org.restapidoc.annotation.RestApiObject

@RestApiObject(name = "book", description = "A book available on the store")
class Book implements Serializable{

    @RestApiObjectField(description = "The title of the book")
    String title

    @RestApiObjectField(description = "Year published for this book")
    Integer year

    @RestApiObjectField(description = "The book category")
    String category

    @RestApiObjectField(description = "The author of the book")
    Author author

    static constraints = {
        title blank:false
    }
}
