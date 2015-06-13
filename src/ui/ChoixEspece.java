package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import plugins.Appli;
import core.Platform;

public class ChoixEspece extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ChoixEspece() {
		setBounds(100, 100, 450, 81);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JButton btnDragon = new JButton("Dragon");
			contentPanel.add(btnDragon);
			btnDragon.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	((Appli) Platform.getInstance().getPlugin("JeuTamagotchi")).newGame(1);
	            }
	        });
		}
		{
			JButton btnLicorne = new JButton("Licorne");
			contentPanel.add(btnLicorne);
			btnLicorne.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent event) {
	            	((Appli) Platform.getInstance().getPlugin("JeuTamagotchi")).newGame(2);
	            }
	        });
		}
	}

}
