package org.restapidoc.sample

import grails.converters.JSON
import groovy.util.logging.Log



/**
 * Created by lrollus on 4/3/14.
 */
class RestApiDocTest extends GroovyTestCase {

    static String PATH_TO_JSON_FILE = "restapidoc.json"

    def json

    protected void setUp() throws java.lang.Exception {
        File file = new File(PATH_TO_JSON_FILE)
        assertTrue(file.exists())
        json = JSON.parse(file.text)
    }


    public void testHeaders() {
        assertEquals("http://localhost:8080/RestApiDoc-example",json.basePath)
        assertEquals("0.1",json.version)
    }

    public void testObjects() {
        assertEquals(3,json.objects.size())

        def author = json.objects.find{it.name.equals("author")}
        assertNotNull(author)
        assertEquals("An author write books",author.description)

        def book = json.objects.find{it.name.equals("book")}
        assertNotNull(book)
        assertEquals("A book available on the store",book.description)

        def stats = json.objects.find{it.name.equals("[stats]")}
        assertNotNull(stats)
    }

    public void testFields() {
        def author = json.objects.find{it.name.equals("author")}
        assertNotNull(author)
        checkField(author.fields,"firstname",["description":"The firstname of the author","presentInResponse":true,type:"String", "useForCreation":true])
        checkField(author.fields,"lastname",["description":"The lastname of the author","presentInResponse":true,type:"String"])
        checkField(author.fields,"fullname",["description":"The first and lastname","presentInResponse":true,type:"String","useForCreation":false])
        checkField(author.fields,"books",["description":"Books id written by this author","presentInResponse":true,type:"List","useForCreation":false])
        checkField(author.fields,"birthday",["presentInResponse":false, "type":"Date","defaultValue":"01/01/1970"])
        //default field (config)
        checkField(author.fields,"id",["type":"Long"])


        def book = json.objects.find{it.name.equals("book")}
        assertNotNull(book)
        checkField(book.fields,"author",["description":"The author of the book","presentInResponse":true,type:"Author"])
        checkField(book.fields,"id",["type":"Long"])



        def stats = json.objects.find{it.name.equals("[stats]")}
        assertNotNull(stats)
        checkField(stats.fields,"fullname",["description":"The author fullname","useForCreation":false])
        assertNull("Field id must be by default ONLY on grails domain, not on custom response",stats.fields.find{it.name.equals("id")})
    }

    public void checkField(def fields, String fieldName, def propertyToCheck) {
        def field = fields.find{it.name.equals(fieldName)}
        assertNotNull("$fieldName cannot be null",field)

        propertyToCheck.each {
            assertEquals("check field ${fieldName} with property ${it.key}",field[it.key],it.value)
        }
    }



//    public void testApis() {
//
//    }
//

//
//





}
