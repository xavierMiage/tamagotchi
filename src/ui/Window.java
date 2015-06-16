package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JProgressBar;

import plugins.Appli;
import plugins.Tamagotchi;
import core.Platform;

/*
 *	Classe permettant la création de la vue 
 */

public class Window extends JFrame {

	private JPanel contentPane;
	private Properties properties;
	private Appli app;
	private PanelDragon panel;

	/**
	 * Create the frame.
	 * @param properties 
	 * @param platform 
	 */
	public Window(Properties properties, Appli app) {
		this.properties = properties;
		this.app = app;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		
		// Bar de menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Bouton File
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		// Menu déroulant du bouton File
		JMenuItem eMenuItemLoad = new JMenuItem("Charger");
		eMenuItemLoad.setMnemonic(KeyEvent.VK_F1);
		eMenuItemLoad.setToolTipText("Charger la partie");
		eMenuItemLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	// TODO : Je ne sais pas si on a le droit d'appeler le plugin appli à partir d'ici
                Platform.getInstance().getPlugin("LoadTamagotchi");
            }
        });

        mnFile.add(eMenuItemLoad);

		JMenuItem eMenuItemSave = new JMenuItem("Sauvegarder");
        eMenuItemSave.setMnemonic(KeyEvent.VK_F2);
        eMenuItemSave.setToolTipText("Sauvegarder la partie");
        eMenuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
				app.save();
            }
        });

        mnFile.add(eMenuItemSave);
        
        JMenuItem eMenuItemNew = new JMenuItem("Nouvelle partie");
        eMenuItemNew.setMnemonic(KeyEvent.VK_F3);
        eMenuItemNew.setToolTipText("Nouvelle partie");
        eMenuItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                app.choixEspece();
            }
        });

        mnFile.add(eMenuItemNew);
        

		
        // Panel 1, contenu de la fenêtre
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(contentPane);
	}
	
	public void showGame(Tamagotchi tama) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		JPanel panel;
		contentPane.removeAll();
		contentPane.revalidate();
		contentPane.repaint();
		// Panel 2, contenant l'image et les boutons
		if(tama.getEspece() == 2)
			 panel = new PanelLicorne();
		else
			panel = new PanelDragon();

		contentPane.add(panel, BorderLayout.CENTER);
		
		// Chargement dynamique des boutons d'actions
		for(Object key : properties.keySet()) {
			JButton btn = new JButton((String) key);
			panel.add(btn);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	                app.soccuper(properties.get(key).toString());
				}
			});
			String[] conf = properties.get(key).toString().split(";");
			JProgressBar progressBar = new JProgressBar();
			progressBar.setMaximum(100);
			Method m = tama.getClass().getMethod("get" + conf[0]);
			int val = (int) m.invoke(tama);
			progressBar.setValue(val);
			progressBar.setStringPainted(true);
			panel.add(progressBar, BorderLayout.PAGE_END);
		}
		
		/*JProgressBar progressBar = new JProgressBar();
		progressBar.setMaximum(100);
		progressBar.setValue(tama.getFaim());
		progressBar.setStringPainted(true);
		panel.add(progressBar, BorderLayout.PAGE_END);
		//panel.add(pa, BorderLayout.SOUTH);*/
		
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		
		// Bar de menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Bouton File
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		// Menu déroulant du bouton File
		JMenuItem eMenuItemLoad = new JMenuItem("Charger");
		eMenuItemLoad.setMnemonic(KeyEvent.VK_F1);
		eMenuItemLoad.setToolTipText("Charger la partie");
		eMenuItemLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            }
        });

        mnFile.add(eMenuItemLoad);

		JMenuItem eMenuItemSave = new JMenuItem("Sauvegarder");
        eMenuItemSave.setMnemonic(KeyEvent.VK_F2);
        eMenuItemSave.setToolTipText("Sauvegarder la partie");
        eMenuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            }
        });

        mnFile.add(eMenuItemSave);
        
        JMenuItem eMenuItemNew = new JMenuItem("Nouvelle partie");
        eMenuItemNew.setMnemonic(KeyEvent.VK_F3);
        eMenuItemNew.setToolTipText("Nouvelle partie");
        eMenuItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            }
        });

        mnFile.add(eMenuItemNew);
		
        // Panel 1, contenu de la fenêtre
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Panel 2, contenant l'image et les boutons
		JPanel panel = new PanelLicorne();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnNourrir = new JButton("Nourrir");
		panel.add(btnNourrir);
		btnNourrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		}); 
		
		JButton btnLaver = new JButton("Laver");
		panel.add(btnLaver);
		btnLaver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

}
