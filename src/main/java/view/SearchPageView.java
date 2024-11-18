package view;

import javax.swing.*;

/**
 * The View for the Search Page
 */

public class SearchPageView {
    public static void main(String[] args){
        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("What are you looking for today?");
        title.setVisible(true);
        titlePanel.add(title, BorderLayout.CENTER);

        JPanel dropdownPanel = new JPanel(new BorderLayout());
        String choices = {"Korean", "Chinese", "Japanese", "Fast Food"}; //choices should not be there, should show depends on the foods available
        JComboBox<String> options = new JComboBox<String>(choices);
        options.setVisible(true);
        dropdownPanel.add(options, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        JButton button = new JButton("search");
        button.setVisible(true);
        buttonPanel.add(button, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //go to swiping page and run function serrch (include filtering)
            }
        });

        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
        mainpanel.add(titlePanel);
        mainpanel.add(dropdownPanel);
        mainpanel.add(buttonPanel);

        JFrame frame = new JFrame("Search Page");
        frame.setSize(500, 400);
        frame.setContentPane(mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}