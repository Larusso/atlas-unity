/*
 * Copyright 2018 Wooga GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package wooga.gradle.unity

import org.apache.commons.lang.StringEscapeUtils
import spock.lang.Ignore

@Ignore
class UnityIntegrationRealSpec extends IntegrationSpec {

    def escapedPath(String path) {
        String osName = System.getProperty("os.name").toLowerCase()
        if (osName.contains("windows")) {
            return StringEscapeUtils.escapeJava(path)
        }
        path
    }

    def "runs batchmode action"() {
        given: "path to future project"
        def project_path = new File( projectDir,"build/test")

        and: "a build script"
        buildFile << """
            group = 'test'
            ${applyPlugin(UnityPlugin)}
         
            task mUnity {
                doLast {
                    unity.batchMode {
                        args "-createProject", "test"
                    }
                }
            }
        """.stripIndent()

        when:
        def result = runTasksSuccessfully("mUnity")

        then:
        result.standardOutput.contains("Unity.exe")
        fileExists("build/test")
    }

    def "runs batchmode task"() {
        given: "path to future project"
        def project_path = new File( projectDir,"build/test")

        and: "a build script"
        buildFile << """
            group = 'test'
            ${applyPlugin(UnityPlugin)}
         
            task (mUnity, type: wooga.gradle.unity.tasks.Unity) {
                args "-createProject", "${escapedPath(project_path.path)}"
            }
        """.stripIndent()

        when:
        def result = runTasksSuccessfully("mUnity")

        then:
        result.standardOutput.contains("Unity.exe")
        fileExists("build/test")
    }
}
