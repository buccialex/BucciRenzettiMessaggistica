/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buccirenzettimessaggisticaserver;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
/**
 *
 * @author Bux
 */

public class ThreadGestoreServer implements Runnable{
    private Socket clientSocket;
    
    public ThreadGestoreServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream())) {
            while (true) {
                // Legge il messaggio formattato in UTF dal client
                String message = dataInputStream.readUTF();
                System.out.println("\nClient: " + message);
                System.out.print("Tu: ");
            }
        } catch (IOException e) {
            // Quando il client si disconnette, readUTF lancia un'eccezione
            System.out.println("\nConnessione chiusa dal client.");
        }
    }
            
            
}
