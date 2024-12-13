package use_case.profile;

import use_case.profile.ProfileInputData;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface ProfileInputBoundary {

    /**
     * Executes the profile use case.
     * @param profileInputData the input data
     */
    void execute(ProfileInputData profileInputData);
}
