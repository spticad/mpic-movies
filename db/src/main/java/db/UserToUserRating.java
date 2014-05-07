package db;

import models.User;

/**
 * Created by Alex on 19/04/14.
 */
public class UserToUserRating implements Comparable<UserToUserRating> {
    private Tuple<User, User> userToUser;
    private double similarity;

    public UserToUserRating(Tuple<User, User> userToUser, double similarity) {
        this.userToUser = userToUser;
        this.similarity = similarity;
    }

    public Tuple<User, User> getUserToUser() {
        return userToUser;
    }

    public void setUserToUser(Tuple<User, User> userToUser) {
        this.userToUser = userToUser;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
    public boolean isInRating(User user){
        return this.userToUser._0.equals(user)||this.userToUser._1.equals(user);
    }

    @Override
    public int compareTo(UserToUserRating o) {
        return (int) (o.getSimilarity() - this.similarity) * 10;
    }
}
