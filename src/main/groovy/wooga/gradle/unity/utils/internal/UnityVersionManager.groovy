/*
 * Copyright 2018-2020 Wooga GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wooga.gradle.unity.utils.internal

import org.apache.maven.artifact.versioning.ArtifactVersion
import org.apache.maven.artifact.versioning.DefaultArtifactVersion

class UnityVersionManager {
    static ArtifactVersion retrieveUnityVersion(File pathToUnity, String defaultVersion) {
        def versionString = net.wooga.uvm.UnityVersionManager.readUnityVersion(pathToUnity)
        if(!versionString ) {
            versionString = defaultVersion
        }

        new DefaultArtifactVersion(versionString.split(/f|p|b|a/).first().toString())
    }
}
