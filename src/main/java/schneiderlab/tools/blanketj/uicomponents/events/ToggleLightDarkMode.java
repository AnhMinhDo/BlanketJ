package schneiderlab.tools.blanketj.uicomponents.events;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleLightDarkMode implements ActionListener {
    private JToggleButton toggle;
    private JFrame frame;
    private String osName;

    public ToggleLightDarkMode(JToggleButton toggle, JFrame frame) {
        this.toggle = toggle;
        this.frame = frame;
        osName = System.getProperty("os.name").toLowerCase();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (toggle.isSelected()){
                if(osName.contains("mac")){
                    UIManager.setLookAndFeel(new FlatMacDarkLaf());
                } else{
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                }
            } else {
                if(osName.contains("mac")){
                    UIManager.setLookAndFeel(new FlatMacLightLaf());
                } else {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                }
            }
            SwingUtilities.updateComponentTreeUI(frame);
            // Update all components in the frame
            frame.revalidate();
            frame.repaint();
        } catch (UnsupportedLookAndFeelException ex) {
            throw new RuntimeException(ex);
        }
    }
}
