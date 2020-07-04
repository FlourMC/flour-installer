package cn.enaium.flourmc.installer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.Checkbox;

/**
 * Project: flour-installer
 * -----------------------------------------------------------
 * Copyright © 2020 | Enaium | All rights reserved.
 */
public class FlourInstaller extends JFrame {

	private JPanel contentPane;
	private JTextField pathTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlourInstaller frame = new FlourInstaller();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FlourInstaller() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel gameLabel = new JLabel("Game");
		gameLabel.setBounds(51, 57, 81, 21);
		contentPane.add(gameLabel);

		JComboBox gameCombo = new JComboBox();
		gameCombo.setModel(new DefaultComboBoxModel(new String[] {"1.8.9", "1.14.4", "1.15.2"}));
		gameCombo.setBounds(227, 54, 111, 27);
		contentPane.add(gameCombo);

		JLabel loaderLabel = new JLabel("Loader");
		loaderLabel.setBounds(51, 109, 81, 21);
		contentPane.add(loaderLabel);

		JComboBox loaderCombo = new JComboBox();
		loaderCombo.setModel(new DefaultComboBoxModel(new String[] {"1.8.9", "1.14.4", "1.15.2"}));
		loaderCombo.setBounds(227, 106, 111, 27);
		contentPane.add(loaderCombo);

		JLabel pathLabel = new JLabel("Path");
		pathLabel.setBounds(51, 169, 81, 21);
		contentPane.add(pathLabel);

		pathTextField = new JTextField();
		pathTextField.setBounds(144, 166, 277, 27);
		contentPane.add(pathTextField);
		pathTextField.setColumns(10);

		Button selectPathButton = new Button("...");
		selectPathButton.setBounds(433, 160, 105, 30);
		contentPane.add(selectPathButton);

		Button installButton = new Button("Install");
		installButton.setBounds(233, 209, 105, 30);
		contentPane.add(installButton);
	}
}
