import javax.swing.*;
import java.awt.event.*;

public class shoppingList extends JFrame {
    private JPanel shoppingList;
    private JLabel label;
    private JPanel labelPanel;
    private JTextField enterGrocery;
    private JPanel buttonLabel;
    private JButton enterButton;
    private JList groceryList;
    private JButton resetButton;
    private JSlider sliderNumber;
    private JTextField price;
    private JLabel priceLabel;
    private JLabel groceryLabel;
    private JButton delete;
    private JButton calculate;
    private JLabel euro;
    private JLabel quantity;


    public shoppingList() {
        //default settings
        setContentPane((shoppingList));
        setTitle("List");
        setSize(1000, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);



        //list for shopping list
        DefaultListModel<Items> mod = new DefaultListModel<Items>();

        //put mod into jlist
        groceryList.setModel(mod);

        //enter Button listener
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mod.addElement(new Items(enterGrocery.getText().trim(),Integer.parseInt(price.getText()), sliderNumber.getValue()));
                enterGrocery.setText("");
                price.setText("");
                sliderNumber.setValue(1);

            }
        });


        //reset Button listener
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mod.removeAllElements();

            }
        });

        //remove selected item
        groceryList.setCellRenderer(new ItemRenderer(mod));
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mod.remove(groceryList.getSelectedIndex());

            }
        });

        //calculates the entire shopping list
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = 0;
                for(int i = 0; i < mod.getSize(); ++i){
                    result = result + mod.get(i).price * mod.get(i).number;
                }

                euro.setText(Integer.toString(result) + " €");
            }
        });
    }



    // main
    public static void main(String[] args) {

        shoppingList shoppingList = new shoppingList();

    }
}


