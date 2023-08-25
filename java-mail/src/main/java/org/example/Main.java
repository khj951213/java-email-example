package org.example;

public class Main {
    public static void main(String[] args) {
        var sendMail = new SendMail(
                "email",
                "password",
                "host server address",
                587);

        sendMail.setTitle("set title");
        sendMail.setContent("set content");

        sendMail.send("to email");
    }
}