import exception.PostNotFoundException;
import exception.UserNotFoundException;
import model.Post;
import model.User;
import storage.PostStorage;
import storage.UserStorage;

import java.util.Scanner;

public class PostMain implements Commands {
    public static PostStorage postStorage = new PostStorage();
    static Scanner scanner = new Scanner(System.in);
    public static UserStorage userStorage = new UserStorage();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printMainCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please input number");
                command = -1;
            }
            switch (command) {
                case EXIT:
                    System.out.println("Bye!!!");
                    isRun = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Wrong command!!!");
            }
        }
    }

    private static void register() {
        System.out.println("Please input name,surname,email,password");
        try {
            String userStr = scanner.nextLine();
            String[] userData = userStr.split(",");
            User user = new User();
            user.setName(userData[0]);
            user.setSurname(userData[1]);
            user.setEmail(userData[2]);
            user.setPassword(userData[3]);
            userStorage.add(user);
            System.out.println("User added!!!");
        } catch (Exception e) {
            System.out.println("Invalid data!!!");
            register();
        }
    }

    private static void login() {
        if (userStorage.isEmptyUser()) {
            System.out.println("Please register first!!!");
        } else {
            System.out.println("Please input email,password");
            String userStr = scanner.nextLine();
            String[] userData = userStr.split(",");
            try {
                userStorage.getUserByEmailAndPassword(userData[0], userData[1]);
                userLogin();
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void userLogin() {
        System.out.println("Welcome!!!");
        boolean isRun = true;
        while (isRun) {
            Commands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please input number");
                command = -1;
            }
            switch (command) {
                case ADD_POST:
                    addPost();
                    break;
                case SEARCH_POST:
                    searchPost();
                    break;
                case POSTS_BY_CATEGORY:
                    postByCategory();
                    break;
                case ALL_POSTS:
                    postStorage.printAllPosts();
                    break;
                case LOGOUT:
                    isRun = false;
                    break;
                default:
                    System.out.println("Wrong command!!!");
            }
        }
    }

    private static void postByCategory() {
        if (postStorage.isEmpty()) {
            System.out.println("Please add post first!!!");
            return;
        }
        System.out.println("Import category for search post");
        String category = scanner.nextLine();
        postStorage.printPostsByCategory(category);
    }

    private static void searchPost() {
        System.out.println("Import option for search");
        String option = scanner.nextLine();
        try {
            if (option.equals("title")) {
                System.out.println("Import post title");
                String titleName = scanner.nextLine();
                System.out.println(postStorage.getPostByTitle(titleName));
            } else if (option.equals("keyword")) {
                System.out.println("Import keyword for search");
                String keyName = scanner.nextLine();
                postStorage.getPostsByKeyword(keyName);
            }
        } catch (PostNotFoundException e) {
            System.out.println("No post with this title");
        }
    }

    private static void addPost() {
        System.out.println("Input user name");
        String userName = scanner.nextLine();
        try {
            userStorage.getUserByName(userName);
            System.out.println("Import post data (title,text,category,createdDate)");
            try {
                String postDataStr = scanner.nextLine();
                String[] postData = postDataStr.split(",");
                Post post = new Post();
                post.setTitle(postData[0]);
                post.setText(postData[1]);
                post.setCategory(postData[2]);
                post.setCreatedDate(postData[3]);
                post.setUser(userName);
                postStorage.add(post);
                System.out.println("Post added!!!");
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Incorrect value! Please try again!");
                addPost();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
