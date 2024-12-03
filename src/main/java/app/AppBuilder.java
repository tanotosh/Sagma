//package app;
//
//import java.awt.CardLayout;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.WindowConstants;
//
//import data_access.UserDAO;
//import interface_adapter.ViewManagerModel;
//import interface_adapter.login.LoginController;
//import interface_adapter.login.LoginPresenter;
//import interface_adapter.login.LoginViewModel;
//import interface_adapter.logout.LogoutController;
//import interface_adapter.logout.LogoutPresenter;
//import interface_adapter.signup.SignupController;
//import interface_adapter.signup.SignupPresenter;
//import interface_adapter.signup.SignupViewModel;
//import use_case.login.LoginInputBoundary;
//import use_case.login.LoginInteractor;
//import use_case.login.LoginOutputBoundary;
//import use_case.logout.LogoutInputBoundary;
//import use_case.logout.LogoutInteractor;
//import use_case.logout.LogoutOutputBoundary;
//import use_case.signup.SignupInputBoundary;
//import use_case.signup.SignupInteractor;
//import use_case.signup.SignupOutputBoundary;
//import view.LoggedInView;
//import view.LoginView;
//import view.SignupView;
//import view.ViewManager;
// import entity.SagmaFactory;
//
///**
// * The AppBuilder class is responsible for putting together the pieces of
// * our CA architecture; piece by piece.
// * <p/>
// * This is done by adding each View and then adding related Use Cases.
// */
//// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
////                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
////                  your team to think about ways to refactor the code to resolve these
////                  if your team decides to work with this as your starter code
////                  for your final project this term.
//public class AppBuilder {
//    private final JPanel cardPanel = new JPanel();
//    private final CardLayout cardLayout = new CardLayout();
//    // thought question: is the hard dependency below a problem?
//    // private final SagmaFactory sagmaFactory = new SagmaFactory();
//    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
//    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);
//
//    // thought question: is the hard dependency below a problem?
//    // private final UserDAO userDAO = new UserDAO();
//
//    private SignupView signupView;
//    private SignupViewModel signupViewModel;
//    private LoginViewModel loginViewModel;
//    private LoggedInView loggedInView;
//    private LoginView loginView;
//
//    public AppBuilder() {
//        cardPanel.setLayout(cardLayout);
//    }
//
//    /**
//     * Adds the Signup View to the application.
//     * @return this builder
//     */
//    public AppBuilder addSignupView() {
//        signupViewModel = new SignupViewModel();
//        signupView = new SignupView(signupViewModel);
//        cardPanel.add(signupView, signupView.getViewName());
//        return this;
//    }
//
//    /**
//     * Adds the Login View to the application.
//     * @return this builder
//     */
//    public AppBuilder addLoginView() {
//        loginViewModel = new LoginViewModel();
//        loginView = new LoginView(loginViewModel);
//        cardPanel.add(loginView, loginView.getViewName());
//        return this;
//    }
//
//    /**
//     * Adds the LoggedIn View to the application.
//     * @return this builder
//     */
//    public AppBuilder addLoggedInView() {
//        loggedInView = new LoggedInView();
//        cardPanel.add(loggedInView, loggedInView.getViewName());
//        return this;
//    }
//
//    /**
//     * Adds the Signup Use Case to the application.
//     * @return this builder
//     */
//    public AppBuilder addSignupUseCase() {
//        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
//                signupViewModel, loginViewModel);
//        final SignupInputBoundary userSignupInteractor = new SignupInteractor(signupOutputBoundary);
//
//        final SignupController controller = new SignupController(userSignupInteractor);
//        signupView.setSignupController(controller);
//        return this;
//    }
//
//    /**
//     * Adds the Login Use Case to the application.
//     * @return this builder
//     */
//    public AppBuilder addLoginUseCase() {
//        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loginViewModel);
//        final LoginInputBoundary loginInteractor = new LoginInteractor(loginOutputBoundary);
//
//        final LoginController loginController = new LoginController(loginInteractor);
//        loginView.setLoginController(loginController);
//        return this;
//    }
//
//    /**
//     * Adds the Logout Use Case to the application.
//     * @return this builder
//     */
//    public AppBuilder addLogoutUseCase() {
//        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel, loginViewModel);
//
//        final LogoutInputBoundary logoutInteractor = new LogoutInteractor(logoutOutputBoundary);
//
//        final LogoutController logoutController = new LogoutController(logoutInteractor);
//        loggedInView.setLogoutController(logoutController);
//        return this;
//    }
//
//    /**
//     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
//     * @return the application
//     */
//    public JFrame build() {
//        final JFrame application = new JFrame("Login Example");
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        application.add(cardPanel);
//
//        viewManagerModel.setState(signupView.getViewName());
//        viewManagerModel.firePropertyChanged();
//
//        return application;
//    }
//}
