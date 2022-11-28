import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDisplay {
    private JPanel panelSampingTombolLogin = new JPanel();
    private JLabel labelGagal = new JLabel();
    private String nama;
    private String foto;
    
    boolean cekUSer (String username, String password) {
        String sql = "select * from librarian where username = '" + username + "'" + " collate utf8mb4_bin";
        
        try {
            Database databaseRaelsa = new Database();
            ResultSet result = databaseRaelsa.perintah.executeQuery(sql);
            result.next();
            
            if (password.equals(result.getString("pass"))) {
                nama = result.getString("nama");
                foto = result.getString("foto");
                databaseRaelsa.koneksi.close();
                return true;
            }

            else {
                databaseRaelsa.koneksi.close();
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    LoginDisplay () {
        ImageIcon logoFrame = new ImageIcon("logo.png");

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(logoFrame.getImage());
        frame.setResizable(false);

        Font fontJudul = new Font("Exo 2", Font.PLAIN, 50);
        Font fontInputField = new Font("FiraCode Nerd Font Mono", Font.PLAIN, 20);
        
        JPanel panelUtama = new JPanel();
        panelUtama.setPreferredSize(new Dimension(470, 510));
        panelUtama.setBackground(new Color(255, 255, 255));
        frame.add(panelUtama);

        JPanel panelBatasAtas = new JPanel();
        panelBatasAtas.setPreferredSize(new Dimension(500, 20));
        panelBatasAtas.setBackground(new Color(255, 255, 255));

        JPanel panelBatasTengah = new JPanel();
        panelBatasTengah.setPreferredSize(new Dimension(500, 25));
        panelBatasTengah.setBackground(new Color(255, 255, 255));

        JLabel labelLogoRaelsa = new JLabel();
        labelLogoRaelsa.setText("📕");
        labelLogoRaelsa.setFont(new Font("FiraCode", Font.PLAIN, 140));
        labelLogoRaelsa.setForeground(new Color(50, 94, 190));

        JPanel panelSampingLogo = new JPanel();
        panelSampingLogo.setSize(189, 150);
        panelSampingLogo.setBackground(new Color(255, 255, 255));
        panelSampingLogo.setLayout(new GridLayout(2, 1));

        JLabel labelJudul1 = new JLabel("Raelsa", JLabel.CENTER);
        labelJudul1.setFont(fontJudul);

        JLabel labelJudul2 = new JLabel("Library", JLabel.CENTER);
        labelJudul2.setFont(fontJudul);

        panelSampingLogo.add(labelJudul1);
        panelSampingLogo.add(labelJudul2);

        JPanel panelInput = new JPanel();
        panelInput.setBackground(new Color(255, 255, 255));
        panelInput.setLayout(new FlowLayout());
        panelInput.setPreferredSize(new Dimension(400, 110));

        final JTextField fieldUsername = new JTextField();
        fieldUsername.setPreferredSize(new Dimension(400, 50));
        fieldUsername.setFont(fontInputField);
        fieldUsername.setLayout(new BorderLayout());

        ImageIcon logoKosong = new ImageIcon("kosong.png");

        final JCheckBox hideShowPassword = new JCheckBox();
        hideShowPassword.setFocusable(false);
        hideShowPassword.setIcon(logoKosong);
        hideShowPassword.setOpaque(false);
        hideShowPassword.setText("🔒");
        hideShowPassword.setFont(new Font("FiraCode", Font.BOLD, 15));
        hideShowPassword.setBackground(new Color(255, 255, 255));

        Font fontPlaceholder = new Font("Arial", Font.PLAIN, 15);

        final JLabel placeholderUsername = new JLabel();
        placeholderUsername.setText("Username");
        placeholderUsername.setFont(fontPlaceholder);
        placeholderUsername.setForeground(new Color(166, 166, 166));

        final JLabel placeholderPassword = new JLabel();
        placeholderPassword.setFont(fontPlaceholder);
        placeholderPassword.setText("Password");
        placeholderPassword.setForeground(new Color(166, 166, 166));

        final JPasswordField fieldPassword = new JPasswordField();
        fieldPassword.setPreferredSize(new Dimension(400, 50));
        fieldPassword.setFont(fontInputField);
        fieldPassword.setLayout(new BorderLayout());
        fieldPassword.add(hideShowPassword, BorderLayout.EAST);
        fieldPassword.add(placeholderPassword, BorderLayout.WEST);
        fieldUsername.add(placeholderUsername, BorderLayout.WEST);

        final JButton tombolLogin = new JButton();
        tombolLogin.setBackground(new Color(50, 94, 190));
        tombolLogin.setPreferredSize(new Dimension(400, 50));
        tombolLogin.setForeground(new Color(255, 255, 255));
        tombolLogin.setText("Login");
        tombolLogin.setFont(new Font("Arial", Font.BOLD, 15));
        tombolLogin.setFocusable(false);

        panelSampingTombolLogin.setBackground(new Color(255, 255, 255));
        panelSampingTombolLogin.setLayout(new FlowLayout());
        panelSampingTombolLogin.setPreferredSize(new Dimension(263, 30));

        labelGagal.setForeground(Color.RED);
        labelGagal.setFont(new Font("Arial", Font.PLAIN, 20));
        panelSampingTombolLogin.add(labelGagal);

        panelInput.add(fieldUsername);
        panelInput.add(fieldPassword);

        panelUtama.add(panelBatasAtas);
        panelUtama.add(labelLogoRaelsa);
        panelUtama.add(panelSampingLogo);
        panelUtama.add(panelBatasTengah);
        panelUtama.add(panelInput);
        panelUtama.add(panelSampingTombolLogin);
        panelUtama.add(tombolLogin);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        fieldUsername.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            private void warn() {
                placeholderUsername.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {
                if (fieldUsername.getText().equals("")) {
                    placeholderUsername.setVisible(true);
                }
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }
          });

        fieldPassword.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            private void warn() {
                placeholderPassword.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {
                if (String.valueOf(fieldPassword.getPassword()).equals("")) {
                    placeholderPassword.setVisible(true);
                }
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }
        });

        fieldUsername.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                labelGagal.setText("");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    fieldPassword.requestFocusInWindow();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });

        fieldPassword.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tombolLogin.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });

        hideShowPassword.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                if (hideShowPassword.isSelected()) {
                    hideShowPassword.setText("🔓");
                    fieldPassword.setEchoChar('\u0000');
                }

                else {
                    hideShowPassword.setText("🔒");
                    fieldPassword.setEchoChar((Character) UIManager.get("PasswordField.echoChar"));
                }
            }
        });

        tombolLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == tombolLogin) {
                    if (cekUSer(fieldUsername.getText(), foto)) {
                        new HomeDisplay(nama, foto);
                        frame.dispose();
                    }

                    else {
                        labelGagal.setText("User tidak ditemukan!");
                        fieldUsername.setText("");
                        fieldPassword.setText("");
                        fieldUsername.requestFocusInWindow();
                    }
                }
            }
            
        });
    }
}
