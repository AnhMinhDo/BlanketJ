package schneiderlab.tools.blanketj.uicomponents;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;
import schneiderlab.tools.blanketj.ZStackDirection;
/*
 * Created by JFormDesigner on Wed Jun 25 19:41:46 CEST 2025
 */



/**
 * @author anhminh
 */
public class PreviewDialog extends JDialog {
	public PreviewDialog(JFrame owner) {
		super(owner);
		setModal(false);
		initComponents();
	}

	public JLabel getLabel2ZStackDirection() {
		return label2ZStackDirection;
	}

	public void setLabel2ZStackDirection(JLabel label2ZStackDirection) {
		this.label2ZStackDirection = label2ZStackDirection;
	}

	public JComboBox<String> getComboBox1ZStackDirection() {
		return comboBox1ZStackDirection;
	}

	public void setComboBox1ZStackDirection(JComboBox<String> comboBox1ZStackDirection) {
		this.comboBox1ZStackDirection = comboBox1ZStackDirection;
	}

	public JLabel getLabel3Stiffness() {
		return label3Stiffness;
	}

	public void setLabel3Stiffness(JLabel label3Stiffness) {
		this.label3Stiffness = label3Stiffness;
	}

	public JSpinner getSpinner1Stiffeness() {
		return spinner1Stiffeness;
	}

	public void setSpinner1Stiffeness(JSpinner spinner1Stiffeness) {
		this.spinner1Stiffeness = spinner1Stiffeness;
	}

	public JSpinner getSpinnerSigma(){return spinnerSigma;}

	public JLabel getLabel4FilterSize() {
		return label4FilterSize;
	}

	public void setLabel4FilterSize(JLabel label4FilterSize) {
		this.label4FilterSize = label4FilterSize;
	}

	public JSpinner getSpinner2FilterSize() {
		return spinner2FilterSize;
	}

	public void setSpinner2FilterSize(JSpinner spinner2FilterSize) {
		this.spinner2FilterSize = spinner2FilterSize;
	}

	public JLabel getLabel8Offset() {
		return label8Offset;
	}

	public void setLabel8Offset(JLabel label8Offset) {
		this.label8Offset = label8Offset;
	}

	public JSpinner getSpinner3Offset() {
		return spinner3Offset;
	}

	public void setSpinner3Offset(JSpinner spinner3Offset) {
		this.spinner3Offset = spinner3Offset;
	}

	public JLabel getLabel6Depth() {
		return label6Depth;
	}

	public void setLabel6Depth(JLabel label6Depth) {
		this.label6Depth = label6Depth;
	}

	public JSpinner getSpinner4Depth() {
		return spinner4Depth;
	}

	public void setSpinner4Depth(JSpinner spinner4Depth) {
		this.spinner4Depth = spinner4Depth;
	}

	public JTextField getTextField3Status() {
		return textField3Status;
	}

	public void setTextField3Status(JTextField textField3Status) {
		this.textField3Status = textField3Status;
	}

	public JProgressBar getProgressBar1() {
		return progressBar1;
	}

	public void setProgressBar1(JProgressBar progressBar1) {
		this.progressBar1 = progressBar1;
	}

	public JButton getButton1Parameter() {
		return button1Parameter;
	}

	public void setButton1Parameter(JButton button1Parameter) {
		this.button1Parameter = button1Parameter;
	}

	public JButton getButton5Cancel() {
		return button5Cancel;
	}

	public void setButton5Cancel(JButton button5Cancel) {
		this.button5Cancel = button5Cancel;
	}

	public JButton getButton4StartProcess() {
		return button4StartProcess;
	}

