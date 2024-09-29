package org.dimi;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import static org.dimi.Main.renderSpheres;

public class AxisDocumentListener implements DocumentListener {
    private final Axis axis;
    private final JTextField textField;
    private final int objectIndex;
    private final Scene scene;
    private final JPanel panel;

    public AxisDocumentListener(Axis axis, JTextField textField, int objectIndex, Scene scene, JPanel panel) {
        this.axis = axis;
        this.textField = textField;
        this.objectIndex = objectIndex;
        this.scene = scene;
        this.panel = panel;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        update();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        update();
    }

    private void update() {
        double value;
        if (textField.getText().isEmpty() || textField.getText().equals("-")) {
            value = 0;
        } else {
            value = Double.parseDouble(textField.getText());
        }
        switch (axis) {
            case X:
                scene.updateSphereX(objectIndex, value);
                break;
            case Y:
                scene.updateSphereY(objectIndex, value);
                break;
            case Z:
                scene.updateSphereZ(objectIndex, value);
                break;
        }
        renderSpheres();
        panel.repaint();
    }
}
