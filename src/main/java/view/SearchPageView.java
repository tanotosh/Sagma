package view;

import entity.User;
import entity.Food;
import interface_adapter.search.SearchController;
import interface_adapter.state.SearchSessionState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 * The View for the Search Page
 */

public class SearchPageView extends JPanel{
    //instantiated a current user here, since previous page wasn't fully implemented to send User data here.
    private User currentUser;
    private static java.util.List<Food> filteredFoods = null;
    private SearchController searchController;

    public SearchPageView(){
        Color green = new Color(164, 179, 148);
        Color brown = new Color(123, 86, 61);
        Color pink = new Color(234, 223, 214);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(164, 179,148));
        GridBagConstraints c = new GridBagConstraints();

        setBackground(green);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //title mainPanel
        JLabel title = new JLabel("What are you looking for?");
        title.setForeground(new Color(40, 54, 24));
        title.setFont(new Font("Helvetica", Font.BOLD, 15));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        mainPanel.add(title, c);

        //dropdown mainPanel
        java.util.List<String> choices = new ArrayList<>();
        choices.add("Korean");
        choices.add("Japanese");
        choices.add("Italian");
        JComboBox<String> options = new JComboBox<String>(new Vector<>(choices));
        options.setFont(new Font("Arial", Font.PLAIN, 13));
        options.setBackground(new Color(234, 223, 214));
        options.setForeground(new Color(123,86,61));
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10, 10, 5, 10);
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 50;
        mainPanel.add(options, c);

        //button mainPanel
        JButton button = new JButton("search");
        button.setFont(new Font("Arial", Font.PLAIN, 10));
        button.setBackground(new Color(123,86,61));
        button.setForeground(new Color(234,223,214));
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(5, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 20;
        mainPanel.add(button, c);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cuisine = options.getSelectedItem().toString();

                searchController.execute(currentUser,  cuisine);

                //since next page (swiping page) uses the global variable declared here, i need to set the value here.
                SearchSessionState sessionState = SearchSessionState.getInstance();
                filteredFoods = sessionState.getFoods();

//                Container parent = SearchPageView.this.getParent();
//                if (parent != null) {
//                    CardLayout cl = (CardLayout) parent.getLayout();
//                    cl.show(parent, "SWIPE");
//                }
            }
        });

        add(mainPanel);
    }

    public static java.util.List<Food> getFilteredFoods() {
        return filteredFoods;
    }
}







