package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(args.length > 0 ? args[0] : "127.0.0.1", 6013);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            
            int read;
            while ((read = System.in.read()) != -1) {
                output.write(read);
                System.out.write(input.read());
            } 
            
            socket.shutdownOutput();
            socket.close();
            
            System.out.flush();
        } catch (ConnectException exception) {
            System.out.println("Unable to connect to the server");
            System.err.println(exception);
        } catch (IOException exception) {
            System.err.println(exception);
        } 
    }
}