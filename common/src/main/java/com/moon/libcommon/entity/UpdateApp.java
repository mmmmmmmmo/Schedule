package com.moon.libcommon.entity;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.Serializable;

public class UpdateApp implements Serializable {
    private long id;
    private int ver;
    private String apkName;
    private String fileName;
    private long createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }

    public String getApkName() {
        return apkName;
    }

    public void setApkName(String apkName) {
        this.apkName = apkName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
