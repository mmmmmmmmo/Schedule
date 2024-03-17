package com.moon.libbase.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

import com.moon.libbase.base.BaseApplication;

import timber.log.Timber;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.*;

public class FileUtils {

    public static final String ROOT_DIR = "Android/data/"
            + BaseApplication.Companion.getInstance().getPackageName();
    public static final String DOWNLOAD_DIR = "download";
    public static final String CACHE_DIR = "cache";
    public static final String ICON_DIR = "icon";

    public static final String APP_STORAGE_ROOT = "tiho";

    /**
     * 判断SD卡是否挂载
     */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取下载目录
     */
    public static String getDownloadDir() {
        return getDir(DOWNLOAD_DIR);
    }

    /**
     * 获取缓存目录
     */
    public static String getCacheDir() {
        return getDir(CACHE_DIR);
    }

    /**
     * 获取icon目录
     */
    public static String getIconDir() {
        return getDir(ICON_DIR);
    }

    /**
     * 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录
     */
    public static String getDir(String name) {
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getAppExternalStoragePath());
        } else {
            sb.append(getCachePath());
        }
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        if (createDirs(path)) {
            return path;
        } else {
            return null;
        }
    }

    /**
     * 获取SD下的应用目录
     */
    public static String getExternalStoragePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(ROOT_DIR);
        sb.append(File.separator);
        return sb.toString();
    }

    /**
     * 获取SD下当前APP的目录
     */
    public static String getAppExternalStoragePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(APP_STORAGE_ROOT);
        sb.append(File.separator);
        return sb.toString();
    }

    /**
     * 获取应用的cache目录
     */
    public static String getCachePath() {
        File f = BaseApplication.Companion.getInstance().getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }

    /**
     * 创建文件夹
     */
    public static boolean createDirs(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists() || !file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 产生图片的路径，这里是在缓存目录下
     */
    public static String generateImgePathInStoragePath() {
        return getDir(ICON_DIR) + String.valueOf(System.currentTimeMillis()) + ".jpg";
    }


    //常见文件的头
    public final static String TYPE_JPEG = "jpg";
    public final static String HEAD_JPEG = "FFD8FF";
    public final static String TYPE_PNG = "png";
    public final static String HEAD_PNG = "89504E47";
    public final static String TYPE_BMP = "bmp";
    public final static String HEAD_BMP = "424D";
    public final static String TYPE_GIF = "gif";
    public final static String HEAD_GIF = "47494638";
    public final static String TYPE_ZIP = "zip";
    public final static String HEAD_ZIP = "504B0304";
    private static Map<String, String> typeMap = new HashMap<String, String>();

    static {
        typeMap.put(TYPE_JPEG, HEAD_JPEG);
        typeMap.put(TYPE_PNG, HEAD_PNG);
        typeMap.put(TYPE_BMP, HEAD_BMP);
        typeMap.put(TYPE_GIF, HEAD_GIF);
        typeMap.put(TYPE_ZIP, HEAD_ZIP);
    }


    /**
     * 根据文件的头返回文件类型
     *
     * @param filePath 完整的文件路径
     * @return 返回文件类型 jpg,png,bmp,gif,zip 其他返回null
     */

    public static String getFileType(String filePath) {
        return getFileType(new File(filePath));
    }

    /**
     * 根据文件的头返回文件类型
     *
     * @param file
     * @return 返回文件类型 jpg,png,bmp,gif,zip 其他返回null
     */
    public static String getFileType(File file) {
        String fileType = null;
        if (file.isFile()) {
            byte[] b = new byte[50];
            InputStream is = null;
            try {
                is = new FileInputStream(file);
                is.read(b);
                fileType = getFileTypeByStream(b);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return fileType;
    }

    /**
     * 根据文件的头返回文件类型
     *
     * @param buffer 文件前50个字节流
     * @return 返回文件类型 jpg,png,bmp,gif,zip 其他返回null
     */
    public static String getFileTypeByStream(byte[] buffer) {
        String fileTypeHex = String.valueOf(getHexString(buffer));
        for (Map.Entry<String, String> entry : typeMap.entrySet()) {
            if (fileTypeHex.toUpperCase().startsWith(entry.getValue()))
                return entry.getKey();
        }
        return null;
    }

    public static void fileChannelCopy(String srcPath, String outPath) {
        File s = new File(srcPath);
        File t = new File(outPath);
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fi != null) {
                    fi.close();
                }
                if (in != null) {
                    in.close();
                }
                if (fo != null) {
                    fo.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static String getHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 保存图片
     *
     * @param bitName
     * @param mBitmap
     */
    public static String saveBitmap(String bitName, Bitmap mBitmap) {
        File f = new File(bitName);
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fOut) {
                try {
                    fOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return f.getAbsolutePath();
    }

    //获取全部图片
    public static ArrayList<String> getImagePath(String path) {
        // 图片列表
        ArrayList<String> imagePathList = new ArrayList<String>();
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(path);
        File[] files = fileAll.listFiles();
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (checkIsImageFile(file.getPath())) {
                    imagePathList.add(file.getPath());
                }
            }
        }

        // 返回得到的图片列表
        return imagePathList;
    }

    //目录下文件夹的列表
    public static ArrayList<String> getPathList(String path) {
        // 图片列表
        ArrayList<String> PathList = new ArrayList<String>();
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(path);
        if (!fileAll.exists()) {
            return PathList;
        }
        File[] files = fileAll.listFiles();
        Arrays.sort(files, new Comparator<File>() {
            public int compare(File f1, File f2) {
                long diff = f1.lastModified() - f2.lastModified();
                if (diff > 0)
                    return 1;
                else if (diff == 0)
                    return 0;
                else
                    return -1;
            }

            public boolean equals(Object obj) {
                return true;
            }

        });
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                PathList.add(file.getPath());
            }
        }
        return PathList;
    }

    /**
     * 检查扩展名，得到图片格式的文件
     *
     * @param fName 文件名
     * @return
     */
    public static boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg") || FileEnd.equals("bmp")) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete_noFirst_Directory(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return delete_unFirstDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean delete_unFirstDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                // 删除子文件
                if (files[i].isFile()) {
                    flag = deleteFile(files[i].getAbsolutePath());
                    if (!flag)
                        break;
                }
                // 删除子目录
                else if (files[i].isDirectory()) {
                    flag = deleteDirectory(files[i]
                            .getAbsolutePath());
                    if (!flag)
                        break;
                }
            }
        }

        if (!flag) {
            return false;
        }
        return true;
    }

    public static boolean rename(String oldName, String newName) {
        File oldFile = new File(oldName);
        File newFile = new File(newName);
        if (!oldFile.exists()) {
            return false;
        }
        if (newFile.exists()) {
            System.out.println(newName + " 已存在");
            newFile.deleteOnExit();
        }
        return oldFile.renameTo(newFile);
    }
}
