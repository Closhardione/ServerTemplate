package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private ArrayList<ClientThread> clients = new ArrayList<>();
    private int port;

    public Server(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }
    public void listen(){
        while(true){
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println(clientSocket + " joined!");
                ClientThread clientThread = new ClientThread(this,clientSocket);
                clients.add(clientThread);
                clientThread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void broadcast(ClientThread sender,String message){
        for(var client : clients){
            if(!client.equals(sender)){
                client.send(message);
            }
        }
    }
}
