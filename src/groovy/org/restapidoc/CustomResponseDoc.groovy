package org.restapidoc

import org.restapidoc.annotation.RestApiObjectField
import org.restapidoc.annotation.RestApiObjectFields

/**
 * Created by lrollus on 3/28/14.
 */
class CustomResponseDoc {

    @RestApiObjectField(description = "An author stats")
    @RestApiObjectFields(params=[
        @RestApiObjectField(apiFieldName = "fullname", description = "The author fullname",allowedType = "String",useForCreation = false),
        @RestApiObjectField(apiFieldName = "numberOfBook", description = "The number of books created by this author",allowedType = "Integer",useForCreation = false)
    ])
    static def stats

}
