package storage;

import exception.PostNotFoundException;
import model.Post;

public class PostStorage<T> {

    private int size = 0;
    private Object[] posts = new Post[16];

    public void add(T post) {
        if (size == posts.length) {
            extend();
        }
        posts[size++] = post;
    }

    private void extend() {
        Object[] tmp = new Post[posts.length + 10];
        System.arraycopy(posts,0,tmp,0,posts.length);
        posts = tmp;
    }

    public T getPostByTitle(String title) throws PostNotFoundException {
        for (int i = 0; i < size; i++) {
            Post t = (Post) posts[i];
            if (title.equals(t.getTitle())) {
                return (T) posts[i];
            } else {
                throw new PostNotFoundException("No post with title " + title);
            }
        }
        return null;
    }

    public T getPostsByKeyword(String keyword) {
        for (int i = 0; i < size; i++) {
            Post t = (Post) posts[i];
            if (t.getTitle().contains(keyword) || t.getText().contains(keyword)) {
                System.out.println((T) posts[i]);
            } else {
                System.out.println("Sorry,no post with keyword " + keyword);
                break;
            }
        }
        return null;
    }

    public void printAllPosts() {
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                System.out.println((T)posts[i]);
            }
        } else {
            System.out.println("Please add post!!!");
        }
    }

    public T printPostsByCategory(String category) {
        for (int i = 0; i < size; i++) {
            Post t = (Post) posts[i];
            if (t.getCategory().equals(category)) {
                System.out.println((T) posts[i]);
            } else {
                System.out.println("No post with category " + category);
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
