package com.hongcheng.fruitmall.common.util;

/**
 * 位标记器， 最大支持31个{ [0, 30] }位置的标记
 * Created by wanghongcheng on 2018/04/20.
 */
public class BitMarker {
    private int value = 0;

    /**
     * 添加一个标记
     *
     * @param position 标记的位置 [0, 30]
     * @return
     */
    public BitMarker mark(int position) {
        if (isValidPosition(position)) {
            value |= (1 << position);
        }
        return this;
    }


    /**
     * 移除一个标记
     *
     * @param position 标记的位置 [0, 30]
     * @return
     */
    public BitMarker remove(int position) {
        if (marked(position)) {
            value ^= (1 << position);
        }
        return this;
    }

    /**
     * 判断所给位置是否被标记了
     *
     * @param position 位置 [0, 30]
     * @return
     */
    public boolean marked(int position) {
        if (!isValidPosition(position)) {
            return false;
        }
        int p = 1 << position;
        return (value & p) == p;
    }

    @Override
    public String toString() {
        return value + "";
    }

    /**
     * 整数最大不超过32位
     *
     * @param position
     * @return
     */
    private boolean isValidPosition(int position) {
        return position < 31 && position >= 0;
    }
}
