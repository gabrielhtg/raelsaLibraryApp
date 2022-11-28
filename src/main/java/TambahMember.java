import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TambahMember {

    void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    boolean tambahMember (String nim, String nama, String prodi, String angkatan, String foto) {
        String sql = String.format("insert into member(nim, nama, prodi, angkatan, foto) values ('%s', '%s', '%s', '%s', '%s')", nim.toUpperCase(), nama, prodi, angkatan, foto);

        try {
            Database dataRaelsa = new Database();
            dataRaelsa.perintah.executeUpdate(sql);
            dataRaelsa.koneksi.close();
            return true;
        } catch (SQLException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    TambahMember () {
        final JFrame frame = new JFrame();
        frame.setTitle("Tambah Member");
        frame.setVisible(true);

        Font fontInputField = new Font("FiraCode Nerd Font Mono", Font.PLAIN, 20);

        Font fontPlaceholder = new Font("Arial", Font.PLAIN, 15);

        final JTextField fieldNim = new JTextField();
        fieldNim.requestFocusInWindow();
        fieldNim.setPreferredSize(new Dimension(650, 50));
        fieldNim.setFont(fontInputField);
        frame.add(fieldNim);
        fieldNim.setLayout(new BorderLayout());

        final JLabel placeholderNim = new JLabel();
        placeholderNim.setText("NIM Member");
        placeholderNim.setFont(fontPlaceholder);
        placeholderNim.setForeground(new Color(166, 166, 166));
        fieldNim.add(placeholderNim, BorderLayout.WEST);

        final JTextField fieldNama = new JTextField();
        fieldNama.setLayout(new BorderLayout());
        fieldNama.setPreferredSize(new Dimension(650, 50));
        fieldNama.setFont(fontInputField);
        frame.add(fieldNama);

        final JLabel placeholderNama = new JLabel();
        placeholderNama.setText("Nama Member");
        placeholderNama.setFont(fontPlaceholder);
        placeholderNama.setForeground(new Color(166, 166, 166));
        fieldNama.add(placeholderNama, BorderLayout.WEST);

        final JTextField fieldProdi = new JTextField();
        fieldProdi.setLayout(new BorderLayout());
        fieldProdi.setPreferredSize(new Dimension(650, 50));
        fieldProdi.setFont(fontInputField);
        frame.add(fieldProdi);

        final JLabel placeholderProdi = new JLabel();
        placeholderProdi.setText("Prodi Member");
        placeholderProdi.setFont(fontPlaceholder);
        placeholderProdi.setForeground(new Color(166, 166, 166));
        fieldProdi.add(placeholderProdi, BorderLayout.WEST);

        final JTextField fieldAngkatan = new JTextField();
        fieldAngkatan.setLayout(new BorderLayout());
        fieldAngkatan.setPreferredSize(new Dimension(650, 50));
        fieldAngkatan.setFont(fontInputField);
        frame.add(fieldAngkatan);

        final JLabel placeholderAngkatan = new JLabel();
        placeholderAngkatan.setText("Angkatan");
        placeholderAngkatan.setFont(fontPlaceholder);
        placeholderAngkatan.setForeground(new Color(166, 166, 166));
        fieldAngkatan.add(placeholderAngkatan, BorderLayout.WEST);

        final JTextField fieldFoto = new JTextField();
        fieldFoto.setLayout(new BorderLayout());
        fieldFoto.setPreferredSize(new Dimension(500, 50));
        fieldFoto.setFont(fontInputField);
        frame.add(fieldFoto);

        final JButton tombolFoto = new JButton();
        tombolFoto.setFont(new Font("Arial", Font.PLAIN, 20));
        tombolFoto.setText("Pilih Foto");
        tombolFoto.setPreferredSize(new Dimension(145, 50));
        frame.add(tombolFoto);
        tombolFoto.setFocusable(false);

        final JFileChooser pilihFoto = new JFileChooser();

        tombolFoto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                pilihFoto.showOpenDialog(pilihFoto);
                pilihFoto.setFileSelectionMode(JFileChooser.FILES_ONLY);
                String[] namaFoto = String.valueOf(pilihFoto.getSelectedFile()).split("\\\\");
                fieldFoto.setText(namaFoto[namaFoto.length - 1]);
            }
            
        });

        final JLabel placeholderFoto = new JLabel();
        placeholderFoto.setText("Lokasi Foto");
        placeholderFoto.setFont(fontPlaceholder);
        placeholderFoto.setForeground(new Color(166, 166, 166));
        fieldFoto.add(placeholderFoto, BorderLayout.WEST);

        JPanel panelBawah1 = new JPanel(new FlowLayout());
        panelBawah1.setPreferredSize(new Dimension(650, 25));
        frame.add(panelBawah1);
        
        final JButton tombolOke = new JButton();
        tombolOke.setBackground(new Color(50, 94, 190));
        tombolOke.setForeground(Color.white);
        tombolOke.setPreferredSize(new Dimension(200, 50));
        tombolOke.setFocusable(false);
        tombolOke.setText("Done");
        tombolOke.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(tombolOke);

        JPanel panelBawah2 = new JPanel(new FlowLayout());
        panelBawah2.setPreferredSize(new Dimension(650, 50));
        frame.add(panelBawah2);

        final JLabel berhasilTidak = new JLabel();
        berhasilTidak.setFont(new Font("Arial", Font.BOLD, 20));
        panelBawah2.add(berhasilTidak);

        frame.setLayout(new FlowLayout());
        frame.setSize(700, 460);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tombolOke.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                File source = new File(String.valueOf(pilihFoto.getSelectedFile()));

                    String[] temp = String.valueOf(pilihFoto.getSelectedFile()).split("\\\\");
                    File destination = new File("source\\" + fieldNim.getText() + "." + temp[temp.length - 1].split("\\.")[1]);

                    if (tambahMember(fieldNim.getText(), fieldNama.getText(), fieldProdi.getText(), fieldAngkatan.getText(), "source\\\\" + fieldNim.getText() + "." + temp[temp.length - 1].split("\\.")[1])) {
                        berhasilTidak.setForeground(new Color(111, 144, 84));
                        berhasilTidak.setText("Berhasil Menambahkan Member Baru");
                    }

                    else {
                        berhasilTidak.setForeground(new Color(243, 69, 70));
                        berhasilTidak.setText("Gagal Menambahkan Member Baru");
                    }

                    try {
                        copyFileUsingStream(source, destination);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    fieldNim.setText("");
                    fieldNama.setText("");
                    fieldProdi.setText("");
                    fieldAngkatan.setText("");
                    fieldFoto.setText("");
            }
            
        });


        fieldNim.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    fieldNama.requestFocusInWindow();
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });

        fieldNama.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    fieldProdi.requestFocusInWindow();
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });

        fieldProdi.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    fieldAngkatan.requestFocusInWindow();
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });

        fieldAngkatan.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        tombolFoto.doClick();
                        fieldFoto.requestFocusInWindow();
                    }
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });

        fieldFoto.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    tombolOke.doClick();
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });

        fieldNim.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            private void warn() {
                placeholderNim.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {
                if (fieldNim.getText().equals("")) {
                    placeholderNim.setVisible(true);
                }
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }
        });

        fieldNama.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            private void warn() {
                placeholderNama.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {
                if (fieldNama.getText().equals("")) {
                    placeholderNama.setVisible(true);
                }
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }
        });


        fieldAngkatan.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            private void warn() {
                placeholderAngkatan.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {
                if (fieldAngkatan.getText().equals("")) {
                    placeholderAngkatan.setVisible(true);
                }
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }
        });

        fieldProdi.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            private void warn() {
                placeholderProdi.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {
                if (fieldProdi.getText().equals("")) {
                    placeholderProdi.setVisible(true);
                }
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }
        });

        fieldFoto.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            private void warn() {
                placeholderFoto.setVisible(false);
            }
            public void removeUpdate(DocumentEvent e) {
                if (fieldFoto.getText().equals("")) {
                    placeholderFoto.setVisible(true);
                }
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }
        });

    }
}
