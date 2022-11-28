import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;


public class TableIcon extends JFrame
{
    public TableIcon()
    {
        JFrame frame = new JFrame();
        frame.setTitle("Judul");
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());
        String[] colom = {"nim", "Nama"};
        String[][] output = {{"11S21010", "Gabriel"}};
        // DefaultTableModel tableModel = new DefaultTableModel(output, colom);
        // tableModel.
        JTable tabel = new JTable(output, colom);
        tabel.setPreferredSize(new Dimension(300, 300));
        // tabel.setModel(tableModel);
        JScrollPane scroll = new JScrollPane(tabel);
        frame.add(scroll);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}