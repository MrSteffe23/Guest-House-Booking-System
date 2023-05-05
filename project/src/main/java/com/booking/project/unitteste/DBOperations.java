package com.booking.project.unitteste;

public class DBOperations implements InterfataDBOperations{


    /**
     * This class works with the database. You can return a user from the database here
     * @return A new User
     */
    @Override
    public User getUser() {
        return new User("Andrei", RiskType.LOW);
    }
}
