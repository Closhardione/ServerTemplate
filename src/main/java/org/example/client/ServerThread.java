package org.example.client;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket serverSocket;
    private PrintWriter writer;
    private BufferedReader reader;
    public ServerThread(String address, int port){
        try {
            this.serverSocket = new Socket(address,port);
//            OutputStream output = serverSocket.getOutputStream();
            writer = new PrintWriter(/*output*/serverSocket.getOutputStream(),true);
//            InputStream input = serverSocket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(/*input*/serverSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void run(){
        String message;
        while(true){
            try {
                if (((message = reader.readLine()) != null)) {
                    send(message);
                    System.out.println(message);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void broadcast(String message) {
        writer.println(message);
    }

    private void send(String message){
        writer.println(message);
    }
}
