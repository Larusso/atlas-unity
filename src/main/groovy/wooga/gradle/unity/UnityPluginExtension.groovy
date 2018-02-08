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

import org.gradle.api.Action
import org.gradle.process.ExecResult
import wooga.gradle.unity.batchMode.ActivationSpec
import wooga.gradle.unity.batchMode.BaseBatchModeSpec
import wooga.gradle.unity.batchMode.BatchModeSpec
import wooga.gradle.unity.batchMode.BuildTarget

interface UnityPluginExtension extends UnityPluginTestExtension {

    File getUnityPath()
    void setUnityPath(Object path)
    UnityPluginExtension unityPath(Object path)

    File getUnityLicenseDirectory()

    File getProjectPath()
    void setProjectPath(File path)
    UnityPluginExtension projectPath(File path)

    File getReportsDir()
    void setReportsDir(File reportsDir)
    void setReportsDir(Object reportsDir)

    File getPluginsDir()
    void setPluginsDir(File reportsDir)
    void setPluginsDir(Object reportsDir)

    File getAssetsDir()
    void setAssetsDir(File reportsDir)
    void setAssetsDir(Object reportsDir)

    ExecResult batchMode(Closure closure)
    ExecResult batchMode(Action<? super BatchModeSpec> action)

    ExecResult activate(Closure closure)
    ExecResult activate(Action<? super ActivationSpec> action)

    ExecResult returnLicense(Closure closure)
    ExecResult returnLicense(Action<? super BaseBatchModeSpec> action)

    Boolean getAutoReturnLicense()
    void setAutoReturnLicense(Boolean value)
    UnityPluginExtension autoReturnLicense(Boolean value)

    Boolean getAutoActivateUnity()
    void setAutoActivateUnity(Boolean value)
    UnityPluginExtension autoActivateUnity(Boolean value)

    UnityAuthentication getAuthentication()
    void setAuthentication(UnityAuthentication authentication)

    UnityPluginExtension authentication(Closure closure)
    UnityPluginExtension authentication(Action<? super UnityAuthentication> action)

    BuildTarget getDefaultBuildTarget()
    void setDefaultBuildTarget(BuildTarget value)
    void setDefaultBuildTarget(Object value)
    UnityPluginExtension defaultBuildTarget(BuildTarget value)

    Boolean getRedirectStdOut()
    void setRedirectStdOut(Boolean redirect)
    UnityPluginExtension redirectStdOut(Boolean redirect)

    void setLogCategory(String value)
    String getLogCategory()
    UnityPluginExtension logCategory(String value)
}

