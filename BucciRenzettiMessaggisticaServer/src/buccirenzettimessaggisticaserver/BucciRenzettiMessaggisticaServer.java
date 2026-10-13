package buccirenzettimessaggisticaserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BucciRenzettiMessaggisticaServer {

    private final int PORTA = 5555;
    private final List<ThreadGestoreServer> clientConnessi = Collections.synchronizedList(new ArrayList<>());

    public void avvia() {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Server avviato sulla porta " + PORTA + ". In attesa di client...");

            // Thread separato per inviare messaggi da console server a tutti i client
            new Thread(this::gestisciConsole).start();

            // Ciclo infinito: accetta un numero illimitato di client
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nuova connessione accettata da: " + socket.getRemoteSocketAddress());

                // Crea e avvia un nuovo thread per il client connesso
                ThreadGestoreServer gestore = new ThreadGestoreServer(socket, this);
                new Thread(gestore).start();
            }

        } catch (IOException e) {
            System.err.println("Errore del ServerSocket: " + e.getMessage());
        }
    }

    private void gestisciConsole() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String testo = scanner.nextLine();
            broadcast("Server: " + testo, null);
        }
    }

    public void aggiungiClient(ThreadGestoreServer client) {
        clientConnessi.add(client);
    }

    public void rimuoviClient(ThreadGestoreServer client) {
        clientConnessi.remove(client);
    }

    // Invia il messaggio a tutti i client attualmente connessi
    public void broadcast(String messaggio, ThreadGestoreServer mittente) {
        synchronized (clientConnessi) {
            for (ThreadGestoreServer client : clientConnessi) {
                client.inviaMessaggio(messaggio);
            }
        }
    }

    public static void main(String[] args) {
        new BucciRenzettiMessaggisticaServer().avvia();
    }
}