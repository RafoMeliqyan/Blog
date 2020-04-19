public interface PostStorageImpl {

    int ADD_POST = 1;
    int SEARCH_POST = 2;
    int POSTS_BY_CATEGORY = 3;
    int ALL_POSTS = 4;
    int LOGOUT = 5;

    int EXIT = 0;
    int LOGIN = 1;
    int REGISTER = 2;

    static void printMainCommands() {
        System.out.println("Import " + EXIT + " for EXIT");
        System.out.println("Import " + LOGIN + " for LOGIN");
        System.out.println("Import " + REGISTER + " for REGISTER");
    }

    static void printUserCommands() {
        System.out.println("Import " + ADD_POST + " for ADD POST");
        System.out.println("Import " + SEARCH_POST + " for SEARCH POST");
        System.out.println("Import " + POSTS_BY_CATEGORY + " for SEARCH POST BY CATEGORY");
        System.out.println("Import " + ALL_POSTS + " for PRINT ALL POSTS");
        System.out.println("Import " + LOGOUT + " for LOGOUT");
    }

}
