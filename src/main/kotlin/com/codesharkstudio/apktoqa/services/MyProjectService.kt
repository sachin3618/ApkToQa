package com.codesharkstudio.apktoqa.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.codesharkstudio.apktoqa.MyBundle
import com.intellij.ide.util.PropertiesComponent

@Service(Service.Level.PROJECT)
class MyProjectService(project: Project) {

    private val propertiesComponent = PropertiesComponent.getInstance(project)


    init {
        thisLogger().info(MyBundle.message("projectService", project.name))
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    fun getRandomNumber() = (1..100).random()

    fun saveEmails(emails: String) {
        propertiesComponent.setValue("saved_emails", emails)
    }

    fun getSavedEmails(): String {
        return propertiesComponent.getValue("saved_emails", "")
    }
}
