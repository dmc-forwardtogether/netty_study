package org.netty.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @className: JavaServer
 * @author: 齐向前
 * @date: 2023/6/19
 **/
public class JavaServer {

    public static void main(String[] args) throws IOException {
        String request = "";
        String response = "";
        int portNumber = 6000;
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket clientSocket = serverSocket.accept();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
        while ((request = in.readLine()) != null){
            if ("Done".equals(request)) {
                break;
            }
            response = processRequest(request);
            out.println(response);
        }
    }

    private static String processRequest(String request) {
        return "111";
    }

}
