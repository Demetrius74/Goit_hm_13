package org.example;

import org.example.dto.post.PostDTO;
import org.example.dto.todo.TodoDTO;
import org.example.dto.user.UserDTO;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Айді користувача, над яким будемо знущатись
        int userId = 2;

        Task1 task1 = new Task3();
        System.out.println(task1.createObject());
        System.out.println(task1.deleteObject(userId));
        System.out.println(task1.updateObject(userId));
        System.out.println(task1.getAllUserInfo());
        UserDTO userTwo = task1.getInfoByUserId(userId);
        System.out.println(userTwo.getName());
        System.out.println(task1.getInfoByUserName(userTwo.getUsername()).get(0).getName());
        System.out.println();


        Task2 task2 = new Task3();
        List<PostDTO> userPosts = task2.getUserPosts(userId);
        task2.writeAllPosts(userPosts, userId);
        System.out.println();

        Task3 task3 = new Task3();
        List<TodoDTO> todoList = task3.getUserTodos(userId);
        task3.printActiveTodos(todoList);
    }
}