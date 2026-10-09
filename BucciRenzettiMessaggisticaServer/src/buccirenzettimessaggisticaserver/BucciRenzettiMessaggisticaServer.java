/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buccirenzettimessaggisticaserver;

import java.io.*;
import java.net.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author bucci.alex
 */
public class BucciRenzettiMessaggisticaServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean isRunning = true;
        try {
            while (isRunning) {
                ServerSocket serverSocket = new ServerSocket(5555);
                Socket clientSocket = serverSocket.accept();
                InputStream inputStream = clientSocket.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);
                String clientMessage = dataInputStream.readUTF();
                // Ottenere il flusso di output per inviare dati al client
                OutputStream outputStream = clientSocket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
// Invia dati al client
                String response = "Ciao, renzo!";
                dataOutputStream.writeUTF(response);
// Chiudi il socket del client quando hai finito con questo client
                
             }
        }catch (IOException ex) {
            System.getLogger(BucciRenzettiMessaggisticaServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
