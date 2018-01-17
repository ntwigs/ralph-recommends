package model;

import util.Filehandler;

import java.io.IOException;
import java.util.HashMap;

public class UserDB {
    private HashMap<Integer, User> users;

    public UserDB() {
        this.users = new HashMap<>();
    }

    public void storeUsers() throws IOException {
        Filehandler fh = new Filehandler();
        this.users = fh.getUsers();
    }

    public HashMap<Integer, User> getUsers() {
        return this.users;
    }

    public User getUser(int id) {
        return this.users.get(id);
    }
}
