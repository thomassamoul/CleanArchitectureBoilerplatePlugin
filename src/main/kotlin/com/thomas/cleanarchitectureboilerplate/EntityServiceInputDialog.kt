package com.thomas.cleanarchitectureboilerplate

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.JBUI.insets
import java.awt.Dimension
import java.awt.Font
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.font.TextAttribute
import java.io.InputStream
import javax.swing.*

class EntityServiceInputDialog() : DialogWrapper(true) {
    private val panel = JPanel(GridBagLayout())
    val featureName = JTextField()

    init {
        title = "Enter Feature Name"
        init()
    }


    override fun createCenterPanel(): JComponent {
        val gbc = GridBagConstraints()
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.anchor = GridBagConstraints.LINE_START
        gbc.weightx = 3.0
        gbc.fill = GridBagConstraints.NONE
        gbc.insets = insets(0, 0, 5, 5)

        val fontSettings = mapOf(TextAttribute.WEIGHT to TextAttribute.WEIGHT_BOLD)


        val fontStream: InputStream? = javaClass.getResourceAsStream("/font/Montserrat-VariableFont_wght.ttf")
        val baseFont = Font.createFont(Font.PLAIN, fontStream)
        val derivedFont = baseFont.deriveFont(fontSettings)
        val font = derivedFont.deriveFont(18f)

        val label = JLabel("Feature Name:")
        label.font = font // Set the loaded font
        panel.add(label, gbc)

        gbc.gridx = 1
        gbc.weightx = 2.0
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = JBUI.insetsBottom(5)

        featureName.preferredSize = Dimension(300, featureName.preferredSize.height)
        featureName.minimumSize = Dimension(200, featureName.preferredSize.height)
        featureName.font = font
        panel.add(featureName, gbc)

        return panel
    }

    override fun doValidate(): ValidationInfo? {
        if (featureName.text.isBlank()) {
            return ValidationInfo("Feature Name is required.", featureName)
        }
        return null
    }

    override fun createActions(): Array<Action> {
        return arrayOf(okAction, cancelAction)
    }

    override fun getPreferredFocusedComponent(): JComponent {
        return featureName
    }

    override fun doOKAction() {
        if (doValidate() == null) {
            super.doOKAction()
        }
    }

    override fun getPreferredSize(): Dimension {
        return Dimension(600, super.getPreferredSize().height)
    }

}
