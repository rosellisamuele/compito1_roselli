package compito;

import java.io.IOException;
import java.net.ServerSocket;

public class AppServer {
    public static int port = 6789;

    public static void main(String[] args){
        String inputString;
        String output;

        ServerSocket serverSocket;
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Server aperto sulla porta "+port);
        }catch(IOException e){
            System.out.println("Errore nella connessione");
            return;
        }

        Server server = new Server();
        server.accept(serverSocket);

        server.send("CALCOLATRICE SERVER: Inserire l'espressione da calcolare: "+'\n');
        inputString = server.receive();
        System.out.println(inputString);

        String operator = "";

        Double val1;
        Double val2;

        if(inputString.contains("+")){
            operator = "\\+";
        }
        else if(inputString.contains("-")){
            operator = "-";
        }
        else if(inputString.contains("*")){
            operator = "\\*";
        }
        else if(inputString.contains("/")){
            operator = "/";
        }else{
            server.send("Espressione non valida"+'\n');
            server.closeClient();
            return;
        }


        try{

        
            val1 = Double.parseDouble(inputString.split(operator)[0]);
            val2 = Double.parseDouble(inputString.split(operator)[1]);

            System.out.println(val1);
            System.out.println(val2);


            switch(operator){
                case "\\+":
                    output = String.valueOf(val1 + val2);
                    break;
                case "-":
                    output = String.valueOf(val1 - val2);
                    break;
                case "\\*":
                    output = String.valueOf(val1 * val2);
                    break;
                case "/":
                    output = String.valueOf(val1 / val2);
                    break;
                default:
                    output = "[X] Espressione non valida";
            }
        }catch(NumberFormatException e){
            output = "[X] Formato dei numeri non valido.";
        }

        server.send("Risultato: "+output+'\n');
        server.closeClient();
    }
}
