package interface_adapter.home;

import entity.User;

public class HomeViewState {
    private User user = null;

    public User getUser() {
        return this.user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
