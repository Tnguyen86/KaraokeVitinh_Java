package thanh;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SongList extends JFrame implements ActionListener {
    public static JTable table;

    public SongList() {
        super("");
        setSize(new Dimension(640, 480));
        setTitle("Song List");
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                 UnsupportedLookAndFeelException e) {
            System.err.println(e.getCause());
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JPanel center = new JPanel();
        JPanel BottomPanel = new JPanel();
        BottomPanel.setLayout(new BorderLayout());

        JPanel BottomPanela = new JPanel();

        JButton buttonAdd = new JButton();
        buttonAdd.setText("+");
        buttonAdd.setPreferredSize(new Dimension(50, 35));

        JButton buttonRemove = new JButton();
        buttonRemove.setText("-");
        buttonRemove.setPreferredSize(new Dimension(50, 35));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25));
        JLabel search = new JLabel("Search : ");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPanel.add(search, gbc);
        gbc.gridx++;
        //gbc.gridy++;
        searchPanel.add(textField, gbc);


        BottomPanela.add(buttonAdd);
        BottomPanela.add(buttonRemove);
        BottomPanela.add(searchPanel);
        //JTextField textField = new JTextField();
        BottomPanel.add(BottomPanela, BorderLayout.WEST);

        Object[] header = {"#", ""};
        DefaultTableModel tablemodel = new DefaultTableModel(header, 0) {
            private static final long serialVersionUID = 1L;

            // TODO i set column 1 description editable
            // return false is noneditable
            @Override
            public boolean isCellEditable(int row, int column) {
                // make read only fields except column 0,13,14
                //return column == 1 || column == 2;// || column == 6;
                // || column == 2 || column == 3 || column == 4;
                // return column == 1 || column == 10;
                return false;
            }

            // TODO i declare column as below
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    /*case 2:
                        return Integer.class;
                    case 3:
                        return Double.class;*/
                    default:
                        return String.class;
                }
            }
        };

        //String format = String.format("%07d", 0);
        //String result = String.format(format, num);

        for (int i = 1; i <= 10000; i++) {
            String format = String.format("%06d", i);
            //String result = String.format(format, i);
            tablemodel.addRow(new Object[]{String.format(format, i), " - Trọn Kiếp Bình Yên - 123456"});
        }


        // TODO TABLE WITH COLOR
        table = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(240, 240, 240);
                Color whiteColor = Color.WHITE;
                if (!comp.getBackground().equals(getSelectionBackground())) {
                    Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
                    comp.setBackground(bg);
                }
                ((JLabel) comp).setHorizontalAlignment(JLabel.LEFT);
                return comp;
            }
        };


        table.setAutoCreateRowSorter(true);
        table.setRowHeight(25);
        table.setFont(new Font("Serif", Font.BOLD, 20));
        table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 10));
        table.setFillsViewportHeight(true);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        table.getTableHeader().setToolTipText("TEST TEST");
        table.setModel(tablemodel);

        setTableRowsSize(table);

        //center.add(table);
        JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //scroll.setLayout(layout);
        scroll.setPreferredSize(new Dimension(640, 480));

        container.add(scroll, BorderLayout.CENTER);
        container.add(BottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        final JFrame SongList = new SongList();
        SongList.setVisible(true);
        SongList.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void setTableRowsSize(JTable table) {
        TableColumn column = null;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            switch (i) {
                // no
                case 0:
                    column.setPreferredWidth(65);
                    break;
                case 1:
                    column.setPreferredWidth(500);
                    break;
                /*case 2:
                    column.setPreferredWidth(50);
                    break;
                case 3:
                    column.setPreferredWidth(75);
                    break;*/
                default:
                    column.setPreferredWidth(65);
            }
        }
        table.revalidate();
        table.repaint();
    }
}
