import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Detail {
    JFrame frame = new JFrame();
    Font fontLabel = new Font("FiraCode Nerd Font Mono", Font.PLAIN, 20);
    JPanel[] panel = new JPanel[3];
    JLabel labelFoto = new JLabel();
    JLabel[] label = new JLabel[5];

    ImageIcon changeSize (String lokasiFoto, int ukuranTujuan) {
        ImageIcon test = new ImageIcon(lokasiFoto); 
        double pengurang = (double)ukuranTujuan / (double)test.getIconHeight();
        Image image2 = test.getImage();
        Image gambarbaru = image2.getScaledInstance((int) Math.round(test.getIconWidth() * pengurang), ukuranTujuan, java.awt.Image.SCALE_SMOOTH);
        ImageIcon gambarBuku = new ImageIcon(gambarbaru);
        return gambarBuku;
    }
    
    Detail () {
        frame.setTitle("Tambah Buku");
        frame.setVisible(true);
        // frame.setLayout(new FlowLayout());
        // frame.setSize(700, 460);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for (int i = 0; i < panel.length; i++) {
            panel[i] = new JPanel();
            panel[i].setBackground(new Color(255, 255, 255));
            panel[i].setLayout(new FlowLayout());
        }        

        panel[0].setPreferredSize(new Dimension(700, 460));

        panel[1].setPreferredSize(new Dimension(690, 200));
        panel[1].setBackground(Color.BLUE);
        panel[2].setBackground(Color.BLUE);
        panel[2].setPreferredSize(new Dimension(690, 200));

        for (int i = 0; i < label.length; i++) {
            label[i] = new JLabel();
            label[i].setFont(fontLabel);
        }

        panel[0].add(panel[1]);
        panel[0].add(panel[2]);

        frame.add(panel[0]);

        frame.pack();
    }
}
