package storage;

import exception.PostNotFoundException;
import model.Post;

public class PostStorage {

    private int size = 0;
    private Post[] posts = new Post[10];

    public void add(Post post) {
        if (size == posts.length) {
            extend();
        }
        posts[size++] = post;
    }

    private void extend() {
        Post[] tmp = new Post[posts.length + 10];
        System.arraycopy(posts,0,tmp,0,posts.length);
        posts = tmp;
    }

    public Post getPostByTitle(String title) throws PostNotFoundException {
        for (int i = 0; i < size; i++) {
            if (title.equals(posts[i].getTitle())) {
                return posts[i];
            } else {
                throw new PostNotFoundException("No post with title " + title);
            }
        }
        return null;
    }

    public void getPostsByKeyword(String keyword) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().contains(keyword) || posts[i].getText().contains(keyword)) {
                System.out.println(posts[i]);
            } else {
                System.out.println("Sorry,no post with keyword " + keyword);
                break;
            }
        }
    }

    public void printAllPosts() {
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                System.out.println(posts[i]);
            }
        } else {
            System.out.println("Please add post!!!");
        }
    }

    public void printPostsByCategory(String category) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getCategory().equals(category)) {
                System.out.println(posts[i]);
            } else {
                System.out.println("No post with category " + category);
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
