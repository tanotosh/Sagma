package use_case.uploadFood;

/**
 * Input Boundary for actions which are related to uploading a food.
 */
public interface UploadFoodInputBoundary {

    /**
     * Executes the upload food use case.
     * @param uploadFoodInputData the input data
     */
    void execute(UploadFoodInputData uploadFoodInputData);

}
