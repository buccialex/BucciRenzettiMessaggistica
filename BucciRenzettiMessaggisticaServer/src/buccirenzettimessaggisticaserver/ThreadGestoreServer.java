package buccirenzettimessaggisticaserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadGestoreServer implements Runnable {

    private final Socket clientSocket;
    private final BucciRenzettiMessaggisticaServer server;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean attivo = true;

    public ThreadGestoreServer(Socket clientSocket, BucciRenzettiMessaggisticaServer server) {
        this.clientSocket = clientSocket;
        this.server = server;

        try {
            this.in = new DataInputStream(clientSocket.getInputStream());
            this.out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            chiudiConnessione();
        }
    }

    @Override
    public void run() {
        // Registra il client attivo sul server
        server.aggiungiClient(this);

        try {
            while (attivo) {
                // Resta in ascolto dei messaggi inviati da questo client
                String messaggio = in.readUTF();

                if (messaggio.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("Client [" + clientSocket.getPort() + "]: " + messaggio);

                // Inoltra il messaggio a tutti i client connessi
                server.broadcast("Client [" + clientSocket.getPort() + "]: " + messaggio, this);
            }
        } catch (IOException e) {
            System.out.println("Client disconnesso: " + clientSocket.getRemoteSocketAddress());
        } finally {
            chiudiConnessione();
        }
    }

    // Invia un messaggio verso il client associato a questo socket
    public synchronized void inviaMessaggio(String messaggio) {
        if (!attivo) return;

        try {
            out.writeUTF(messaggio);
            out.flush();
        } catch (IOException e) {
            chiudiConnessione();
        }
    }

    // Rimuove il client dal server e chiude tutti gli stream/socket
    public synchronized void chiudiConnessione() {
        if (!attivo) return;
        attivo = false;

        server.rimuoviClient(this);

        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
        } catch (IOException ignored) {}
    }
}