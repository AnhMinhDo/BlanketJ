package schneiderlab.tools.blanketj.uicomponents;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.*;
import schneiderlab.tools.blanketj.SMPToolView;
import schneiderlab.tools.blanketj.ZStackDirection;
/*
 * Created by JFormDesigner on Mon Jun 09 23:41:48 CEST 2025
 */


/**
 * @author anhminh
 */
public class MainDialog extends JFrame implements SMPToolView {
	private JFrame parentFrame;

	public MainDialog(JFrame parentFrame) {
		initComponents();
		this.parentFrame = parentFrame;
	}

	public JFrame getParentFrame() {
		return parentFrame;
	}

	public JLabel getLabel1ProcessMode() {
		return label1ProcessMode;
	}

	public JRadioButton getRadioButton1SingleFile() {
		return radioButton1SingleFile;
	}

	public JRadioButton getRadioButton2MultipleFiles() {
		return radioButton2MultipleFiles;
	}

	public JRadioButton getRadioButton3Preview() {
		return radioButton3Preview;
	}

	public JToggleButton getToggleButton1LightDarkTheme() {
		return toggleButton1LightDarkTheme;
	}

	public JLabel getLabel5References() {
		return label5References;
	}

	public JCheckBox getCheckBox2SingleFileRef() {
		return checkBox2SingleFileRef;
	}

	public JComboBox getComboBox3SingleFileRef() {
		return comboBox3SingleFileRef;
	}

	public JCheckBox getCheckBox3BatchFileRef() {
		return checkBox3BatchFileRef;
	}

	public JComboBox getComboBox4BatchFileRef() {
		return comboBox4BatchFileRef;
	}

	public JCheckBox getCheckBox4PreviewRef() {
		return checkBox4PreviewRef;
	}

	public JComboBox getComboBox5PreviewRef() {
		return comboBox5PreviewRef;
	}

	public JLabel getLabel2ZStackDirection() {
		return label2ZStackDirection;
	}

	public JComboBox<String> getComboBox1ZStackDirection() {
		return comboBox1ZStackDirection;
	}

	public JLabel getLabel3Stiffness() {
		return label3Stiffness;
	}

	public JSpinner getSpinner1Stiffeness() {
		return spinner1Stiffeness;
	}

	public JLabel getLabel4FilterSize() {
		return label4FilterSize;
	}

	public JSpinner getSpinner2FilterSize() {
		return spinner2FilterSize;
	}

	public JSpinner getSpinnerSigma(){return spinnerSigma;}

	public JSpinner getSpinner3Offset() {
		return spinner3Offset;
	}

	public JLabel getLabel8Offset() {
		return label8Offset;
	}

	public JLabel getLabel6Depth() {
		return label6Depth;
	}

	public JSpinner getSpinner4Depth() {
		return spinner4Depth;
	}

	public JLabel getLabel7DirPath() {
		return label7DirPath;
	}

	public JButton getButton1BrowseDirPath() {
		return button1BrowseDirPath;
	}

	public JTextField getTextField1DirPath() {
		return textField1DirPath;
	}

	public JLabel getLabel9SingleFilePath() {
		return label9SingleFilePath;
	}

	public JButton getButton2BrowseSingleFile() {
		return button2BrowseSingleFile;
	}

	public JTextField getTextField2SingleFilePath() {
		return textField2SingleFilePath;
	}

	public JLabel getLabel11ChooseOutput() {
		return label11ChooseOutput;
	}

	public JButton getButton3SelectOutput() {
		return button3SelectOutput;
	}

	public JTextField getTextField3Status() {
		return textField3Status;
	}

	public JProgressBar getProgressBar1() {
		return progressBar1;
	}

	public JButton getButton4StartProcess() {
		return button4StartProcess;
	}

	public JButton getButton5Cancel() {
		return button5Cancel;
	}

	public JPopupMenu getPopupMenu1Output() {
		return popupMenu1Output;
	}

	public JCheckBoxMenuItem getCheckBoxMenuItem8MIP() {
		return checkBoxMenuItem8MIP;
	}

	public JCheckBoxMenuItem getCheckBoxMenuItem9MIPzMap() {
		return checkBoxMenuItem9MIPzMap;
	}

	public JCheckBoxMenuItem getCheckBoxMenuItem10SMP() {
		return checkBoxMenuItem10SMP;
	}

	public JCheckBoxMenuItem getCheckBoxMenuItem11SMPzMap() {
		return checkBoxMenuItem11SMPzMap;
	}