	public void setButton4StartProcess(JButton button4StartProcess) {
		this.button4StartProcess = button4StartProcess;
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Educational license - Anh Minh Do
		label2ZStackDirection = new JLabel();
		comboBox1ZStackDirection = new JComboBox<>(ZStackDirection.values());
		label3Stiffness = new JLabel();
		spinner1Stiffeness = new JSpinner();
		label4FilterSize = new JLabel();
		spinner2FilterSize = new JSpinner();
		labelSigma = new JLabel();
		spinnerSigma = new JSpinner();
		label8Offset = new JLabel();
		spinner3Offset = new JSpinner();
		label6Depth = new JLabel();
		spinner4Depth = new JSpinner();
		textField3Status = new JTextField();
		progressBar1 = new JProgressBar();
		button1Parameter = new JButton();
		button5Cancel = new JButton();
		button4StartProcess = new JButton();

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
			"[fill]" +
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
			"[7]" +
			"[]" +
			"[]" +
			"[]" +
			"[]" +
			"[]"));

		//---- label2ZStackDirection ----
		label2ZStackDirection.setText("Direction of Z stack: ");
		contentPane.add(label2ZStackDirection, "cell 0 0 3 1,alignx right,growx 0");
		contentPane.add(comboBox1ZStackDirection, "cell 3 0,alignx left,growx 0");

		//---- label3Stiffness ----
		label3Stiffness.setText("Envelope stiffness [pixels]:");
		label3Stiffness.setLabelFor(spinner1Stiffeness);
		contentPane.add(label3Stiffness, "cell 0 1 3 1,alignx right,growx 0");

		//---- spinner1Stiffeness ----
		spinner1Stiffeness.setModel(new SpinnerNumberModel(60, 0, null, 1));
		contentPane.add(spinner1Stiffeness, "cell 3 1");

		//---- label4FilterSize ----
		label4FilterSize.setText("Filter size [pixel]:");
		label4FilterSize.setLabelFor(spinner2FilterSize);
		contentPane.add(label4FilterSize, "cell 0 2 3 1,alignx right,growx 0");

		//---- spinner2FilterSize ----
		spinner2FilterSize.setModel(new SpinnerNumberModel(30, 0, null, 1));
		contentPane.add(spinner2FilterSize, "cell 3 2");

		//---- labelSigma ----
		labelSigma.setText("Sigma:");
		contentPane.add(labelSigma, "cell 0 3 3 1,alignx right,growx 0");

		//---- spinnerSigma ----
		spinnerSigma.setModel(new SpinnerNumberModel(0.0, 0.0, null, 0.1));
		contentPane.add(spinnerSigma, "cell 3 3");

		//---- label8Offset ----
		label8Offset.setText("Offset [pixels]:");
		label8Offset.setToolTipText("Offset-N planes above(+) or below(-) blanket [pixels]");
		contentPane.add(label8Offset, "cell 0 5 3 1,alignx right,growx 0");

		//---- spinner3Offset ----
		spinner3Offset.setModel(new SpinnerNumberModel(0, null, null, 1));
		contentPane.add(spinner3Offset, "cell 3 5");

		//---- label6Depth ----
		label6Depth.setText("Depth [pixels]:");
		label6Depth.setLabelFor(spinner4Depth);
		label6Depth.setToolTipText("Depth:MIP for N pixels into blanket [pixels]");
		contentPane.add(label6Depth, "cell 0 6 3 1,alignx right,growx 0");

		//---- spinner4Depth ----
		spinner4Depth.setModel(new SpinnerNumberModel(0, null, null, 1));
		contentPane.add(spinner4Depth, "cell 3 6");

		//---- textField3Status ----
		textField3Status.setEditable(false);
		contentPane.add(textField3Status, "cell 0 7 2 1");
		contentPane.add(progressBar1, "cell 2 7 2 1");

		//---- button1Parameter ----
		button1Parameter.setText("Confirm Parameters");
		contentPane.add(button1Parameter, "cell 1 8");

		//---- button5Cancel ----
		button5Cancel.setText("Cancel");
		contentPane.add(button5Cancel, "cell 2 8");

		//---- button4StartProcess ----
		button4StartProcess.setText("Update Preview");
		contentPane.add(button4StartProcess, "cell 3 8");
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Educational license - Anh Minh Do
	private JLabel label2ZStackDirection;
	private JComboBox comboBox1ZStackDirection;
	private JLabel label3Stiffness;
	private JSpinner spinner1Stiffeness;
	private JLabel label4FilterSize;
	private JSpinner spinner2FilterSize;
	private JLabel labelSigma;
	private JSpinner spinnerSigma;
	private JLabel label8Offset;
	private JSpinner spinner3Offset;
	private JLabel label6Depth;
	private JSpinner spinner4Depth;
	private JTextField textField3Status;
	private JProgressBar progressBar1;
	private JButton button1Parameter;
	private JButton button5Cancel;
	private JButton button4StartProcess;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
