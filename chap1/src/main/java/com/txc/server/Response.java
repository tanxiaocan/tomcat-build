package com.txc.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;

/**
 * Created by tanxiaocan on 2016/3/28.
 */
public class Response {
    private OutputStream outputStream;
    public Response(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void sendStaticResource(){
        String errorFilePath = System.getProperty("user.dir") + File.separator + "error.html";
        File file = new File(errorFilePath);
        if(file.exists()){
            FileInputStream in = null;
            try{
                in = new FileInputStream(file);
                int c = -1;
                byte[] buffer = new byte[1024];
                c = in.read(buffer,0,1024);
                while(c != -1){
                    outputStream.write(buffer);
                    c = in.read(buffer,0,1024);
                }
            }catch (Exception e){
                e.printStackTrace();
                System.exit(-1);
            }finally {
                if(in != null){
                    try {
                        in.close();
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
