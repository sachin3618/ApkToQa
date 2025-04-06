package com.codesharkstudio.apktoqa.services

import com.intellij.openapi.components.Service

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.vfs.newvfs.BulkFileListener
import com.intellij.openapi.vfs.newvfs.events.VFileEvent

@Service(Service.Level.PROJECT)
class BuildObserverService(private val project: Project) {

    private val fileConnection   = project.messageBus.connect()

    /** Call this when your toggle goes from OFF→ON */
//    fun startObserving() {
//        // 1) Listen for any External System (e.g. Gradle) task starts
//        gradleConnection.subscribe(
//            ExternalSystemTaskNotificationListener.NULL_OBJECT,
//            object : ExternalSystemTaskNotificationListener {
//                override fun onStart(id: ExternalSystemTaskId, workingDir: String?) {
//                    if (id.projectSystemId == ExternalSystemUtil.GRADLE_SYSTEM_ID) {
//                        onGradleBuildStarted()
//                    }
//                }
//                // Override other methods (onSuccess, onFailure) if needed
//            }
//        )
//    }

    fun onGradleBuildStarted() {
        // 2) When Gradle build kicks off, start watching for new APK files
        fileConnection.subscribe(
            VirtualFileManager.VFS_CHANGES,
            object : BulkFileListener {
                override fun after(events: List<VFileEvent>) {
                    for (evt in events) {
                        val file = evt.file ?: continue
                        // Adjust the path test to your project's build output
                        if (file.extension == "apk" &&
                            file.parent.path.contains("build/outputs/apk")
                        ) {
                            // You’ve got a new APK!
                            // e.g. notify user, open it, etc.
                            println("New APK built: ${file.path}")
                        }
                    }
                }
            }
        )
    }

    /** If you want to stop observing (e.g. toggle OFF), you can disconnect: */
    fun stopObserving() {
      //  gradleConnection.disconnect()
        fileConnection.disconnect()
    }
}