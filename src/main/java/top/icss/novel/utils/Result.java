package top.icss.novel.utils;

/**
 * @author cd.wang
 * @create 2021-01-19 15:48
 * @since 1.0.0
 */
public class Result {
    //响应码
    private int code;

    public Result(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
