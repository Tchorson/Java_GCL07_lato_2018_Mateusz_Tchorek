package com.Labki.Chat;

/**
 * Created by mtchorek on 2018-03-13.
 */
public class Main {
    public static void main(String[] args) {
        ChatEngine chat = new ChatEngine();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();
        user1.setName("Mateusz Tchorek");
        user3.setName("Adam Malysz");
        user4.setName("Jan Nowak");



        try {
            chat.addUser(user1);

            chat.broadcastMessage(1,"");



        } catch (UserAddException e) {
            e.printStackTrace();
        }
    }

}
