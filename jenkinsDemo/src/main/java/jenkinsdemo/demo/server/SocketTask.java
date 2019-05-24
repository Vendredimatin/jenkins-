package jenkinsdemo.demo.server;

import jenkinsdemo.demo.Constants;
import jenkinsdemo.demo.FileHelper;
import jenkinsdemo.demo.FileReceive;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

/*
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
**/

public class SocketTask implements Runnable{
    private Socket socket;

    public SocketTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        DataOutputStream out = null;
        DataInputStream in = null;
        long threadID  = Thread.currentThread().getId();
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String fileInfo = in.readUTF();
            System.out.println("receive msg:" + fileInfo);
            String[] infos = fileInfo.split("=");
            String fileName = infos[0];
            String fileMD5 = infos[1];
            Long fileSize = Long.parseLong(infos[2]);
            long blockNumber = Long.parseLong(infos[3]);


            String dir = Constants.STORE_DIR;
            File file = new File(dir, fileName);
            FileReceive fileReceive = new FileReceive(file, in, out);
            if (!FileHelper.fileExists(fileMD5, file)) {
                System.out.println("服务器不存在该文件，第一次接收该文件");
                out.writeUTF(Constants.FIRST_UPLOAD);
                out.flush();
                fileReceive.firstReceive(blockNumber);
            } else {
                // 检查是否传输完毕
                if (file.length() < fileSize) {
                    //断点传输
                    System.out.println("服务器已存在，但是未接受完全");
                    out.writeUTF(Constants.BREAK_POINT_UPLOAD);
                    out.flush();
                    System.out.println(blockNumber);
                    fileReceive.breakPointReceive(blockNumber);
                } else if (file.length() > fileSize) {
                    //重新传输
                } else {
                    //秒传
                    out.writeUTF(Constants.SECOND_UPLOAD);
                    out.flush();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

    }
}
