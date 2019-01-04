package com.haier.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.haier.APIVersion.APIVersion;
import com.haier.OSS.OSSClientInfo;
import com.haier.OSS.OSSClientUtil;
import com.haier.Utils.FileUtil;
import com.haier.model.FileUploadResponse;
import com.haier.service.DiffService;
import com.haier.service.DownloadService;
import io.sigpipe.jbsdiff.InvalidHeaderException;
import org.apache.commons.compress.compressors.CompressorException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by niuxinghua on 2018/12/25.
 */
@RestController
public class DiffController implements APIVersion {

    @RequestMapping(value = "/diffs/{appName}/{currentVersion}/{targetVersion}", method = RequestMethod.GET)
    public void getDiffFile(@PathVariable(value = "appName") String appName, @PathVariable(value = "currentVersion") String currentVersion, @PathVariable(value = "targetVersion") String targetVersion, HttpServletResponse response) {

        //先判断patch是不是已经存在，如果存在直接请求线上已经diff过的patch
        OSSClient client = OSSClientUtil.getOSSClient();
        String relativePath = appName + "_" + currentVersion + "_" + targetVersion;
        String totalPath = appName + "/" + appName + "_" + currentVersion + "_" + targetVersion;
        //System.out.println(totalPath);
        OSSObject ossObject = OSSClientUtil.getObject(OSSClientUtil.getOSSClient(), totalPath, OSSClientInfo.BUCKET_NAME);
        if ((ossObject != null) && (ossObject.getObjectContent() != null)) {
            DownloadService.downLoadFile(appName, relativePath, response, "");
            return;
        }


        boolean currentversionsuccess = DownloadService.downLoadFile(appName, currentVersion + ".zip", null, appName + "/" + currentVersion);
        if (currentversionsuccess) {
            boolean targetversionsuccess = DownloadService.downLoadFile(appName, targetVersion + ".zip", null, appName + "/" + targetVersion);
            if (targetversionsuccess) {
                String file1path = DownloadService.PATH_FOLDER + appName + "/" + currentVersion + "/" + FileUtil.saveFileName(appName, currentVersion) + ".zip";
                String file2path = DownloadService.PATH_FOLDER + appName + "/" + targetVersion + "/" + FileUtil.saveFileName(appName, targetVersion + ".zip");
                String patchpath = DownloadService.PATH_FOLDER + appName + "/" + FileUtil.saveFileName(appName, currentVersion + "_" + targetVersion);
                System.out.println(patchpath);

                File filecurrent = new File(file1path);
                File filetarget = new File(file2path);
                File filepatch = new File(patchpath);

                try {
                    DiffService.diff(filecurrent, filetarget, filepatch);
                } catch (CompressorException e) {
                    e.printStackTrace();
                } catch (InvalidHeaderException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();

                }
                DownloadService.downLoadlocalPatch(appName, response, patchpath);
                //异步上传下载下载合并好的patch
                try {
                    OSSClientUtil.uploadObject2OSS(client, new FileInputStream(filepatch), OSSClientInfo.BUCKET_NAME, appName, appName + "_" + currentVersion + "_" + targetVersion);

                } catch (Exception e) {
                }

            } else {
                //下载失败
                System.out.print("下载失败");
                try {
                    response.sendError(400);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }

    }
}
