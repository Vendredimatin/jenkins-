package jenkinsdemo.demo;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：Liu hanyi
 * @Description：
 * @Date Created in ${Time} ${Date}
 * @Modified By:
 */
public class FileHelper {
    public static boolean fileExists(String originalMD5, File file){
        if (originalMD5.equals(FileHelper.getFileMD5(file)))
            return true;
        return false;
    }

    public static String getFileMD5(File file){
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest;
        FileInputStream in;
        byte[] buffer = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    public static List<Long> deblock(File file) {
        long fileSize = file.length();
        List<Long> blocks = new ArrayList<>();
        long blockNumber = fileSize / Constants.BLOCK_SIZE;
        for (int i = 0; i < blockNumber; i++) {
            blocks.add((long) Constants.BLOCK_SIZE);
        }
        //最后一块
        blocks.add(fileSize % Constants.BLOCK_SIZE);

        return blocks;
    }
}
