package compito;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    
    Socket socket;
    BufferedReader input;
    DataOutputStream output;

    public void start(String ip, int port) {

        try {
            socket = new Socket(ip, port);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
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

    public void close() {

        try {         
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}