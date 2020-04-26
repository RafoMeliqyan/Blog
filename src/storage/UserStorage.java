package storage;

import exception.UserNotFoundException;

import model.User;


public class UserStorage<T> {
    private int size = 0;
    Object[] array;

    public UserStorage(int length) {
        array = new Object[length];
    }

    public UserStorage() {
        array = new Object[16];
    }

    public void add(T value) throws NullPointerException{
        if (size == array.length) {
            extend();
        }
        array[size++] = value;
    }

    private void extend() {
        Object[] tmp = new User[array.length + 10];
        System.arraycopy(array,0,tmp,0,array.length);
        array = tmp;
    }

    public void printAllUsers() {
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                System.out.println(array[i]);
            }
        } else {
            System.out.println("Please add post!!!");
        }
    }

    public T getUserByEmail(String email) throws UserNotFoundException {
        for (int i = 0; i < array.length; i++) {
            User t = (User) array[i];
            if (t.getEmail().equals(email)) {
                return (T) array[i];
            }
        }
        throw new UserNotFoundException("User with " + email + " does not exist");
    }

    public T getUserByEmailAndPassword(String email, String password) throws UserNotFoundException {
        for (int i = 0; i < array.length; i++) {
            User t = (User) array[i];
            if (t.getEmail().equals(email) && t.getPassword().equals(password)) {
                return (T) array[i];
            } else {
                System.out.println("Wrong email or password!!!");
            }
        }
        throw new UserNotFoundException("User with email " + email + " and password " + password + " does not exist");
    }

    public T getUserByName(String name) throws UserNotFoundException {
        for (int i = 0; i < array.length; i++) {
            User t = (User) array[i];
            if (t.getName().equals(name)) {
                return (T) array[i];
            }
        }
        throw new UserNotFoundException("User with name " + name + " does not exist");
    }


    public boolean isEmptyUser() {
        return size == 0;
    }
}
