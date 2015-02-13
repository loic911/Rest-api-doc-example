restapidoc-example
==================

The sample app for RestApiDoc plugin (https://github.com/loic911/restapidoc)

If you want to use with a local RestApiDoc plugin (github clone), edit your BuildConfig.

Add:

grails.plugin.location.restapidoc = "../restapidoc" (where ../restapidoc is the path of the plugin sources)

And remove:

compile ":rest-api-doc:0.5"
