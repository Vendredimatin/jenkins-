/*
package jenkinsdemo.demo;

import jenkinsdemo.demo.Constants;
import jenkinsdemo.demo.client.Client;
import jenkinsdemo.demo.client.ClientTask;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

*/
/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 *//*

public class FileUpload {
    File file;
    DataOutputStream out;
    DataInputStream in;
    List<Long> blocks;
    long threadID;

    public FileUpload(File file, DataInputStream in, DataOutputStream out) {
        this.file = file;
        this.out = out;
        this.in = in;
        blocks = FileHelper.deblock(file);
        threadID = Thread.currentThread().getId();
    }

    public void firstUpload() throws IOException {
        System.out.println("upload start");
        RandomAccessFile fis = null;
        try {
            fis = new RandomAccessFile(file,"rwd");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        byte[] buffer = new byte[Constants.BLOCK_SIZE];
        int c = 0;
        assert fis != null;
        for (int i = 0; i < blocks.size(); i++) {
            if (i != 0) {
                String response = ClientTask.readFeedBack(in);
                if (!response.equals("OK")) {
                    System.out.println("出错");
                    break;
                }
            }

            long blockID = i;
            sendBlockInfo(blockID);

            readFile(fis,blockID);
        }

        System.out.println("upload done");
    }

    public void breakPointUpload(long start) {
        System.out.println(start);
        System.out.println("breakPointUpload start");

        RandomAccessFile fos = null;
        try {
            fos = new RandomAccessFile(file, "rwd");

            fos.seek(start * Constants.BLOCK_SIZE);
            byte[] buffer = new byte[Constants.BLOCK_SIZE];

            for (long i = start; i < blocks.size(); i++) {
                if (i != start) {
                    String response = ClientTask.readFeedBack(in);
                    if (!response.equals("OK")) {
                        System.out.println("出错");
                        break;
                    }
                }

                long blockID = i;
                sendBlockInfo(blockID);
                readFile(fos,blockID);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendBlockInfo(long blockID) throws IOException {
        long blockSize = blocks.get((int) blockID);
        // 文件名=第几块=本块大小
        String blockInfo = file.getName() + "=" + blockID + "=" + blockSize;
        out.writeUTF(blockInfo);
    }

    private void readFile(RandomAccessFile fos, Long blockNumber) throws IOException {
        byte[] buffer = new byte[Constants.BLOCK_SIZE];
        int c = 0;
        if ((c = fos.read(buffer)) != -1) {
            System.out.println(c);
            out.write(buffer, 0, c);
            out.flush();
            System.out.println("当前进程id:" + threadID +",传送文件名：" + file.getName() + "; 传输第" + blockNumber + "块; " + "块大小：" + c / (1024) + "kb");
        }
    }

    public void checkInfo() throws IOException {
        long fileSize = file.length();
        String fileName = file.getName();
        String fileInfo = fileName + "=" + FileHelper.getFileMD5(file) +"="+fileSize + "=" + blocks.size();
        out.writeUTF(fileInfo);
        out.flush();
    }




}
*/
