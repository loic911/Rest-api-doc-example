/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.grails.plugin.config.DefaultConfigHelper

class PluginConfigGrailsPlugin {

    def version = '0.2.0'
    def grailsVersion = '2.0 > *'
    def dependsOn = ['core': '* > 1.0']

    def loadBefore = ['logging']
    def loadAfter = ['core']

    def pluginExcludes = [
            "grails-app/views/error.gsp",
            'scripts/**/Eclipse.groovy',
            'grails-app/conf/**/MyConfig.groovy',
            'test-plugins/**/*'
    ]

    def author = "Daniel Henrique Alves Lima"
    def authorEmail = "email_daniel_h@yahoo.com.br"
    def title = 'Grails Plugin Config Plugin'
    def description = 'Simplifies plugin configuration tasks'
    def documentation = "http://grails.org/plugin/plugin-config"

    def watchedResources = [
            "file:./grails-app/config/**/*DefaultConfig.groovy"
    ]

    def license = 'APACHE'
    def developers = [[name: 'Joanna Dabal', email: 'joanna@9ci.com'], [name: 'Ondrej Kvasnovsky', email: 'kvasnovsky@gmail.com']]
    def issueManagement = [system: 'GitHub', url: 'https://github.com/ondrej-kvasnovsky/grails-plugin-config-plugin/issues']
    def scm = [url: 'https://github.com/ondrej-kvasnovsky/grails-plugin-config-plugin']

    private DefaultConfigHelper configHelper = new DefaultConfigHelper()

    def doWithWebDescriptor = { xml ->
        enhanceClasses(manager)
    }

    def doWithSpring = {
        enhanceClasses(manager)
    }

    def doWithDynamicMethods = { ctx ->
        enhanceClasses(manager)
    }

    def onChange = { event ->
        configHelper.notifyConfigChange()
    }

    def onConfigChange = { event ->
        configHelper.notifyConfigChange()
    }

    private void enhanceClasses(pluginManager) {
        configHelper.defaultPluginManager = pluginManager
        configHelper.enhanceClasses()
    }
}
