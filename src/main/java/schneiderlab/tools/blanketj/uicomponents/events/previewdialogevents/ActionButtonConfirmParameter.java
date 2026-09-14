package schneiderlab.tools.blanketj.uicomponents.events.previewdialogevents;

import schneiderlab.tools.blanketj.ZStackDirection;
import schneiderlab.tools.blanketj.uicomponents.MainDialog;
import schneiderlab.tools.blanketj.uicomponents.PreviewDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionButtonConfirmParameter implements ActionListener {
    private MainDialog mainDialogView;
    private PreviewDialog previewDialogView;

    public ActionButtonConfirmParameter(MainDialog mainDialogView,
                                        PreviewDialog previewDialogView) {
        this.mainDialogView = mainDialogView;
        this.previewDialogView = previewDialogView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ZStackDirection direction = (ZStackDirection) previewDialogView.getComboBox1ZStackDirection().getSelectedItem();
        mainDialogView.getComboBox1ZStackDirection().setSelectedItem(direction);
        int stiffness = (int)previewDialogView.getSpinner1Stiffeness().getValue();
        int filtersize = (int)previewDialogView.getSpinner2FilterSize().getValue();
        int offset = (int)previewDialogView.getSpinner3Offset().getValue();
        int depth = (int)previewDialogView.getSpinner4Depth().getValue();
        mainDialogView.getSpinner1Stiffeness().setValue(stiffness);
        mainDialogView.getSpinner2FilterSize().setValue(filtersize);
        mainDialogView.getSpinner3Offset().setValue(offset);
        mainDialogView.getSpinner4Depth().setValue(depth);
    }
}
