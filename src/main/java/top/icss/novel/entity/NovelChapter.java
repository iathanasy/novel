package top.icss.novel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author cd.wang
 * @create 2020-10-19 14:37
 * @since 1.0.0
 */
@Data
@Document(collection = "novel_chapter")
public class NovelChapter {
    //
    private long chapterId;
    //小说ID
    private String novId;
    //章节编号
    private long chapterNo;
    //章节标题
    private String title;
    //章节内容
    private String desc;
    //章节采集链接
    private String link;
    //浏览次数
    private long views;
    //章节字数
    private long textNum;
    //章节采集状态0正常，1失败
    private int status;
    //采集重试次数
    private int tryViews;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
}