	public ButtonGroup getButtonGroup1ProcessMode() {
		return buttonGroup1ProcessMode;
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Educational license - Anh Minh Do
		label1ProcessMode = new JLabel();
		radioButton1SingleFile = new JRadioButton();
		separator1 = new JSeparator();
		radioButton2MultipleFiles = new JRadioButton();
		separator2 = new JSeparator();
		radioButton3Preview = new JRadioButton();
		toggleButton1LightDarkTheme = new JToggleButton();
		label5References = new JLabel();
		checkBox2SingleFileRef = new JCheckBox();
		comboBox3SingleFileRef = new JComboBox();
		checkBox3BatchFileRef = new JCheckBox();
		comboBox4BatchFileRef = new JComboBox();
		checkBox4PreviewRef = new JCheckBox();
		comboBox5PreviewRef = new JComboBox();
		label2ZStackDirection = new JLabel();
		comboBox1ZStackDirection = new JComboBox<>(ZStackDirection.values());
		label3Stiffness = new JLabel();
		spinner1Stiffeness = new JSpinner();
		label4FilterSize = new JLabel();
		spinner2FilterSize = new JSpinner();
		labelSigma = new JLabel();
		spinnerSigma = new JSpinner();
		spinner3Offset = new JSpinner();
		label8Offset = new JLabel();
		label6Depth = new JLabel();
		spinner4Depth = new JSpinner();
		label7DirPath = new JLabel();
		button1BrowseDirPath = new JButton();
		textField1DirPath = new JTextField();
		label9SingleFilePath = new JLabel();
		button2BrowseSingleFile = new JButton();
		textField2SingleFilePath = new JTextField();
		label11ChooseOutput = new JLabel();
		button3SelectOutput = new JButton();
		textField3Status = new JTextField();
		progressBar1 = new JProgressBar();
		button4StartProcess = new JButton();
		button5Cancel = new JButton();
		popupMenu1Output = new JPopupMenu();
		checkBoxMenuItem8MIP = new JCheckBoxMenuItem();
		checkBoxMenuItem9MIPzMap = new JCheckBoxMenuItem();
		checkBoxMenuItem10SMP = new JCheckBoxMenuItem();
		checkBoxMenuItem11SMPzMap = new JCheckBoxMenuItem();
		buttonGroup1ProcessMode = new ButtonGroup();

		//======== this ========
		Container contentPane = getContentPane();
		contentPane.setLayout(new MigLayout(
			"hidemode 3,align center top",
			// columns
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]" +
			"[fill]",
			// rows
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]"));

		//---- label1ProcessMode ----
		label1ProcessMode.setText("Process Mode:");
		contentPane.add(label1ProcessMode, "cell 0 0");

		//---- radioButton1SingleFile ----
		radioButton1SingleFile.setText("SINGLE_FILE");
		radioButton1SingleFile.setSelected(true);
		radioButton1SingleFile.setHorizontalAlignment(SwingConstants.CENTER);
		radioButton1SingleFile.setIconTextGap(20);
		contentPane.add(radioButton1SingleFile, "cell 1 0");

		//---- separator1 ----
		separator1.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator1, "cell 2 0 1 2,growy");

		//---- radioButton2MultipleFiles ----
		radioButton2MultipleFiles.setText("MULTIPLE_FILES");
		radioButton2MultipleFiles.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(radioButton2MultipleFiles, "cell 3 0");

