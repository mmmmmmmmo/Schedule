package com.moon.libbase.utils.bitmap;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import timber.log.Timber;

import java.io.*;

/**
 * 图片压缩
 */
public class CompressPicture {
    private static CompressPicture instance = null;
    /**
     * bitmap是压缩后的图片文件 savePath是压缩后图片的保存路径
     */
    private Bitmap bitmap;
    private static final long MAX_FILE_SIZE = 200 * 1024;

    public static CompressPicture instance() {
        synchronized (CompressPicture.class) {
            if (instance == null) {
                instance = new CompressPicture();
            }
            return instance;
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * 判断是否需要压缩(大于MAX_FILE_SIZE则需要压缩)
     *
     * @param picPath
     * @return
     */
    public boolean needCompress(String picPath) {
        if (TextUtils.isEmpty(picPath)) {
            return false;
        }
        File file = new File(picPath);
        if (file.isFile() && file.length() > MAX_FILE_SIZE) {
            return true;
        }
        return false;
    }

    public void compressWithBounds(String savePath, String picPath, float width, float height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高
        bitmap = BitmapFactory.decodeFile(picPath, options); // 此时返回bm为空
        options.inJustDecodeBounds = false;
        int w = options.outWidth;
        int h = options.outHeight;
        int wSampleSize = 1, hSampleSize = 1;
        while (w >= width) {
            w /= 2;
            wSampleSize *= 2;
        }
        while (h >= height) {
            h /= 2;
            hSampleSize *= 2;
        }
        options.inSampleSize = wSampleSize > hSampleSize ? wSampleSize : hSampleSize;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        bitmap = BitmapFactory.decodeFile(picPath, options);
        if (bitmap == null) {
            return;
        }

        int degress = BitmapUtils.getDegress(picPath);
        Timber.e("degress "+degress);
        if (degress > 0) {
            bitmap = BitmapUtils.rotate(bitmap, degress);
        }


        File file = new File(savePath);
        File parent = file.getParentFile();
        if (!parent.exists())
            parent.mkdirs();
        try {
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(CompressFormat.JPEG, 60, out)) {
                out.flush();
                out.close();
            }
            if (!bitmap.isRecycled()) {
                bitmap.recycle();// 记得释放资源，否则会内存溢出
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
