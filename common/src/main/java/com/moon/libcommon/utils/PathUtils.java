package com.moon.libcommon.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import com.moon.libbase.utils.DateUtils;
import com.moon.libbase.utils.FileUtils;
import com.moon.libcommon.R;

import java.io.File;
import java.io.IOException;

public class PathUtils {
    public static String rootPath = "moon";

    public static String historyPath = "/chat/";
    public static String imagePath = "/image/";
    public static String thumbImagePath = "/image/thumb/";
    public static String cameraImagePath = "/image/camera/";
    public static String voicePath = "/voice/";
    public static String filePath = "/file/";
    public static String videoPath = "/video/";
    public static String savePicture = "/save/picture/";
    public static String pictures = "/Pictures/Olala/";

    private static String examPath = "/exam/";//错题导出地址
    private static String decompressionPath = "/decompression/";//试卷解压出来的都放这个地址，下载管理看这个
    public static String ZIP = ".zip";
    private boolean isinit = false;

    public static String getHistoryPath() {
        return historyPath;
    }

    public static String getThumbImagePath() {
        return thumbImagePath;
    }

    public static String getCameraImagePath() {
        return cameraImagePath;
    }

    public static String getVoicePath() {
        return voicePath;
    }

    public static String getVideoPath() {
        return videoPath;
    }

    public static String getRootPath() {
        return rootPath;
    }

    public static String getSavePicture() {
        return savePicture;
    }

    public static String getPictures() {
        return pictures;
    }

    private static PathUtils instance;

    public static synchronized PathUtils instance() {
        if (null == instance) {
            instance = new PathUtils();
        }
        return instance;
    }

    public static PathUtils getInstance() {
        return instance();
    }

    private PathUtils() {
    }

    public boolean isIsinit() {
        return isinit;
    }

    //权限获取的时候，设置下路径
    public void initDirs(Context context) {
        synchronized (rootPath) {
            if (isinit) {
                return;
            }
            String pluginStoragePath = context.getFilesDir().getAbsolutePath();

            pictures = pluginStoragePath + pictures;

            rootPath = pluginStoragePath + "/";
            historyPath = pluginStoragePath + historyPath;
            imagePath = pluginStoragePath + imagePath;
            thumbImagePath = pluginStoragePath + thumbImagePath;
            cameraImagePath = pluginStoragePath + cameraImagePath;
            voicePath = pluginStoragePath + voicePath;
            filePath = pluginStoragePath + filePath;
            videoPath = pluginStoragePath + videoPath;
            savePicture = pluginStoragePath + savePicture;
            examPath = pluginStoragePath + examPath;
            decompressionPath = pluginStoragePath + decompressionPath;
            createDirectory(historyPath);
            createDirectory(imagePath);
            createDirectory(thumbImagePath);
            createDirectory(cameraImagePath);
            createDirectory(voicePath);
            createDirectory(filePath);
            createDirectory(videoPath);
            createDirectory(savePicture);
            createDirectory(pictures);
            createDirectory(examPath);
            createDirectory(decompressionPath);
            isinit = true;
        }
    }


    public boolean enoughSpaceSizeSD() {
        long avail = getAvailableExternalMemorySize() / 1024 / 1024;
        return avail - 30 > 0;
    }

    /**
     * 获取SDCARD剩余存储空间 *
     *
     * @return
     */
    private long getAvailableExternalMemorySize() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File path = Environment.getExternalStorageDirectory();
            long internalSDSize = 0;
            try {
                StatFs stat = new StatFs(path.getPath());
                long blockSize = stat.getBlockSize();
                long availableBlocks = stat.getAvailableBlocks();
                internalSDSize = blockSize * availableBlocks;
            } catch (Exception e) {

            }
            return internalSDSize;
        } else {
            return -1;
        }
    }

    public void createDirectory(String dirPath) {
        try {
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
                String c1 = "chmod 777  " + dirPath; // 755 644
                try {
                    Runtime.getRuntime().exec(c1);
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getImagePath() {
        return imagePath;
    }

    public static String getFilePath() {
        return filePath;
    }

    public String createRecordFile(String dir, String suffix) {
        String filePath = voicePath + dir + "/";
        String fileName = filePath + System.currentTimeMillis() + suffix;
        File dirFile = new File(filePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }


    public static void clearCache() {
        FileUtils.delete_noFirst_Directory(historyPath);
        FileUtils.delete_noFirst_Directory(thumbImagePath);
        FileUtils.delete_noFirst_Directory(cameraImagePath);
        FileUtils.delete_noFirst_Directory(voicePath);
        FileUtils.delete_noFirst_Directory(filePath);
        FileUtils.delete_noFirst_Directory(videoPath);
        FileUtils.delete_noFirst_Directory(savePicture);
        FileUtils.delete_noFirst_Directory(pictures);
    }
}
