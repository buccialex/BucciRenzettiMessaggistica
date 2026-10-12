package buccirenzettimessaggisticaclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author renzetti.alessandro
 */
public class BucciRenzettiMessaggisticaClient {

    public static void main(String[] args) throws IOException {
        String msg;
        Scanner s = new Scanner(System.in);
        Socket client = new Socket("localhost", 5555);
        OutputStream outputStream = client.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        InputStream inputStream = client.getInputStream();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        while (true) {
            System.out.print("Cosa vuoi scrivere al server?: ");
            msg = s.nextLine();
            dataOutputStream.writeUTF(msg);
            String response = dataInputStream.readUTF();
            System.out.println(response);
        }
    }
}