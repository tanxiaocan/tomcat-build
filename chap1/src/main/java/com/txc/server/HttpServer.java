package com.txc.server;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xiaocantan on 2016/3/28.
 */
public class HttpServer {
    private final static String SHUTDOWN_COMMAND = "shutdown";
    private boolean shutdown = false;

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
                socket.getInputStream();
                InputStream in = socket.getInputStream();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }


}
