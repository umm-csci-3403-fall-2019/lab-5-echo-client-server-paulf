package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {
        try {
            // Connect to the server (localhost if no argument provided)
            Socket socket = new Socket(args.length > 0 ? args[0] : "127.0.0.1", 6013);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            
            int read; // The byte we will be reading

            // While there is still input to read
            while ((read = System.in.read()) != -1) {
                output.write(read); // Write the input from the stdin to output stream (the server)
                System.out.write(input.read()); // Output to stdout the input coming from the server
            } 
            
            // Shutdown and close the socket
            socket.shutdownOutput();
            socket.close();
            
            System.out.flush(); // Flush the stdout stream

        } catch (ConnectException exception) {
            System.out.println("Unable to connect to the server");
            System.err.println(exception);
        } catch (IOException exception) {
            System.err.println(exception);
        } 
    }
}