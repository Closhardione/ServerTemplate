package org.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMain {
    public static void main(String[] args) {
        ServerThread serverThread = new ServerThread("localhost", 5000);
        serverThread.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            while(true) {
                String command = reader.readLine();
                serverThread.broadcast(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
