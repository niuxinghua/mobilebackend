package com.haier.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.haier.APIVersion.APIVersion;
import com.haier.OSS.OSSClientUtil;
import com.haier.service.DiffService;
import io.sigpipe.jbsdiff.InvalidHeaderException;
import org.apache.commons.compress.compressors.CompressorException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * Created by niuxinghua on 2018/12/25.
 */
@RestController

public class Test implements APIVersion{

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(HttpServletRequest request, HttpServletResponse response) {


    }


}
