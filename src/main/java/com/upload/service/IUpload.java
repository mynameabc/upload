package com.upload.service;

import communal.Result;

import javax.servlet.http.HttpServletRequest;

public interface IUpload {

    /**
     * 通用上传文件
     * @param request
     * @return
     */
    Result upload(HttpServletRequest request);
}
