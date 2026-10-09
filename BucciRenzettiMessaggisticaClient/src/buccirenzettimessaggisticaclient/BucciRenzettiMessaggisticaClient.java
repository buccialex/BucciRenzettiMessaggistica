/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package buccirenzettimessaggisticaclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author renzetti.alessandro
 */
public class BucciRenzettiMessaggisticaClient {

     /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        while(true){
           String msg="Ciao Server";
            try (Socket client = new Socket("10.205.0.50",5555)) {
            OutputStream outputStream = client.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(msg);
            InputStream inputStream = client.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String response = dataInputStream.readUTF();
            System.out.println("Risposta dal server: " + response);
            } 
        }
        
    }
    
}
