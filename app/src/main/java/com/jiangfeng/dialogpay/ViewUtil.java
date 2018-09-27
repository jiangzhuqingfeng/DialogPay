package com.jiangfeng.dialogpay;

/**
 * 2018/8/21 9:42
 *
 * @author LWH
 * 控件工具类
 */
class ViewUtil {
    private static long lastClickTime;

    /**
     * 判断是否过快点击
     *
     * @return true 是过快点击
     */
    synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
