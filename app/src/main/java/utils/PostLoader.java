package utils;

import models.Post;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostLoader {
    public List<Post> loadPost() throws FileNotFoundException {
        List<Post> posts = new ArrayList<>();

        File file = new File("postsData.csv");

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            Post post = parsePost(line);
            posts.add(post);
        }

        return posts;
    }

    public Post parsePost(String text) {
        String[] words = text.split(",");

        return new Post(words[0], words[1]);
    }


    public void savePost(List<Post> posts) throws IOException {
        FileWriter fileWriter = new FileWriter("postsData.csv");

        for (Post post : posts) {
            String line = post.toCsvRow();
            fileWriter.write(line + "\n");
        }
        fileWriter.close();
    }
}
