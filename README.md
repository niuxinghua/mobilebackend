# mobilebackend
一个做patch分发的服务,patch与资源存储在oss上，依赖阿里云对象存储

#### API:

##### HOST:xxx

1请求zip压缩包

url：/api/v1/files/{appName}/{version.zip}

method：GET

测试url：  

说明：直接是 application/octet-stream流下载

2请求diff file

url：/api/v1/diffs/{appName}/{currentversion}/{targetversion}

method:GET

测试url：

说明：直接是 application/octet-stream流下载

