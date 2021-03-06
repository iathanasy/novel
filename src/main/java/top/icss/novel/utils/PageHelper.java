package top.icss.novel.utils;

import lombok.Data;

import java.util.List;

/**
 * @author cd.wang
 * @create 2020-10-19 15:21
 * @since 1.0.0
 */
@Data
public class PageHelper<T> {
    private long currentPage;
    private long total;
    private long pageSize;
    private long totalPage;
    private List<T> list;

    public PageHelper(long pageNum, long total, long pageSize, List<T> list) {
        this.currentPage = pageNum;
        this.total = total;
        this.pageSize = pageSize;
        this.list = list;
        this.totalPage = (int)Math.ceil((double)total/pageSize);
    }

    public PageHelper(long pageNum, long pageSize, List<T> list) {
        this.currentPage = pageNum;
        this.pageSize = pageSize;
        this.list = list;
    }
}
