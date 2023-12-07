package org.example;

import com.google.gson.reflect.TypeToken;
import okhttp3.Request;
import okhttp3.Response;
import org.example.dto.post.PostDTO;
import org.example.dto.todo.TodoDTO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Task3 extends Task2 {
    public List<TodoDTO> getUserTodos(int userId) throws IOException {
        Request request = new Request.Builder()
                .url(hostUrl+"/users/"+userId+"/todos")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String body = response.body().string();
            Type listType = new TypeToken<List<TodoDTO>>() {}.getType();
            return gson.fromJson(body, listType);
        }
    }

    public void printActiveTodos(List<TodoDTO> todoList) {
        todoList.stream()
                .filter(dto -> !dto.isCompleted())
                .map(TodoDTO::getTitle)
                .forEach(System.out::println);
    }
}
