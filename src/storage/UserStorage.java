package storage;

import exception.UserNotFoundException;
import model.User;

public class UserStorage {
    private User[] users = new User[16];
    private int size = 0;

    public void add(User user) {
        if (size == users.length) {
            extend();
        }
        users[size++] = user;
    }

    private void extend() {
        User[] tmp = new User[users.length + 10];
        System.arraycopy(users, 0, tmp, 0, users.length);
        users = tmp;
    }

    public void printAllUsers() {
        for (int i = 0; i < size; i++) {
            System.out.println(users[i]);
        }
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email)) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with " + email + " does not exist");
    }

    public User getUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPassword().equals(password)) {
                return users[i];
            } else {
                System.out.println("Wrong email or password!!!");
            }
        }
        throw new UserNotFoundException("User with email " + email + " and password " + password + " does not exist");
    }

    public User getUserByName(String name) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getName().equals(name)) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with name " + name + " does not exist");
    }

    public boolean isEmptyUser() {
        return size == 0;
    }
}
