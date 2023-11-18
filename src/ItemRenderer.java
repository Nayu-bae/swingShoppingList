import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemRenderer extends JPanel implements ListCellRenderer<Items> {
    private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    protected static Border noFocusBorder = DEFAULT_NO_FOCUS_BORDER;

    JLabel name = new JLabel("");
    JLabel price = new JLabel("");
    JLabel quantity = new JLabel("");


    public ItemRenderer(DefaultListModel <Items> mod) {
        setLayout(new FlowLayout(0, 150, 0));
        add(name);
        add(price);
        add(quantity);
    }



    @Override
    public Dimension getMinimumSize() {
        return new Dimension(100, 40);
    }

    @Override
    public Dimension getPreferredSize() {
        return getMinimumSize();
    }


    protected Border getNoFocusBorder() {
        Border border = UIManager.getBorder("List.cellNoFocusBorder");
        if (border != null &&
                (noFocusBorder == null ||
                        noFocusBorder == DEFAULT_NO_FOCUS_BORDER)) {
            return border;
        }
        return noFocusBorder;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Items> list, Items value, int index, boolean isSelected, boolean cellHasFocus) {
        setComponentOrientation(list.getComponentOrientation());

        Color bg = null;
        Color fg = null;

        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

            bg = UIManager.getColor("List.dropCellBackground");
            fg = UIManager.getColor("List.dropCellForeground");

            isSelected = true;
        }


        if (isSelected) {
            setBackground(bg == null ? list.getSelectionBackground() : bg);
            setForeground(fg == null ? list.getSelectionForeground() : fg);
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        name.setText(value.getName());
        price.setText(Integer.toString(value.getPrice()));
        quantity.setText(Integer.toString(value.getNumber()));

        name.setForeground(getForeground());
        price.setForeground(getForeground());
        quantity.setForeground(getForeground());

        setEnabled(list.isEnabled());

        name.setFont(list.getFont());
        price.setFont(list.getFont());
        quantity.setFont(list.getFont());


//        quantity.addActionListener();

        Border border = null;
        if (cellHasFocus) {
            if (isSelected) {
                border = UIManager.getBorder("List.focusSelectedCellHighlightBorder");
            }
            if (border == null) {
                border = UIManager.getBorder("List.focusCellHighlightBorder");
            }
        } else {
            border = getNoFocusBorder();
        }
        setBorder(border);

        return this;
    }


}