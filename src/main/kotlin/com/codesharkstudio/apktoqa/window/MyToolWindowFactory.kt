package com.codesharkstudio.apktoqa.window

import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.content.ContentFactory
import com.codesharkstudio.apktoqa.MyBundle
import com.codesharkstudio.apktoqa.services.BuildObserverService
import com.codesharkstudio.apktoqa.services.MyProjectService
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBTextField
import com.intellij.ui.components.OnOffButton
import javax.swing.JButton

class MyToolWindowFactory : ToolWindowFactory {

    init {
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<MyProjectService>()
        private val project = toolWindow.project.service<BuildObserverService>()

        fun getContent() = JBPanel<JBPanel<*>>().apply {

            val savedEmails = service.getSavedEmails()

            val toggleButton = OnOffButton().apply {
                isSelected = false // Set initial state
                addActionListener {
                    val isEnabled = isSelected
                    // Handle the toggle action
                    println("Feature enabled: $isEnabled")
                }
            }

            toggleButton.addActionListener {
                val isOn = toggleButton.isSelected
                val obs = project
              //  if (isOn)      obs.startObserving()
              //  else           obs.stopObserving()
            }


            val inputField = JBTextField().apply {
                emptyText.text = "Enter emails separated by semicolons…"
                columns = 30
                if (savedEmails.isNotEmpty()) {
                    text = savedEmails
                }
            }

            // Submit button
            val submitButton = JButton("Submit").apply {
                addActionListener {
                    val emails = inputField.text
                    val isEnabled = toggleButton.isSelected
                    println("Email String = $emails, Enabled = $isEnabled")
                    service.saveEmails(emails) // Persisting email string
                }
            }

            // Add components to panel
            add(toggleButton)
            add(inputField)
            add(submitButton)
        }
    }
}
