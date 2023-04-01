package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader br = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String s = br.readLine(); s != null && !s.isEmpty(); s = br.readLine()) {
                        if (s.contains("Bye")) {
                            server.close();
                        }
                        System.out.println(s);
                    }
                    out.flush();
                }
            }
        }
    }
}
