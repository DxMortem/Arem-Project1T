package co.edu.escuelaing.arem.project1sockets;

import java.io.IOException;
import java.net.Socket;

public class ClientSocket {
    public ClientSocket() {
    }

    public static java.net.Socket getNewClientSocket(java.net.ServerSocket sc){
        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = sc.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        return clientSocket;
    }
}
