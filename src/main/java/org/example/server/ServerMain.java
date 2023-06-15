package org.example.server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) {
        Server server;
        try {
            server = new Server(5000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        server.listen();
    }
}