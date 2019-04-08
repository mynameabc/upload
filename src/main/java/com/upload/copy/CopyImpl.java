package com.upload.copy;

import communal.Result;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@Component
public class CopyImpl implements ICopy {

    public Result copy(String srcFile, String destination) {

        File file = new File(srcFile);
        if (!file.exists() || !file.isFile()) {
            return new  Result(false, "srcFile不是一个有效的文件");
        }

        File file2 = new File(destination);
        if (!file.exists() || !file.isDirectory()) {
            return new  Result(false, "destination不是一个有效目录");
        }

        try {
            this.nioMappedCopy(file, file2);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "复制失败!");
        }

        return new Result(true, "复制成功!");
    }

    private static void nioMappedCopy(File Copyfile, File newfile) throws Exception {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inFiC = null;
        FileChannel outFoC = null;
        int length = 2097152;
        try {
            fis = new FileInputStream(Copyfile);
            fos = new FileOutputStream(newfile);
            inFiC = fis.getChannel();
            outFoC = fos.getChannel();
            long startTime = System.currentTimeMillis();
            while (inFiC.position() != inFiC.size()) {
                if ((inFiC.size() - inFiC.position()) < length) {
                    length = (int) (inFiC.size() - inFiC.position());
                }
                MappedByteBuffer inbuffer = inFiC.map(FileChannel.MapMode.READ_ONLY, inFiC.position(), length);
                outFoC.write(inbuffer);
                inFiC.position(inFiC.position() + length);
            }
            long EndTime = System.currentTimeMillis();
            System.out.print("nioMappedCopy用了毫秒数：");
            System.out.println(EndTime - startTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outFoC != null) {
                outFoC.close();
            }
            if (inFiC != null) {
                inFiC.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }
}
