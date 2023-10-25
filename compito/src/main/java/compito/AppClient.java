package compito;

import java.util.Scanner;

public class AppClient 
{
    public static String ip = "localhost";
    public static int port = 6789;

    
    public static void main( String[] args )
    {
        Scanner inputScanner = new Scanner(System.in);

        String messageFromServer = "";

        Client client = new Client();
        client.start(ip,port);

        messageFromServer = client.receive();
        System.out.println(messageFromServer);

        
        String userInput = inputScanner.nextLine();
        client.send(userInput);

        messageFromServer = client.receive();
        System.out.println(messageFromServer);


        inputScanner.close();
        client.close();
    }
}
