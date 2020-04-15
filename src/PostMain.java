import exception.PostNotFoundException;
import model.Post;
import storage.PostStorage;

import java.util.Scanner;

public class PostMain implements PostStorageImpl {
    public static PostStorage postStorage = new PostStorage();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            printCommands();
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
            } else if (option.equals("keyword")){
                System.out.println("Import keyword for search");
                String keyName = scanner.nextLine();
                postStorage.getPostsByKeyword(keyName);
            }
        } catch (PostNotFoundException e) {
            System.out.println("No post with this title");
        }

    }

    private static void addPost() {
        System.out.println("Import post data (title,text,category,createdDate)");
        try {
            String postDataStr = scanner.nextLine();
            String[] postData = postDataStr.split(",");
            Post post = new Post();
            post.setTitle(postData[0]);
            post.setText(postData[1]);
            post.setCategory(postData[2]);
            post.setCreatedDate(postData[3]);
            postStorage.add(post);
            System.out.println("Post added!!!");
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Incorrect value! Please try again!");
            addPost();
        }
    }


    private static void printCommands() {
        System.out.println("Import " + EXIT + " for EXIT");
        System.out.println("Import " + ADD_POST + " for ADD POST");
        System.out.println("Import " + SEARCH_POST + " for SEARCH POST");
        System.out.println("Import " + POSTS_BY_CATEGORY + " for SEARCH POST BY CATEGORY");
        System.out.println("Import " + ALL_POSTS + " for PRINT ALL POSTS");
    }
}
