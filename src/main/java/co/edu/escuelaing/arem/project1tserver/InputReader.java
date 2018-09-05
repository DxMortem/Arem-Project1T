package co.edu.escuelaing.arem.project1tserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class InputReader {

    private String resource = null;
    private BufferedReader in;

    public InputReader(Socket cs) throws IOException {


        this.in = new BufferedReader(
                new InputStreamReader(cs.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (!in.ready()) {
                break;
            }
            if(inputLine.contains("GET")){
                this.resource = inputLine.split(" ")[1];
                System.out.println("Me pidieron:"+ resource);
            }
            System.out.println("Recibí: " + inputLine);
        }


    }

    public String getResource() {
        return resource;
    }

    public void closeIn() throws IOException {
        this.in.close();
    }
}
