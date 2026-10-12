/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buccirenzettimessaggisticaserver;

import java.io.*;
import java.net.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author bucci.alex
 */
public class BucciRenzettiMessaggisticaServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int porta = 5555;
        
        try{
            ServerSocket serverSocket = new ServerSocket(porta);
            Socket clientSocket = serverSocket.accept();
            ThreadGestoreServer gestore = new ThreadGestoreServer(clientSocket);
            Thread t = new Thread(gestore);
            t.start();
            
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            
            while (scanner.hasNextLine()) {
                String messaggioUscita = scanner.nextLine();
                
                // Invia il messaggio al client
                dataOutputStream.writeUTF(messaggioUscita);
                dataOutputStream.flush();
                
                if (messaggioUscita.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.print("Tu: ");
            }
        } catch (IOException ex){
            System.out.println("errore");
        }
        
}
}
