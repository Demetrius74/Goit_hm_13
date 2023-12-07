package org.example;

import org.example.dto.comment.CommentDTO;
import org.example.dto.post.PostDTO;
import org.example.dto.todo.TodoDTO;
import org.example.dto.user.UserDTO;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        // Айді користувача, над яким будемо знущатись
        int userId = 2;

        Task1 task1 = new Task1();
        System.out.println(task1.createObject());
        System.out.println(task1.deleteObject(userId));
        System.out.println(task1.updateObject(userId));
        System.out.println(task1.getAllUserInfo());
        UserDTO userTwo = task1.getInfoByUserId(userId);
        System.out.println(userTwo.getName());
        System.out.println(task1.getInfoByUserName(userTwo.getUsername()).get(0).getName());
        System.out.println();


        Task2 task2 = new Task2();
        List<PostDTO> userPosts = task2.getUserPosts(userId);
        Optional<PostDTO> lastPost = userPosts.stream()
                .max(Comparator.comparing(PostDTO::getId));

        if (lastPost.isPresent()) {
            PostDTO post = lastPost.get();
            List<CommentDTO> commentList = task2.getPostComments(post.getId());

            // ТУт виводимо коментарі в термінал
            commentList.stream()
                    .map(CommentDTO::getBody)
                    .forEach(System.out::println);

            // Тут записуємо у файл
            Task2.writeAllComments(commentList, userId, post.getId());
        } else {
            System.out.println("Data is empty.");
        }

        System.out.println();

        Task3 task3 = new Task3();
        List<TodoDTO> todoList = task3.getUserTodos(userId);
        task3.printActiveTodos(todoList);
    }
}