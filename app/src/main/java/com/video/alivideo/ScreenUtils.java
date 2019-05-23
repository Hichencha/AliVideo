package com.video.alivideo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtils {
  /**
   * 获取宽度
   *
   * @param mContext 上下文
   * @return 宽度值，px
   */
  public static int getWidth(Context mContext) {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    ((WindowManager) mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE))
        .getDefaultDisplay().getMetrics(displayMetrics);
    return displayMetrics.widthPixels;
  }

  /**
   * 获取高度
   *
   * @param mContext 上下文
   * @return 高度值，px
   */
  public static int getHeight(Context mContext) {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    ((WindowManager) mContext.getApplicationContext().getSystemService(Context.WINDOW_SERVICE))
        .getDefaultDisplay().getMetrics(displayMetrics);
    return displayMetrics.heightPixels;
  }

  /**
   * 是否在屏幕右侧
   *
   * @param mContext 上下文
   * @param xPos     位置的x坐标值
   * @return true：是。
   */
  public static boolean isInRight(Context mContext, int xPos) {
    return (xPos > getWidth(mContext) / 2);
  }

  /**
   * 是否在屏幕左侧
   *
   * @param mContext 上下文
   * @param xPos     位置的x坐标值
   * @return true：是。
   */
  public static boolean isInLeft(Context mContext, int xPos) {
    return (xPos < getWidth(mContext) / 2);
  }

}
