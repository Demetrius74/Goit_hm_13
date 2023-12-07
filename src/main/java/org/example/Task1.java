package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.example.dto.user.UserDTO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

public class Task1 {

    // Я не впевнений, чи краще робити їх ще static, чи ні?
    protected static final OkHttpClient client = new OkHttpClient();
    protected static final String hostUrl = "https://jsonplaceholder.typicode.com";
    protected static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    public String createObject() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(hostUrl+"/users")
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }

    public int deleteObject(int userId) throws IOException {
        Request request = new Request.Builder()
                .url(hostUrl+"/users/"+userId)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.code();
        }
    }

    public String updateObject(int userId) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .build();

        Request request = new Request.Builder()
                .url(hostUrl+"/users/"+userId)
                .put(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            return response.body().string();
        }
    }

    public List<UserDTO> getAllUserInfo() throws IOException {
        Request request = new Request.Builder()
                .url(hostUrl+"/users")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String body = response.body().string();
            Type listType = new TypeToken<List<UserDTO>>() {}.getType();
            return gson.fromJson(body, listType);
        }
    }

    public UserDTO getInfoByUserId(int userId) throws IOException {
        Request request = new Request.Builder()
                .url(hostUrl+"/users/"+userId)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String body = response.body().string();
            return gson.fromJson(body, UserDTO.class);
        }
    }

    public List<UserDTO> getInfoByUserName(String userName) throws IOException {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(hostUrl + "/users")).newBuilder();
        urlBuilder.addQueryParameter("username", userName);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            assert response.body() != null;
            String body = response.body().string();
            Type listType = new TypeToken<List<UserDTO>>() {}.getType();
            return gson.fromJson(body, listType);
        }
    }

}
