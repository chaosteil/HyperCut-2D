package com.stacktraceinc.hypercut2d;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CanvasSizeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JSpinner txtPlotis;
	public JSpinner txtAukstis;
	public boolean okay = false;

	/**
	 * Create the dialog.
	 */
	public CanvasSizeDialog(JDialog owner) {
		super(owner, true);
		setTitle("Prid\u0117ti br\u0117\u017Einio dyd\u012F...");
		setResizable(false);
		setBounds(100, 100, 367, 129);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow][][]", "[][][]"));
		{
			JLabel lblPlotis = new JLabel("Plotis:");
			contentPanel.add(lblPlotis, "cell 0 0,alignx trailing");
		}
		{
			txtPlotis = new JSpinner();
			txtPlotis.setModel(new SpinnerNumberModel(new Integer(100), new Integer(1), null, new Integer(1)));
			contentPanel.add(txtPlotis, "cell 1 0,growx");
		}
		{
			JLabel lblMm = new JLabel("mm");
			contentPanel.add(lblMm, "cell 2 0");
		}
		{
			JLabel lblAuktis = new JLabel("Auk\u0161tis:");
			contentPanel.add(lblAuktis, "cell 0 1,alignx trailing");
		}
		{
			txtAukstis = new JSpinner();
			txtAukstis.setModel(new SpinnerNumberModel(new Integer(100), new Integer(1), null, new Integer(1)));
			contentPanel.add(txtAukstis, "cell 1 1,growx");
		}
		{
			JLabel lblMm_1 = new JLabel("mm");
			contentPanel.add(lblMm_1, "cell 2 1");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Prid\u0117ti");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						okay = true;
						setVisible(false);
					}
				});
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("At\u0161aukti");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						okay = false;
						setVisible(false);
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
