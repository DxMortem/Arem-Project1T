package co.edu.escuelaing.arem.project1tserver;

import co.edu.escuelaing.arem.project1sockets.ClientSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        while (true) {

            //ServerSocket
            ServerSocket serverSocket = co.edu.escuelaing.arem.project1sockets.ServerSocket.getNewServerSocket();
            //ClientSocket
            Socket clientSocket = ClientSocket.getNewClientSocket(serverSocket);
            //Input Read
            InputReader ir = new InputReader(clientSocket);
            //Output
            ResourceWriter rw = new ResourceWriter(ir.getResource(),clientSocket);
            ir.closeIn();
            //Close Sockets
            clientSocket.close();
            serverSocket.close();
        }
    }



}