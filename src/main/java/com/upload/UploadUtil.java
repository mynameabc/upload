package com.upload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.project.Response;
import communal.Result;
import org.springframework.stereotype.Component;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 上传工具类
 */
@Component
public class UploadUtil {

    private String encoding;
    private String dir;
    private String max_post_size;
    private String overdue_wait_time;
    private String file_domain_name;

    private MimetypesFileTypeMap mtftp;

    public UploadUtil() {
        this.mtftp = new MimetypesFileTypeMap();
    }

    public Response uploadFile(HttpServletRequest request, String saveDir, int maxPostSize, String encoding) {

        String name = request.getParameter("aaa");
        File fileDirectory = new File(saveDir);

        //如果上传目录不存在就新建一个
        {
            if(!fileDirectory.exists()){
                fileDirectory.mkdirs();
            }
        }

        MultipartRequest multi = null;

        //上传
        {
            try {

                multi = new MultipartRequest(
                        request,
                        saveDir,
                        maxPostSize,
                        encoding);

            } catch (IOException e) {
                //上传错误时则删除掉此次表单的文件
//                this.deleteFiles(multi, saveDir);
                e.printStackTrace();
            }
        }

        Response response = null;

        //检查上传文件的扩展名
        return this.checkFileExtension(multi, saveDir);
    }

    /**
     * 检查上传文件的扩展名
     * @param multi
     * @param saveDir
     * @return
     */
    private Response checkFileExtension(MultipartRequest multi, String saveDir) {

        Enumeration fileNames = multi.getFileNames();
        while (fileNames.hasMoreElements()) {

            String fileName = (String)fileNames.nextElement();
            if(null != multi.getFile(fileName)) {
                String lastFileName = multi.getFilesystemName(fileName);

                File file = new File(saveDir + "//" + lastFileName);

/*                this.mtftp.addMimeTypes(uploadParameter.getExtensions());
                String mimetype = mtftp.getContentType(file);
                String type = mimetype.split("/")[0];

                count++;

                if (true) {
                    this.deleteFiles(multi, uploadParameter);
                    break;
                }*/
            }
        }

        return Response.getSUCCESS("检查成功!");
    }

    /**
     * 删除掉此次上传的所有文件
     * @param multi
     */
    private void deleteFiles(MultipartRequest multi, String saveDir) {

        Enumeration fileNames = multi.getFileNames();
        while (fileNames.hasMoreElements()) {

            String fileName = (String)fileNames.nextElement();
            if(null != multi.getFile(fileName)) {
                String lastFileName = multi.getFilesystemName(fileName);

                File file = new File(saveDir + "//" + lastFileName);
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
            }
        }
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setMax_post_size(String max_post_size) {
        this.max_post_size = max_post_size;
    }

    public void setOverdue_wait_time(String overdue_wait_time) {
        this.overdue_wait_time = overdue_wait_time;
    }

    public void setFile_domain_name(String file_domain_name) {
        this.file_domain_name = file_domain_name;
    }
}
