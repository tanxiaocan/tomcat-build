package com.txc.server;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanxiaocan on 2016/3/28.
 */
public class Response {
    private Request request;
    private OutputStream outputStream;
    public Response(OutputStream outputStream,Request request){
        this.outputStream = outputStream;
        this.request = request;
    }

    public void sendStaticResource(){
        String rootPath = System.getProperty("user.dir") + File.separator + "webroot";
        String resourcePath = rootPath + request.getURI();
        File fileResource = new File(resourcePath);
        if(!fileResource.exists()){
            resourcePath = rootPath + File.separator + "error.html";
            fileResource = new File(resourcePath);
        }
        FileInputStream in = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(outputStream);
            pw.println("HTTP/1.1 200 ok");
            pw.println("Content-Type:text/html");
//            pw.println("Content-Length:230");
            pw.println("\r\n");
            in = new FileInputStream(resourcePath);
            br = new BufferedReader(new InputStreamReader(in));
            String line = br.readLine();
            while (line != null){
                pw.println(line);
                line = br.readLine();
            }
            pw.flush();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }finally {
            if(in != null){
                try {
                    outputStream.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
