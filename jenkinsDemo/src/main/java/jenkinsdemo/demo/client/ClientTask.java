/*
package jenkinsdemo.demo.client;

import jenkinsdemo.demo.Constants;
import jenkinsdemo.demo.FileUpload;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

*/
/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 *//*

public class ClientTask implements Runnable {
    private String fileName;
    private Socket socket;

    public ClientTask(String fileName) {
        this.fileName = fileName;

    }

    @Override
    public void run() {
        System.out.println("客户端线程id：" + Thread.currentThread().getId() + ",启动,请求传输文件: "+ this.fileName);
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        try {
            this.socket = new Socket(Constants.HOST, Constants.PORT);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String dir = Constants.FILE_DIR;
            File file = new File(dir, this.fileName);
            FileUpload fileUpload = new FileUpload(file, dataInputStream, dataOutputStream);

            fileUpload.checkInfo();

            String response = readFeedBack(dataInputStream);
            System.out.println(response);

            //如果是第一次上传
            if (response.equals(Constants.FIRST_UPLOAD)) {
                fileUpload.firstUpload();
            } else if (response.equals(Constants.BREAK_POINT_UPLOAD)) {
                long start = dataInputStream.readLong();
                fileUpload.breakPointUpload(start);
            } else if (response.equals(Constants.SECOND_UPLOAD)) {
                System.out.println("文件已上传过，使用秒传");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                dataInputStream.close();
                dataOutputStream.close();
                System.out.println("stream closed");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static String readFeedBack(DataInputStream dataInputStream) throws IOException {
        String response = dataInputStream.readUTF();
        return response;
    }
}
*/
