package use_case.change_password;

import entity.User;
import entity.SagmaFactory;

/**
 * The Change Password Interactor.
 */
public class ChangePasswordInteractor implements ChangePasswordInputBoundary {
    private final ChangePasswordUserDataAccessInterface userDataAccessObject;
    private final ChangePasswordOutputBoundary userPresenter;
    private final SagmaFactory sagmaFactory;

    public ChangePasswordInteractor(ChangePasswordUserDataAccessInterface changePasswordDataAccessInterface,
                                    ChangePasswordOutputBoundary changePasswordOutputBoundary,
                                    SagmaFactory sagmaFactory) {
        this.userDataAccessObject = changePasswordDataAccessInterface;
        this.userPresenter = changePasswordOutputBoundary;
        this.sagmaFactory = sagmaFactory;
    }

    @Override
    public void execute(ChangePasswordInputData changePasswordInputData) {
        final User user = sagmaFactory.create(changePasswordInputData.getUsername(),
                                             changePasswordInputData.getPassword());
        userDataAccessObject.changePassword(user);

        final ChangePasswordOutputData changePasswordOutputData = new ChangePasswordOutputData(user.getName(),
                                                                                  false);
        userPresenter.prepareSuccessView(changePasswordOutputData);
    }
}
