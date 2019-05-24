package jenkinsdemo.demo.client;

import jenkinsdemo.demo.Constants;
import jenkinsdemo.demo.FileUpload;

import java.io.*;
import java.net.Socket;

/*
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 **/

public class Client {
    public static void main(String[] args) {
        //System.out.println(System.getProperty("user.dir"));
        String[] fileNames = {"1.mp4","2.zip","3.exe"};
        for (int i = 0; i < 3; i++) {
            new Thread(new ClientTask(fileNames[i])).start();
        }
    }



}
