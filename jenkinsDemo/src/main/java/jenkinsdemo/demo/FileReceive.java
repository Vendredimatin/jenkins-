package jenkinsdemo.demo;

import java.io.*;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class FileReceive {
    File file;
    DataOutputStream out;
    DataInputStream in;
    long threadID;

    public FileReceive(File file, DataInputStream in, DataOutputStream out) {
        this.file = file;
        this.out = out;
        this.in = in;
        long threadID  = Thread.currentThread().getId();
    }


    public void firstReceive(long blockNumber) throws IOException {
        RandomAccessFile fos = null;
        try {
            fos = new RandomAccessFile(file, "rwd");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long receivedBlock = 0;


        recieveFile(blockNumber, fos, receivedBlock);

        fos.close();
        System.out.println("receive done");
    }

    public void breakPointReceive(long totalBlockNumber) throws IOException {
        long start = file.length() / Constants.BLOCK_SIZE;

        // 向客户端反应当前该从第几块开始接受
        try {
            out.writeLong(start);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RandomAccessFile fos = null;
        try {
            fos = new RandomAccessFile(file, "rwd");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long receivedBlock = start;

        fos.seek((start) * Constants.BLOCK_SIZE);
        recieveFile(totalBlockNumber, fos, receivedBlock);

        fos.close();
        System.out.println("receive done");


    }

    private void recieveFile(long totalBlockNumber, RandomAccessFile fos, long receivedBlock) throws IOException {
        System.out.println("Thread "+ threadID + " receive beginning,共有 " +totalBlockNumber+" 块需要接受");
        byte[] buffer = new byte[Constants.BLOCK_SIZE];
        int c = 0;
        while (receivedBlock < totalBlockNumber-1) {
            String blockInfo = in.readUTF();
            System.out.println(blockInfo);
            String[] blockInfos = blockInfo.split("=");
            long blockID = Long.parseLong(blockInfos[1]);
            int blockSize = Integer.parseInt(blockInfos[2]);

            System.out.println("Thread "+ threadID + " 当前接收第" + blockID + "块，块大小为:" + (blockSize / (1024)) + "kb..........");

            int size = 0;
            while ((c = in.read(buffer)) != -1) {
                fos.write(buffer, 0, c);
                size += c;
                if (size == blockSize) break;
            }


            receivedBlock = blockID;
            out.writeUTF("OK");
            out.flush();
            System.out.println("Thread "+ threadID + " 第" + receivedBlock + "块接收成功");
        }
    }
}
