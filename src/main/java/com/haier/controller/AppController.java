package com.haier.controller;

import com.haier.APIVersion.APIVersion;
import com.haier.model.AppInfo;
import com.haier.model.ResponseResult;
import com.haier.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
//@RequestMapping(path="/app")
public class AppController implements APIVersion{
	@Autowired
	private AppRepository appRepository;

	@PostMapping(value="/addApp", consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseResult addNewUser (@RequestBody AppInfo appInfo) {
		AppInfo ret = appRepository.save(appInfo);
		ResponseResult result = new ResponseResult<AppInfo>();
		if (ret != null) {
			result.code = 200;
			result.message = "success";
			result.result = ret;
		} else {
			result.code = 500;
			result.message = "unknown error";
		}
		return result;
	}

	@GetMapping(value="/findAllApps")
	public @ResponseBody ResponseResult getAllAppinfo() {
		Iterable<AppInfo> ret = appRepository.findAll();
		ResponseResult result = new ResponseResult<Iterable<AppInfo>>();

		if (ret != null) {
			result.code = 200;
			result.message = "success";
			result.result = ret;
		} else {
			result.code = 500;
			result.message = "unknown error";
		}
		return result;
	}
}