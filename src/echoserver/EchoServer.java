package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try {
            // Listen on port 6013
            ServerSocket socket = new ServerSocket(6013);
            
            while (true) { // Loop forever so we can accept multiple clients
                Socket client = socket.accept(); // Waits until a client connects, then accepts the connection
                
                InputStream input = client.getInputStream();
                OutputStream output = client.getOutputStream(); 
                
                int read; // The byte we will be reading

                // While there is still input to read
                while ((read = input.read()) != -1)
                    output.write(read); // Write the input from the client back to the client
                
                output.flush(); // flush the output stream

                // Shutdown and close the connection to the client
                client.shutdownOutput();
                client.close();
            }
        } catch (IOException exception) {
            System.err.println(exception);
        } 
    }
    
}