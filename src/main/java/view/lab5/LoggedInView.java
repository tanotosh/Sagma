package view.lab5;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.state.LoginSessionState;
import interface_adapter.logout.LogoutController;

/**
 * The View for when the user is logged into the program.
 */
public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final String viewName = "logged in";

    private LogoutController logoutController;

    private final JLabel usernameLabel;
    private final JButton logOut;

    @SuppressWarnings({"checkstyle:LambdaBodyLength", "checkstyle:SuppressWarnings"})
    public LoggedInView() {

        final JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel usernameInfo = new JLabel("Currently logged in: ");
        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel = new JLabel();
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        // Add components to panel
        this.add(title);
        this.add(usernameInfo);
        this.add(usernameLabel);
        this.add(logOut);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        logOut.addActionListener(evt -> {
            if (evt.getSource().equals(logOut)) {
                final LoginSessionState sessionState = LoginSessionState.getInstance();
                if (sessionState.isLoggedIn()) {
                    logoutController.execute(sessionState.getEmail());
                } else {
                    JOptionPane.showMessageDialog(this, "No user is currently logged in.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Update username label with current session data
        updateUsernameDisplay();
    }

    /**
     * Updates the username display with the current session username.
     */
    private void updateUsernameDisplay() {
        final LoginSessionState sessionState = LoginSessionState.getInstance();
        usernameLabel.setText(sessionState.isLoggedIn() ? sessionState.getUsername() : "No user logged in.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateUsernameDisplay();
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }
}
