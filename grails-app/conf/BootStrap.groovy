import grails.converters.JSON
import org.restapidoc.sample.Author
import org.restapidoc.sample.Book

class BootStrap {

    def grailsApplication

    def init = { servletContext ->

        Author author1 = new Author(firstname: "Burt", lastname: "Beckwith")
        author1.save(failOnError: true)
        Author author2 = new Author(firstname: "Martin", lastname: "Fowler")
        author2.save(failOnError: true)

        Book book1 = new Book(title:"Programming Grails", year:2013,category: "IT",author:author1)
        book1.save(failOnError: true)
        author1.addToBook(book1)

        Book book2 = new Book(title:"Refactoring: Improving the Design of Existing Code", year:1999,category: "IT",author:author1)
        book2.save(failOnError: true)
        author2.addToBook(book2)

        Book book3 = new Book(title:" Patterns of Enterprise Application Architecture", year:2002,category: "IT",author:author1)
        book3.save(failOnError: true, flush: true)
        author2.addToBook(book3)


        grailsApplication.getDomainClasses().each { domain ->
            domain.metaClass.methods.each { method ->
                if (method.name.equals("getDataFromDomain")) {
                    def domainFullName = domain.packageName + "." + domain.name
                    log.info "Init Marshaller for domain class : " + domainFullName
                    def domainInstance = grailsApplication.getDomainClass(domainFullName).newInstance()
                    log.info("Register custom JSON renderer for " + this.class)
                    JSON.registerObjectMarshaller(domain.clazz) { it ->
                        return domainInstance.getDataFromDomain(it)
                    }
                }
            }
        }

    }
    def destroy = {
    }
}
