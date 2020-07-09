package cn.enaium.flourmc.installer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
    public FlourInstaller() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemClassLoader().getResource("flour.png")));
        setTitle("Flour|Installer");
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
        gameCombo.setModel(new DefaultComboBoxModel(new String[]{"1.8.9", "1.14.4", "1.15.2"}));
        gameCombo.setBounds(227, 54, 111, 27);
        contentPane.add(gameCombo);

        JLabel loaderLabel = new JLabel("Loader");
        loaderLabel.setBounds(51, 109, 81, 21);
        contentPane.add(loaderLabel);

        JComboBox loaderCombo = new JComboBox();
        loaderCombo.setModel(new DefaultComboBoxModel(new String[]{"1.0.0"}));
        loaderCombo.setBounds(227, 106, 111, 27);
        contentPane.add(loaderCombo);

        JLabel pathLabel = new JLabel("Path");
        pathLabel.setBounds(51, 169, 81, 21);
        contentPane.add(pathLabel);

        pathTextField = new JTextField();
        pathTextField.setText(getMinecraftFolder().toString());
        pathTextField.setBounds(144, 166, 277, 27);
        contentPane.add(pathTextField);
        pathTextField.setColumns(10);

        Button selectPathButton = new Button("...");
        selectPathButton.setBounds(433, 160, 105, 30);
        selectPathButton.addActionListener((actionEvent) -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String path = jFileChooser.getSelectedFile().getAbsolutePath();
                if (path.endsWith(".minecraft")) {
                    pathTextField.setText(path);
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong path", "Wrong path", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(selectPathButton);

        Button installButton = new Button("Install");
        installButton.setBounds(233, 209, 105, 30);
        installButton.addActionListener((actionEvent) -> {
            StringBuilder stringBuilder = new StringBuilder();
            Scanner scanner = new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream("profile.json"));
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.next());
            }
            String json = stringBuilder.toString();
            json = json.replace("${mcVersion}", gameCombo.getSelectedItem().toString());
            json = json.replace("${formattedTime}", new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ").format(new Date()));
            json = json.replace("${loaderVersion}", loaderCombo.getSelectedItem().toString());
            String dir = "flour-" + gameCombo.getSelectedItem() + "-" + loaderCombo.getSelectedItem();
            File path = new File(pathTextField.getText(), "/versions/" + dir);
            path.mkdirs();
            try {
                Files.write(new File(path + "/" + dir + ".json").toPath(), json.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        contentPane.add(installButton);
    }

    private File getMinecraftFolder() {
        File minecraftFolder;
        if (getOsName().contains("win")) {
            minecraftFolder = new File(System.getenv("APPDATA") + "/.minecraft");
        } else if (getOsName().contains("mac")) {
            minecraftFolder = new File(System.getProperty("user.home") + "/Library/Application Support/minecraft");
        } else {
            minecraftFolder = new File(System.getProperty("user.home") + "/.minecraft");
        }
        return minecraftFolder;
    }

    private String getOsName() {
        return System.getProperty("os.name").toLowerCase(Locale.ROOT);
    }
}
