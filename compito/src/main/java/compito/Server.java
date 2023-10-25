package compito;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    Socket clientSocket;
    BufferedReader input;
    DataOutputStream output;
    int clientID;

    public void accept(ServerSocket serverSocket) {

        try {
            clientSocket = serverSocket.accept();
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message) {

        try {
            output.writeBytes(message+'\n');
        } catch (IOException e) {  
            e.printStackTrace();
        }
    }

    public String receive() {
        
        try {
            return input.readLine();
        } catch (IOException e) { 
            e.printStackTrace();
            return "Errore";
        }
    }

    public void closeClient() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}