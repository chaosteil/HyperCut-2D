package com.stacktraceinc.hypercut2d;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import org.jdesktop.beansbinding.BeanProperty;
import java.util.List;
import org.jdesktop.swingbinding.JListBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.ELProperty;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CanvasSizes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private CanvasSizeHandler handler;
	private JList lstDydziai;

	/**
	 * Create the dialog.
	 */
	public CanvasSizes(JFrame owner, CanvasSizeHandler handler) {
		super(owner, true);
		this.handler = handler;
		
		setTitle("Br\u0117\u017Einio dyd\u017Eiai");
		setResizable(false);
		setBounds(100, 100, 320, 454);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[grow][][][]"));
		{
			JScrollPane srpDydziai = new JScrollPane();
			contentPanel.add(srpDydziai, "cell 0 0,grow");
			{
				lstDydziai = new JList();
				srpDydziai.setViewportView(lstDydziai);
			}
		}
		{
			JButton btnPridtiNauj = new JButton("Prid\u0117ti nauj\u0105...");
			btnPridtiNauj.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					CanvasSizeDialog cvd = new CanvasSizeDialog(CanvasSizes.this);
					if (!cvd.okay) {
						return;
					}
					
					int width = (Integer) cvd.txtPlotis.getValue();
					int height = (Integer) cvd.txtAukstis.getValue();
					CanvasSizes.this.handler.addSize(new Dimension(width, height));
					
				}
			});
			btnPridtiNauj.setIcon(new ImageIcon(CanvasSizes.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
			contentPanel.add(btnPridtiNauj, "flowx,cell 0 1,grow");
		}
		{
			JButton btnItrinti = new JButton("I\u0161trinti");
			btnItrinti.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent arg0) {
					if (lstDydziai.getSelectedIndex() == -1){
						return;
					}
					
					// Do not allow to erase the last element
					if (CanvasSizes.this.handler.getSizes().size() == 1) {
						return;
					}
					CanvasSizes.this.handler.eraseSize(lstDydziai.getSelectedIndex());
				}
			});
			btnItrinti.setIcon(new ImageIcon(CanvasSizes.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
			contentPanel.add(btnItrinti, "cell 0 1,grow");
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			contentPanel.add(horizontalStrut, "cell 0 2");
		}
		{
			JButton btnIsaugoti = new JButton("I\u0161saugoti...");
			btnIsaugoti.setIcon(new ImageIcon(CanvasSizes.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
			contentPanel.add(btnIsaugoti, "flowx,cell 0 3,grow");
		}
		{
			JButton btnAtidaryti = new JButton("Atidaryti...");
			btnAtidaryti.setIcon(new ImageIcon(CanvasSizes.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
			contentPanel.add(btnAtidaryti, "cell 0 3,grow");
		}
		{
			JPanel btpVeiksmai = new JPanel();
			btpVeiksmai.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(btpVeiksmai, BorderLayout.SOUTH);
			{
				JButton btnIseiti = new JButton("I\u0161eiti");
				btnIseiti.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				btpVeiksmai.add(btnIseiti);
				getRootPane().setDefaultButton(btnIseiti);
			}
		}

		initDataBindings();
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	protected void initDataBindings() {
		BeanProperty<CanvasSizeHandler, List<Dimension>> canvasSizeHandlerBeanProperty = BeanProperty.create("sizes");
		JListBinding<Dimension, CanvasSizeHandler, JList> jListBinding = SwingBindings.createJListBinding(UpdateStrategy.READ, handler, canvasSizeHandlerBeanProperty, lstDydziai);
		//
		ELProperty<Dimension, Object> dimensionEvalutionProperty = ELProperty.create("${width} mm x ${height} mm");
		jListBinding.setDetailBinding(dimensionEvalutionProperty);
		//
		jListBinding.bind();
	}
}
