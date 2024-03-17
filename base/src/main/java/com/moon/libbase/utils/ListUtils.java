package com.moon.libbase.utils;
// _ooOoo_  
// o8888888o  
// 88" . "88  
// (| -_- |)  
//  O\ = /O  
// ___/`---'\____  
// .   ' \\| |// `.  
// / \\||| : |||// \  
// / _||||| -:- |||||- \  
// | | \\\ - /// | |  
// | \_| ''\---/'' | |  
// \ .-\__ `-` ___/-. /  
// ___`. .' /--.--\ `. . __  
// ."" '< `.___\_<|>_/___.' >'"".  
// | | : `- \`.;`\ _ /`;.`/ - ` : | |  
// \ \ `-. \_ __\ /__ _/ .-` / /  
// ======`-.____`-.___\_____/___.-`____.-'======  
// `=---='  
//          .......................
//           佛曰：bug泛滥，我已瘫痪！

import java.util.List;

/**
 * TeachAssistant
 * create by NiMa.Wang on 2019/12/24
 */

public class ListUtils {
    public static boolean isEmpty(List list) {
        if (list != null && list.size() > 0) {
            return false;
        }
        return true;
    }
}
