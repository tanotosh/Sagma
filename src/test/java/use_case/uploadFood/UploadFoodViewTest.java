package use_case.uploadFood;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.uploadFood.UploadFoodController;
import interface_adapter.uploadFood.UploadFoodPresenter;
import view.HomeView;
import view.UploadFoodView;

import javax.swing.*;
import java.util.Arrays;

public class UploadFoodViewTest {
    public static void main(String[] args) {
        // Set the Look and Feel to the system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a test User object
        User gaia = new User(2, "Gaia", "gaia@email.com", "password", 5.0f, 5, Arrays.asList("vegan"), null);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeViewModel homeView = new HomeViewModel();

        // Create the necessary layers
        UploadFoodPresenter uploadFoodPresenter = new UploadFoodPresenter(viewManagerModel, homeView) {
            @Override
            public void switchToHomeView() {
                // Mock transition to HomeView
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame("Home View");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(1000, 600);
                    frame.add(new HomeView());
                    frame.setVisible(true);
                });
            }
        };
        UploadFoodInteractor uploadFoodInteractor = new UploadFoodInteractor(uploadFoodPresenter);

        // Controller
        UploadFoodController uploadFoodController = new UploadFoodController(uploadFoodInteractor); // Temporary null for the view

        // View
        UploadFoodView uploadFoodView = new UploadFoodView(gaia, uploadFoodController);

        // Link controller and view
        uploadFoodController = new UploadFoodController(uploadFoodInteractor);

        // Set up the JFrame for visual testing
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Upload Food View Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.add(uploadFoodView);
            frame.setVisible(true);
        });

        System.out.println("Please interact with the Upload Food View and verify functionality.");
    }
}
