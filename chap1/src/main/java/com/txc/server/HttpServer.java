package com.txc.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xiaocantan on 2016/3/28.
 */
public class HttpServer {
    private final static String SHUTDOWN_COMMAND = "shutdown";
    private volatile boolean shutdown = false;

    public void await(int port, int backlog,String hostName){
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port,backlog, InetAddress.getByName(hostName));
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        while (!shutdown){
            Socket socket = null;
            try{
                socket = serverSocket.accept();
                InputStream in = socket.getInputStream();
                Request request = new Request();
                request.parse(in);
                OutputStream out = socket.getOutputStream();
                Response response = new Response(out);
                response.sendStaticResource();
                socket.close();
                shutdown = SHUTDOWN_COMMAND.equals(request.getURI());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]){
        HttpServer httpServer = new HttpServer();
        httpServer.await(8080,1,"localhost");
    }
}