		//---- separator2 ----
		separator2.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator2, "cell 4 0 1 2,growy");

		//---- radioButton3Preview ----
		radioButton3Preview.setText("PREVIEW");
		radioButton3Preview.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(radioButton3Preview, "cell 5 0");

		//---- toggleButton1LightDarkTheme ----
		toggleButton1LightDarkTheme.setIcon(new ImageIcon(getClass().getResource("/iconimages/light_mode_26dp_000000_FILL0_wght400_GRAD0_opsz24.png")));
		toggleButton1LightDarkTheme.setSelectedIcon(new ImageIcon(getClass().getResource("/iconimages/dark_mode_26dp_FFFFFF_FILL0_wght400_GRAD0_opsz24.png")));
		toggleButton1LightDarkTheme.setBorderPainted(false);
		toggleButton1LightDarkTheme.setVisible(false);
		contentPane.add(toggleButton1LightDarkTheme, "cell 6 0");

		//---- label5References ----
		label5References.setText("References:");
		contentPane.add(label5References, "cell 0 1");

		//---- checkBox2SingleFileRef ----
		checkBox2SingleFileRef.setEnabled(false);
		contentPane.add(checkBox2SingleFileRef, "cell 1 1,alignx left,growx 0");

		//---- comboBox3SingleFileRef ----
		comboBox3SingleFileRef.setEnabled(false);
		contentPane.add(comboBox3SingleFileRef, "cell 1 1");

		//---- checkBox3BatchFileRef ----
		checkBox3BatchFileRef.setEnabled(false);
		contentPane.add(checkBox3BatchFileRef, "cell 3 1,alignx left,growx 0");

		//---- comboBox4BatchFileRef ----
		comboBox4BatchFileRef.setEnabled(false);
		contentPane.add(comboBox4BatchFileRef, "cell 3 1");

		//---- checkBox4PreviewRef ----
		checkBox4PreviewRef.setEnabled(false);
		contentPane.add(checkBox4PreviewRef, "cell 5 1,alignx left,growx 0");

		//---- comboBox5PreviewRef ----
		comboBox5PreviewRef.setEnabled(false);
		contentPane.add(comboBox5PreviewRef, "cell 5 1");

		//---- label2ZStackDirection ----
		label2ZStackDirection.setText("Direction of Z stack: ");
		contentPane.add(label2ZStackDirection, "cell 0 2 4 1,alignx right,growx 0");
		contentPane.add(comboBox1ZStackDirection, "cell 5 2,alignx left,growx 0");

		//---- label3Stiffness ----
		label3Stiffness.setText("Envelope stiffness [pixels]:");
		label3Stiffness.setLabelFor(spinner1Stiffeness);
		contentPane.add(label3Stiffness, "cell 0 3 4 1,alignx right,growx 0");

		//---- spinner1Stiffeness ----
		spinner1Stiffeness.setModel(new SpinnerNumberModel(60, 0, null, 1));
		contentPane.add(spinner1Stiffeness, "cell 5 3");

		//---- label4FilterSize ----
		label4FilterSize.setText("Filter size [pixel]:");
		label4FilterSize.setLabelFor(spinner2FilterSize);
		contentPane.add(label4FilterSize, "cell 0 4 4 1,alignx right,growx 0");

		//---- spinner2FilterSize ----
		spinner2FilterSize.setModel(new SpinnerNumberModel(30, 0, null, 1));
		contentPane.add(spinner2FilterSize, "cell 5 4");

		//---- labelSigma ----
		labelSigma.setText("Sigma:");
		contentPane.add(labelSigma, "cell 0 5 4 1,alignx right,growx 0");

		//---- spinnerSigma ----
		spinnerSigma.setModel(new SpinnerNumberModel(0.0, 0.0, null, 0.1));
		contentPane.add(spinnerSigma, "cell 5 5");

		//---- spinner3Offset ----
		spinner3Offset.setModel(new SpinnerNumberModel(0, null, null, 1));
		contentPane.add(spinner3Offset, "cell 5 6");

		//---- label8Offset ----
		label8Offset.setText("Offset [pixels]:");
		label8Offset.setToolTipText("Offset: number of planes above(+) or below(-) blanket [pixels]");
		contentPane.add(label8Offset, "cell 0 6 4 1,alignx right,growx 0");

		//---- label6Depth ----
		label6Depth.setText("Depth [pixels]:");
		label6Depth.setLabelFor(spinner4Depth);
		label6Depth.setToolTipText("Depth:MIP for N pixels into blanket [pixels]");
		contentPane.add(label6Depth, "cell 0 7 4 1,alignx right,growx 0");

		//---- spinner4Depth ----
		spinner4Depth.setModel(new SpinnerNumberModel(0, null, null, 1));
		contentPane.add(spinner4Depth, "cell 5 7");

		//---- label7DirPath ----
		label7DirPath.setText("Directory for MULTIPLE_FILES:");
		contentPane.add(label7DirPath, "cell 0 8 2 1,alignx right,growx 0");

		//---- button1BrowseDirPath ----
		button1BrowseDirPath.setText("Browse");
		button1BrowseDirPath.setEnabled(false);
		contentPane.add(button1BrowseDirPath, "cell 3 8");

		//---- textField1DirPath ----
		textField1DirPath.setEnabled(false);
		textField1DirPath.setEditable(false);
		contentPane.add(textField1DirPath, "cell 5 8");

		//---- label9SingleFilePath ----
		label9SingleFilePath.setText("File path for SINGLE FILE:");
		contentPane.add(label9SingleFilePath, "cell 0 9 2 1,alignx right,growx 0");

		//---- button2BrowseSingleFile ----
		button2BrowseSingleFile.setText("Browse");
		contentPane.add(button2BrowseSingleFile, "cell 3 9");

		//---- textField2SingleFilePath ----
		textField2SingleFilePath.setEditable(false);
		contentPane.add(textField2SingleFilePath, "cell 5 9");

		//---- label11ChooseOutput ----
		label11ChooseOutput.setText("Choose Output File type:");
		contentPane.add(label11ChooseOutput, "cell 0 10 4 1,alignx right,growx 0");

		//---- button3SelectOutput ----
		button3SelectOutput.setText("select output");
		button3SelectOutput.setComponentPopupMenu(popupMenu1Output);
		contentPane.add(button3SelectOutput, "cell 5 10");

		//---- textField3Status ----
		textField3Status.setEditable(false);
		contentPane.add(textField3Status, "cell 0 11");
		contentPane.add(progressBar1, "cell 1 11");

		//---- button4StartProcess ----
		button4StartProcess.setText("Start Process");
		contentPane.add(button4StartProcess, "cell 3 11");

		//---- button5Cancel ----
		button5Cancel.setText("Cancel");
		contentPane.add(button5Cancel, "cell 5 11");
		pack();
		setLocationRelativeTo(getOwner());

		//======== popupMenu1Output ========
		{
			popupMenu1Output.setBorder(new EtchedBorder());

			//---- checkBoxMenuItem8MIP ----
			checkBoxMenuItem8MIP.setText("MIP");
			popupMenu1Output.add(checkBoxMenuItem8MIP);

			//---- checkBoxMenuItem9MIPzMap ----
			checkBoxMenuItem9MIPzMap.setText("MIP z map");
			popupMenu1Output.add(checkBoxMenuItem9MIPzMap);

			//---- checkBoxMenuItem10SMP ----
			checkBoxMenuItem10SMP.setText("SMP ");
			checkBoxMenuItem10SMP.setSelected(true);
			popupMenu1Output.add(checkBoxMenuItem10SMP);

			//---- checkBoxMenuItem11SMPzMap ----
			checkBoxMenuItem11SMPzMap.setText("SMP z map");
			popupMenu1Output.add(checkBoxMenuItem11SMPzMap);
		}

		//---- buttonGroup1ProcessMode ----
		buttonGroup1ProcessMode.add(radioButton1SingleFile);
		buttonGroup1ProcessMode.add(radioButton2MultipleFiles);
		buttonGroup1ProcessMode.add(radioButton3Preview);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Educational license - Anh Minh Do
	private JLabel label1ProcessMode;
	private JRadioButton radioButton1SingleFile;
	private JSeparator separator1;
	private JRadioButton radioButton2MultipleFiles;
	private JSeparator separator2;
	private JRadioButton radioButton3Preview;
	private JToggleButton toggleButton1LightDarkTheme;
	private JLabel label5References;
	private JCheckBox checkBox2SingleFileRef;
	private JComboBox comboBox3SingleFileRef;
	private JCheckBox checkBox3BatchFileRef;
	private JComboBox comboBox4BatchFileRef;
	private JCheckBox checkBox4PreviewRef;
	private JComboBox comboBox5PreviewRef;
	private JLabel label2ZStackDirection;
	private JComboBox comboBox1ZStackDirection;
	private JLabel label3Stiffness;
	private JSpinner spinner1Stiffeness;
	private JLabel label4FilterSize;
	private JSpinner spinner2FilterSize;
	private JLabel labelSigma;
	private JSpinner spinnerSigma;
	private JSpinner spinner3Offset;
	private JLabel label8Offset;
	private JLabel label6Depth;
	private JSpinner spinner4Depth;
	private JLabel label7DirPath;
	private JButton button1BrowseDirPath;
	private JTextField textField1DirPath;
	private JLabel label9SingleFilePath;
	private JButton button2BrowseSingleFile;
	private JTextField textField2SingleFilePath;
	private JLabel label11ChooseOutput;
	private JButton button3SelectOutput;
	private JTextField textField3Status;
	private JProgressBar progressBar1;
	private JButton button4StartProcess;
	private JButton button5Cancel;
	private JPopupMenu popupMenu1Output;
	private JCheckBoxMenuItem checkBoxMenuItem8MIP;
	private JCheckBoxMenuItem checkBoxMenuItem9MIPzMap;
	private JCheckBoxMenuItem checkBoxMenuItem10SMP;
	private JCheckBoxMenuItem checkBoxMenuItem11SMPzMap;
	private ButtonGroup buttonGroup1ProcessMode;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
