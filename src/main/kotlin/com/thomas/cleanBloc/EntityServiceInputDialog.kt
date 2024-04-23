package com.thomas.cleanBloc

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.JBUI.insets
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.*

class EntityServiceInputDialog() : DialogWrapper(true) {
    private val panel = JPanel(GridBagLayout())
    val featureName = JTextField()
    val addFeatureOnlyCheckbox = JCheckBox("Add Feature Only")

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

        val label = JLabel("Feature Name:")
        panel.add(label, gbc)

        gbc.gridx = 1
        gbc.weightx = 2.0
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = JBUI.insetsBottom(5)

        featureName.preferredSize = Dimension(300, featureName.preferredSize.height)
        featureName.minimumSize = Dimension(200, featureName.preferredSize.height)
        panel.add(featureName, gbc)

        gbc.gridx = 0
        gbc.gridy = 1
        gbc.gridwidth = 2
        addFeatureOnlyCheckbox.isSelected = false
        panel.add(addFeatureOnlyCheckbox, gbc)

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
