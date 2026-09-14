package schneiderlab.tools.blanketj.uicomponents.events;

import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ActionMouseWheelSpinnerInteger implements MouseWheelListener {
    private JSpinner spinner;

    public ActionMouseWheelSpinnerInteger(JSpinner spinner) {
        this.spinner = spinner;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (spinner == null) return;
        int rotation = e.getWheelRotation();
        SpinnerModel model = spinner.getModel();

        if (model instanceof SpinnerNumberModel) {
            SpinnerNumberModel numberModel = (SpinnerNumberModel) model;
            // Get current value as integer
            int currentValue = ((Number)numberModel.getValue()).intValue();
            int stepSize = ((Number)numberModel.getStepSize()).intValue();
            // Calculate new value
            int newValue = currentValue - (rotation * stepSize);
            // Check min/max bounds (they might be null)
            Integer min = (numberModel.getMinimum() != null)
                    ? ((Number)numberModel.getMinimum()).intValue()
                    : null;
            Integer max = (numberModel.getMaximum() != null)
                    ? ((Number)numberModel.getMaximum()).intValue()
                    : null;

            // Apply bounds checking
            if ((min == null || newValue >= min) &&
                    (max == null || newValue <= max)) {
                numberModel.setValue(newValue);
            }
        }
    }
}
