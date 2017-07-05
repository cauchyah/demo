package com.lulu.lin.mac;

/**
 * Created by lin on 2017/6/27.
 */

public interface OnItemCallbackListener {
    /**
     * @param fromPosition 起始位置
     * @param toPosition 移动的位置
     */
    void onMove(int fromPosition, int toPosition);
    void onSwipe(int position);
}
