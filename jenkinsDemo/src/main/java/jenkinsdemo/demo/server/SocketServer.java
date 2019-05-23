/*
package jenkinsdemo.demo.server;

import jenkinsdemo.demo.Constants;
import jenkinsdemo.demo.FileHelper;
import jenkinsdemo.demo.FileReceive;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

*/
/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 *//*

public class SocketServer {
    private ExecutorService executorService;
    public static void main(String[] args) {

        new SocketServer().await();
    }

    public SocketServer() {
        //Runtime.getRuntime().availableProcessors() 获得CPU核心数
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 50);
    }

    public void await() {
        int port = 8080;
        boolean ok = true;

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (ok) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println(socket.getInetAddress().getHostAddress() + "连接进入");
                executorService.execute(new SocketTask(socket));
                System.out.println("is stop here?.......................");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
*/
