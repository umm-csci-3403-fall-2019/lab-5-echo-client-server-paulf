package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(6013);
            
            while (true) {
                Socket client = socket.accept();
                
                InputStream input = client.getInputStream();
                OutputStream output = client.getOutputStream(); 
                
                int read;
                while ((read = input.read()) != -1)
                    output.write(read);
                
                output.flush();
                client.shutdownOutput();
                client.close();
            }
        } catch (IOException exception) {
            System.err.println(exception);
        } 
    }
    
}