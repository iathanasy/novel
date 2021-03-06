package top.icss.novel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author cd.wang
 * @create 2020-10-19 14:37
 * @since 1.0.0
 */
@Data
@Document(collection = "novel")
public class Novel {

    private String novId ;
    //小说名称
    private String name ;
    //小说描述
    private String desc;
    //小说封面
    private String cover;
    //小说分类
    private String cateId;
    //分类名称
    private String cateName;
    //小说作者
    private String author;
    //浏览次数
    private String views;
    //小说字数
    private String textNum;
    //小说章节数
    private String chapterNum;
    //最新章节时间
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date chapterUpdatedAt;
    //最新章节id
    private String chapterId;
    //最新章节标题
    private String chapterTitle;

    //书籍状态
    private String novStatus;
    //状态，0：入库，1：上架
    private int status;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

}
