package com.upload.service;

import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.upload.UploadContact;
import com.upload.UploadUtil;
import communal.Result;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("universalUploadImpl")
public class UniversalUploadImpl implements IUpload {

    @Autowired
    private UploadUtil uploadUtil;

    @Autowired
    @Qualifier("randomFileNamePolicy")
    private FileRenamePolicy fileRenamePolicy;

    public UniversalUploadImpl() {}

    public Result upload(HttpServletRequest request) {

//        uploadUtil.setAfterSuccess(afterSuccess);              //上传成功后执行的对象
//        uploadUtil.setFileRenamePolicy(fileRenamePolicy);      //命名规则对象

/*        return uploadUtil.uploadFile(
                request,
                UploadContact.UNIVERSAL_DIR,
                UploadContact.UNIVERSAL_MAX_POST_SIZE,
                UploadContact.UNIVERSAL_ENCODING);*/
            return null;
    }

}
