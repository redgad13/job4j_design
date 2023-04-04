package ru.job4j.io;

import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
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
        } catch (IOException e) {
            LOG.error("Thrown error is ", e);
        }
    }
}
