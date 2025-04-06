package com.codesharkstudio.apktoqa.listener

import com.codesharkstudio.apktoqa.services.BuildObserverService
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskId
import com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskNotificationListener
import com.intellij.openapi.components.service
import org.jetbrains.plugins.gradle.util.GradleConstants


class MyExternalSystemTaskListener : ExternalSystemTaskNotificationListener {
    override fun onStart(projectPath: String, id: ExternalSystemTaskId) {
        if (id.projectSystemId == GradleConstants.SYSTEM_ID) {
            // Lookup your service and delegate
            val project = id.findProject()  // see note below
            project?.service<BuildObserverService>()?.onGradleBuildStarted()
        }
    }
    // other methods can be no‑ops
    override fun onSuccess(id: ExternalSystemTaskId) {

    }
    override fun onFailure(id: ExternalSystemTaskId, e: Exception) {

    }
    override fun onStatusChange(event: com.intellij.openapi.externalSystem.model.task.ExternalSystemTaskNotificationEvent) {

    }
    override fun onTaskOutput(id: ExternalSystemTaskId, text: String, stdOut: Boolean) {

    }
}
