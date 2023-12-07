package org.example;

import com.google.gson.reflect.TypeToken;
import okhttp3.Request;
import okhttp3.Response;
import org.example.dto.comment.CommentDTO;
import org.example.dto.post.PostDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Task2 extends Task1 {
    public List<PostDTO> getUserPosts(int userId) throws IOException {
        Request request = new Request.Builder()
                .url(hostUrl+"/users/"+userId+"/posts")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String body = response.body().string();
            Type listType = new TypeToken<List<PostDTO>>() {}.getType();
            return gson.fromJson(body, listType);
        }
    }

    public List<CommentDTO> getPostComments(int postId) throws IOException {
        Request request = new Request.Builder()
                .url(hostUrl+"/users/"+postId+"/comments")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String body = response.body().string();
            Type listType = new TypeToken<List<CommentDTO>>() {}.getType();
            return gson.fromJson(body, listType);
        }
    }

    public static void writeAllComments(List<CommentDTO> comments, int userId, int postId) throws IOException {
        String fileName = String.format("user-%s-post-%d-comments.json", userId, postId);
        String filePath = "src/main/resources/";
        try (FileWriter writer = new FileWriter(filePath+fileName)) {
            gson.toJson(comments, writer);
        }

        System.out.println("All posts were successfully written.");
    }
}
