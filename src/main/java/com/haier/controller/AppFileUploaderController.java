package com.haier.controller;

import com.aliyun.oss.OSSClient;
import com.haier.APIVersion.APIVersion;
import com.haier.OSS.OSSClientInfo;
import com.haier.OSS.OSSClientUtil;
import com.haier.model.FileUploadResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by niuxinghua on 2019/1/2.
 */
@RestController
public class AppFileUploaderController implements APIVersion {

    @RequestMapping(value = "/fileupload/{appName}/{fileKey}", method = RequestMethod.POST)
    public FileUploadResponse dowloadFile(@PathVariable(value = "appName") String appName, @PathVariable(value = "fileKey") String fileKey, @RequestParam("file") MultipartFile file) {
        OSSClient client = OSSClientUtil.getOSSClient();

        try {
            OSSClientUtil.uploadObject2OSS(client, file.getInputStream(), OSSClientInfo.BUCKET_NAME, appName, fileKey);
            return FileUploadResponse.buildSuccessResponse();

        } catch (Exception e) {
            return FileUploadResponse.buildErrorResponse(e.getMessage());
        }

    }
}
