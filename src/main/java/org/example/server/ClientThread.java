package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket clientSocket;
    private Server server;
    private PrintWriter writer;
    private BufferedReader reader;


    public ClientThread(Server server, Socket socket){
        this.clientSocket = socket;
        this.server=server;
    }
    public void run(){
        try {
            reader =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer =  new PrintWriter(clientSocket.getOutputStream(),true);
            String message;
            while ((message = reader.readLine())!=null){
                server.broadcast(this,message);
            }
            reader.close();
            writer.close();
            clientSocket.close();
            System.out.println("Closed\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void send(String message){
        writer.println(message);
    }
}
