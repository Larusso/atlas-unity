/*
 * Copyright 2017 the original author or authors.
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
 *
 */

package wooga.gradle.unity

import nebula.test.PluginProjectSpec
import nebula.test.ProjectSpec
import org.gradle.api.DefaultTask
import spock.lang.Unroll
import wooga.gradle.unity.tasks.Test
import wooga.gradle.unity.tasks.UnityPackage

class UnityPluginActivationSpec extends PluginProjectSpec {
    @Override
    String getPluginName() { return 'net.wooga.unity' }
}

class UnityPluginSpec extends ProjectSpec {
    public static final String PLUGIN_NAME = 'net.wooga.unity'

    def 'Creates the [unity] extension'() {
        given:
        assert !project.plugins.hasPlugin(PLUGIN_NAME)
        assert !project.extensions.findByName(UnityPlugin.EXTENSION_NAME)

        when:
        project.plugins.apply(PLUGIN_NAME)

        then:
        def extension = project.extensions.findByName(UnityPlugin.EXTENSION_NAME)
        extension instanceof DefaultUnityPluginExtension
    }

    @Unroll("creates the task #taskName")
    def 'Creates needed tasks'(String taskName, Class taskType) {
        given:
        assert !project.plugins.hasPlugin(PLUGIN_NAME)
        assert !project.tasks.findByName(taskName)

        when:
        project.plugins.apply(PLUGIN_NAME)

        then:
        def task = project.tasks.findByName(taskName)
        taskType.isInstance(task)

        where:
        taskName                                 | taskType
        UnityPlugin.TEST_TASK_NAME               | Test
        UnityPlugin.EXPORT_PACKAGE_TASK_NAME     | UnityPackage
        UnityPlugin.ASSEMBLE_RESOURCES_TASK_NAME | DefaultTask
        UnityPlugin.SETUP_TASK_NAME              | DefaultTask
    }

    @Unroll
    def 'adds pluginToAdd #pluginToAdd'(String pluginToAdd) {
        given:
        assert !project.plugins.hasPlugin(PLUGIN_NAME)
        assert !project.plugins.hasPlugin(pluginToAdd)

        when:
        project.plugins.apply(PLUGIN_NAME)

        then:
        project.plugins.hasPlugin(pluginToAdd)

        where:
        pluginToAdd << ['base', 'reporting-base']
    }
}
