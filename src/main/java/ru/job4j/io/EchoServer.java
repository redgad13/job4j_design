package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(8080)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String request = in.readLine();
                    request = request.substring((request.indexOf('=') + 1), request.indexOf("HTTP") - 1);
                    if ("Exit".equalsIgnoreCase(request)) {
                        server.close();
                    } else if ("Hello".equalsIgnoreCase(request)) {
                        out.write("HI HELLO".getBytes());
                    } else {
                        out.write(request.getBytes());
                    }
                    out.flush();
                }
            }
        }
    }
}
