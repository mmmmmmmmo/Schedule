package com.moon.libbase.download;

/**
 * by ry on 2018/11/23.
 */
public interface DownloadListener {
    void onStartDownload();

    void onProgress(int progress);

    void onFail(String errorInfo);

}
