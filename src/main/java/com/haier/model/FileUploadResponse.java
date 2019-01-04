package com.haier.model;

/**
 * Created by niuxinghua on 2019/1/2.
 */
public class FileUploadResponse extends ResponseResult {
    public static FileUploadResponse buildSuccessResponse()
    {
        return new FileUploadResponse(200,"请求成功");
    }

    public static FileUploadResponse buildErrorResponse(String message)
    {
        return new FileUploadResponse(400,message);
    }

    public FileUploadResponse(int code,String message)
    {
        this.code = code;
        this.message = message;
        this.result = "";
    }
}
