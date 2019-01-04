package com.haier.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class AppInfo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer appId;

    private String appName;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "app_id")
    private List<VersionInfo> appVersion;


    private String appIconUrl;

    private Long createTime;

    private Long updateTime;

    private String createUser;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<VersionInfo> getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(List<VersionInfo> appVersion) {
        this.appVersion = appVersion;
    }


    public String getAppIconUrl() {
        return appIconUrl;
    }

    public void setAppIconUrl(String appIconUrl) {
        this.appIconUrl = appIconUrl;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
}