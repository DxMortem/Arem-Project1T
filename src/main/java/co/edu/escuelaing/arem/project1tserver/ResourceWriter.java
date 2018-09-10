package co.edu.escuelaing.arem.project1tserver;

import java.io.*;
import java.net.Socket;

class ResourceWriter {

    private Socket clientSocket;

    ResourceWriter(String resource, Socket clientSocket) {
        this.clientSocket = clientSocket;
        if (resource.toLowerCase().contains(".html")){htmlResource(resource);}
        else if (resource.toLowerCase().contains(".css")){cssResource(resource);}
        else if (resource.toLowerCase().contains(".png")){pngResource(resource);}
        else{
            raise501();
        }
    }



    private void raise501() {
        PrintWriter out;
        try {
            out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            String outputLine = "HTTP/1.1 501 Method Not Implemented\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>501 Method Not Implemented</title>\n"
                    + "</head>"
                    + "<body>"
                    + "<h1>Error 501: Method Not Implemented</h1>"
                    + "</body>"
                    + "</html>";

            out.println(outputLine);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void raise404() {
        try {
            PrintWriter out;
            out = new PrintWriter(this.clientSocket.getOutputStream(), true);
            String outputLine = "HTTP/1.1 404 Not Found\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>404 Not Found</title>\n"
                    + "</head>"
                    + "<body>"
                    + "<h1>Error 404: Not Found</h1>"
                    + "</body>"
                    + "</html>";

            out.println(outputLine);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pngResource(String resource) {

//        PrintWriter out = new PrintWriter(
//                this.clientSocket.getOutputStream(), true);
//
//        String outputLine = "HTTP/1.1 404 Not Found\r\n"
//                + "Content-Type: image/png\r\n"
//                + "\r\n"
//                + "<!DOCTYPE html>"
//                + "<html>"
//                + "<head>"
//                + "<meta charset=\"UTF-8\">"
//                + "<title>"+ resource.replaceFirst("/","") +"</title>\n"
//                + "</head>"
//                + "<body>"
//                + "<img src=resources"+resource+"/>"
//                + "</body>"
//                + "</html>";
//
//        out.println(outputLine);
//        out.close();


        try {
            File graphicResource= new File("src/main/resources" +resource);
            FileInputStream inputImage = new FileInputStream(graphicResource);
            byte[] bytes = new byte[(int) graphicResource.length()];
            inputImage.read(bytes);

            DataOutputStream binaryOut;
            binaryOut = new DataOutputStream(clientSocket.getOutputStream());
            binaryOut.writeBytes("HTTP/1.1 200 OK \r\n");
            binaryOut.writeBytes("Content-Type: image/png\r\n");
            binaryOut.writeBytes("Content-Length: " + bytes.length);
            binaryOut.writeBytes("\r\n\r\n");
            binaryOut.write(bytes);
            binaryOut.close();
        } catch (IOException ex){
            raise404();
            System.err.println("Error en la lectura de el archivo");
        }

    }

    private void htmlResource(String resource) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/main/resources" + resource));
            StringBuilder outputLine = new StringBuilder();
            outputLine.append("HTTP/1.1 200 OK\r\n");
            outputLine.append("Content-Type: text/html\n");
            outputLine.append("\r\n\r\n");
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                outputLine.append(bfRead);
            }
            PrintWriter out = new PrintWriter(
                    this.clientSocket.getOutputStream(), true);


            out.println(outputLine.toString());
            out.close();

        }catch (IOException ex){
            raise404();
            System.err.println("Error en la lectura de el archivo");
        }
    }

    private void cssResource(String resource) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/main/resources" + resource));
            StringBuilder outputLine = new StringBuilder();
            outputLine.append("HTTP/1.1 200 OK\r\n");
            outputLine.append("Content-Type: text/css\n");
            outputLine.append("\r\n\r\n");
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                outputLine.append(bfRead);
            }
            PrintWriter out = new PrintWriter(
                    this.clientSocket.getOutputStream(), true);


            out.println(outputLine.toString());
            out.close();

        }catch (IOException ex){
            raise404();
            System.err.println("Error en la lectura de el archivo");
        }
    }
}
