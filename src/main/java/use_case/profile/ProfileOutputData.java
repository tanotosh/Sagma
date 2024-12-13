package use_case.profile;

/**
 * Output Data for the Login Use Case.
 */
public class ProfileOutputData {

    private final String foods;
    private final String matches;

    public ProfileOutputData(String foods, String matches) {
        this.foods = foods;
        this.matches = matches;
    }

    public String getFoods() {
        return foods;
    }
    public String getMatches() {
        return matches;
    }
}
