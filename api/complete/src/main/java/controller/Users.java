package controller;

import model.UserDB;

import java.io.IOException;
import java.util.HashSet;

public class Users {
    private UserDB udb;

    public Users() {
        udb = new UserDB();
    }

    public HashSet<Integer> getUsers() throws IOException {
        HashSet<Integer> userIds = new HashSet<>();
        udb.storeUsers();
        udb.getUsers().forEach((id, u) -> {
           userIds.add(id);
        });

        return userIds;
    }
}
