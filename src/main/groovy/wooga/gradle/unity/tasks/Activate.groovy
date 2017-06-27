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

package wooga.gradle.unity.tasks

import org.gradle.api.Action
import org.gradle.api.internal.ConventionTask
import org.gradle.api.specs.Spec
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.internal.Factory
import org.gradle.process.ExecResult
import wooga.gradle.unity.UnityAuthentication
import wooga.gradle.unity.UnityPluginExtension
import wooga.gradle.unity.batchMode.ActivationAction
import wooga.gradle.unity.batchMode.ActivationSpec

class Activate extends ConventionTask implements ActivationSpec {

    ActivationAction activationAction
    private ExecResult batchModeResult

    protected Factory<ActivationAction> getActivationActionFactory() {
        return project.extensions.getByType(UnityPluginExtension).activationActionFactory
    }

    Activate() {
        this.activationAction = getActivationActionFactory().create()
        onlyIf(new Spec<Activate>() {
            @Override
            boolean isSatisfiedBy(Activate task) {
                return ((task.userName && !task.userName.isEmpty())
                        && (task.password && !task.password.isEmpty())
                        && (task.serial && !task.serial.isEmpty()))
            }
        })
    }

    @TaskAction
    protected void activate() {
        batchModeResult = activationAction.activate()
    }

    @Input
    String getUserName() {
        return authentication.username
    }

    @Input
    String getPassword() {
        return authentication.password
    }

    @Optional
    @Input
    String getSerial() {
        return authentication.serial
    }

    @Override
    UnityAuthentication getAuthentication() {
        return activationAction.authentication
    }

    @Override
    void setAuthentication(UnityAuthentication authentication) {
        activationAction.authentication = authentication
    }

    @Override
    Activate authentication(Closure closure) {
        activationAction.authentication(closure)
        return this
    }

    @Override
    Activate authentication(Action<? super UnityAuthentication> action) {
        activationAction.authentication(action)
        return this
    }

    @Override
    File getUnityPath() {
        return activationAction.getUnityPath()
    }

    @Override
    Activate unityPath(File path) {
        activationAction.unityPath(path)
        return this
    }

    @Override
    void setUnityPath(File path) {
        activationAction.setUnityPath(path)
    }

    @Override
    File getProjectPath() {
        return activationAction.getProjectPath()
    }

    @Override
    Activate projectPath(File path) {
        activationAction.projectPath(path)
        return this
    }

    @Override
    void setProjectPath(File path) {
        activationAction.setProjectPath(path)
    }

    @Override
    File getLogFile() {
        return activationAction.getLogFile()
    }

    @Override
    Activate logFile(Object file) {
        return activationAction.logFile(file)
    }

    @Override
    void setLogFile(Object file) {
        activationAction.setLogFile(file)
    }
}