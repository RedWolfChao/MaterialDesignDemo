package redwolf.com.materialdesigndemo;

import java.io.Serializable;

/**
 * @作者 RedWolf
 * @时间 2017/4/25 17:14
 * @简介 MeiZi.java
 */


public class MeiZi implements Serializable {
    private String meiZiName;
    private int meiZiId;

    public MeiZi(String meiZiName, int meiZiId) {
        this.meiZiName = meiZiName;
        this.meiZiId = meiZiId;
    }

    public String getMeiZiName() {
        return meiZiName;
    }

    public void setMeiZiName(String meiZiName) {
        this.meiZiName = meiZiName;
    }

    public int getMeiZiId() {
        return meiZiId;
    }

    public void setMeiZiId(int meiZiId) {
        this.meiZiId = meiZiId;
    }
}
