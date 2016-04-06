package com.txc.server;

import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by xiaocantan on 2016/3/28.
 */
public class Request {
    private String URI;
    public void parse(InputStream inputStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try{
            line = reader.readLine();//拿到http请求的第一行
            parseURI(line);
            while (line != null && !"".equals(line)){
                System.out.println(line);
                line = reader.readLine();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * 从http请求的第一行解析出URI
     * @param httpFirstLine
     */
    private void parseURI(String httpFirstLine){
        if(httpFirstLine == null){
            URI = "";
            return;
        }
        String separator = " ";
        int index1 = httpFirstLine.indexOf(separator);
        if(index1 != -1){
            int index2 = httpFirstLine.indexOf(separator,index1 + 1);
            if( index2 != -1){
                URI = httpFirstLine.substring(index1 + 1,index2);
            }
        }

    }

    public String getURI(){
         return this.URI;
    }
}
