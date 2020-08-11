package com.upload.copy;

import com.project.Response;
import com.project.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
@Component
public class CopyImpl implements ICopy {

    public Response copy(String srcFile, String destination) {

        File file = new File(srcFile);
        if (!file.exists() || !file.isFile()) {
            return Response.getFAIL(ResponseCode.srcFileException);
        }

        File file2 = new File(destination);
        if (!file.exists() || !file.isDirectory()) {
            return Response.getFAIL(ResponseCode.destinationException);
        }

        try {
            this.nioMappedCopy(file, file2);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.getFAIL(ResponseCode.CopyFail);
        }

        return Response.getSUCCESS(ResponseCode.CopySuccess);
    }

    private static void nioMappedCopy(File copyFile, File newFile) throws Exception {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inFiC = null;
        FileChannel outFoC = null;
        int length = 2097152;
        try {
            fis = new FileInputStream(copyFile);
            fos = new FileOutputStream(newFile);
            inFiC = fis.getChannel();
            outFoC = fos.getChannel();
            while (inFiC.position() != inFiC.size()) {
                if ((inFiC.size() - inFiC.position()) < length) {
                    length = (int) (inFiC.size() - inFiC.position());
                }
                MappedByteBuffer inbuffer = inFiC.map(FileChannel.MapMode.READ_ONLY, inFiC.position(), length);
                outFoC.write(inbuffer);
                inFiC.position(inFiC.position() + length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outFoC != null) { outFoC.close(); }
            if (inFiC != null) { inFiC.close(); }
            if (fos != null) { fos.close(); }
            if (fis != null) { fis.close(); }
        }
    }
}
