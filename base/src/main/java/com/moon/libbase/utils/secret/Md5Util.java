package com.moon.libbase.utils.secret;


public final class Md5Util {
    private static Md5FileNameGenerator mGenerator;

    static {
        mGenerator = new Md5FileNameGenerator();
    }

    private Md5Util() {

    }

    public static String generate(String uri) {
        return mGenerator.generate(uri);
    }

    public static String generateStr(String uri){
        return mGenerator.generateStr(uri);
    }
}
