package org.example;

import com.google.gson.reflect.TypeToken;
import okhttp3.Request;
import okhttp3.Response;
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

    public void writeAllPosts(List<PostDTO> postList, int userId) {
        for(PostDTO post: postList){
            System.out.println(post.getTitle());
            String fileName = String.format("user-%s-post-%d-comments.json", userId, post.getId());
            String filePath = "src/main/resources/";
            String json = gson.toJson(post);

            try (FileWriter writer = new FileWriter(filePath+fileName)) {
                writer.write(json);
            } catch (IOException e) {
                System.out.println("Proizoshla ohibka!");
                e.printStackTrace(System.out);
            }
        }

        System.out.println("All posts were successfully written.");
    }
}
