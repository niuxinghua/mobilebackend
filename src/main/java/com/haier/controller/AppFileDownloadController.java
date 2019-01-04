package com.haier.controller;


import com.haier.APIVersion.APIVersion;
import com.haier.service.DownloadService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by niuxinghua on 2019/1/2.
 */
@RestController
public class AppFileDownloadController implements APIVersion {

    @RequestMapping(value = "/files/{appName}/{fileKey}", method = RequestMethod.GET)
    public void dowloadFile(@PathVariable(value = "appName") String appName,@PathVariable(value = "fileKey") String fileKey, HttpServletResponse response)  {
        DownloadService.downLoadFile(appName,fileKey,response,"");
    }



}
