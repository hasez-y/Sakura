package com.ssslzgn.common.base.net;

public enum HttpEnumStatus {

    NET_ERROR(-7, "网络错误"),
    UNKNOWN_ERROR(-8, "未知错误"),
    SERVICE_ERROR(-9, "服务器错误"),
    DATA_ERROR(-10, "数据出错了");

    private int status;
    private String desc;

    HttpEnumStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public static HttpEnumStatus getByValue(int status) {
        for (HttpEnumStatus x : values()) {
            if (x.getStatus() == status) {
                return x;
            }
        }
        return null;
    }

}
