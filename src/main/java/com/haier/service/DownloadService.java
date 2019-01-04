package com.haier.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.haier.OSS.OSSClientInfo;
import com.haier.OSS.OSSClientUtil;
import com.haier.Utils.FileUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by niuxinghua on 2019/1/3.
 */
public class DownloadService {
    public static  String PATH_FOLDER = "/niuxinghua/Patchs/";
    static
    {
        PATH_FOLDER = System.getProperty("user.dir") + PATH_FOLDER;
    }
    public static synchronized Boolean downLoadFile(String appName, String fileKey, HttpServletResponse response, String firstsaverelativePathfolder) {

        OSSClient client = OSSClientUtil.getOSSClient();
        String totalPath = appName + "/" + fileKey;
        //System.out.println(totalPath);
        OSSObject ossObject = OSSClientUtil.getObject(OSSClientUtil.getOSSClient(), totalPath, OSSClientInfo.BUCKET_NAME);
        if (ossObject == null || ossObject.getObjectContent() == null)
        {
            return false;
        }
        if (firstsaverelativePathfolder.length() > 0) {
            String dir = PATH_FOLDER + firstsaverelativePathfolder;
            FileUtil.createDir(dir);
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File(dir, FileUtil.saveFileName(appName, fileKey)));
                FileUtil.saveFile(ossObject.getObjectContent(), fos);
            } catch (Exception e) {
                System.out.print(e);
                return false;
            }
            return true;

        }


        response.setContentType("application/octet-stream");//
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileKey);// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(ossObject.getObjectContent());
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        client.shutdown();
        return true;
    }

    public static synchronized Boolean downLoadlocalPatch(String appName,HttpServletResponse response, String patchPath) {

        response.setContentType("application/octet-stream");//
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName=" + appName + "_patch");// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(new File(patchPath));
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
