package top.icss.novel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import top.icss.novel.entity.NovelChapter;

import java.util.List;

/**
 * @author cd.wang
 * @create 2020-10-19 14:36
 * @since 1.0.0
 */
@Service
public class NovelChapterService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<NovelChapter> list(String novId){
        Query query = new Query(Criteria.where("novId").is(novId));
        //升序
        query.with(Sort.by(Sort.Order.asc("chapterNo")));
        List<NovelChapter> novelChapters = mongoTemplate.find(query, NovelChapter.class);
        return novelChapters;
    }

    public NovelChapter details(String novId, String chapterId){
        Query query = new Query(Criteria.where("novId").is(novId)
                                        .and("chapterId").is(chapterId));
        NovelChapter chapter = mongoTemplate.findOne(query, NovelChapter.class);
        return chapter;
    }

    public NovelChapter pre(String novId, String chapterId){
        //上一页
        Query queryPre = new Query(Criteria.where("novId").is(novId)
                .and("chapterNo").lt(Integer.valueOf(chapterId)));
        //降序
        queryPre.with(Sort.by(Sort.Order.desc("chapterNo")));
        queryPre.limit(1);

        NovelChapter pre = mongoTemplate.findOne(queryPre, NovelChapter.class);
        return pre;
    }

    public NovelChapter next(String novId, String chapterId){
        //下一页
        Query queryNext = new Query(Criteria.where("novId").is(novId)
                .and("chapterNo").gt(Integer.valueOf(chapterId)));
        //升序
        queryNext.with(Sort.by(Sort.Order.asc("chapterNo")));
        queryNext.limit(1);
        NovelChapter next = mongoTemplate.findOne(queryNext, NovelChapter.class);
        return next;
    }
}
