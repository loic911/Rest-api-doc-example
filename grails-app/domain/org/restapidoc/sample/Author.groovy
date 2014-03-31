package org.restapidoc.sample

import org.restapidoc.annotation.RestApiObjectField
import org.restapidoc.annotation.RestApiObjectFields
import org.restapidoc.annotation.RestApiObject

@RestApiObject(name = "author", description = "An author write books")
class Author implements Serializable{

    @RestApiObjectField(description = "The firstname of the author")
    String firstname

    @RestApiObjectField(description = "The lastname of the author")
    String lastname

    //Shhh keep it secret! Let say that if not set, author is born in 01/01/1970...
    @RestApiObjectField(description = "The birthday date of the author",presentInResponse = false, mandatory = false, defaultValue = "01/01/1970")
    Date birthday

    @RestApiObjectField(apiFieldName= "books", description = "Books id written by this author",allowedType =  "List",useForCreation = false)
    static hasMany = [book:Book]

    @RestApiObjectFields(params=[
        @RestApiObjectField(apiFieldName= "fullname", description = "The first and lastname",allowedType =  "String",useForCreation = false)
    ])
    static transients = []

    static constraints = {
        lastname (blank:false)
        firstname (blank:false)
        birthday (nullable: true)
    }

    //method call for JSON
    static def getDataFromDomain(def domain) {
        def returnArray = [:]
        returnArray['firstname'] = domain?.firstname
        returnArray['lastname'] = domain?.lastname
        returnArray['fullname'] = domain?.firstname + " " + domain?.lastname
        returnArray['books'] = domain?.book?.collect{it.id}
        //returnArray.putAll(Author.declaredFields.properties)

        return returnArray
    }
}
