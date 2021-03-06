package top.icss.novel.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.icss.novel.entity.Novel;
import top.icss.novel.utils.MongoUtil;
import top.icss.novel.utils.PageHelper;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author cd.wang
 * @create 2020-10-19 14:36
 * @since 1.0.0
 */
@Service
public class NovelService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Resource
    private MongoUtil mongoUtil;

    public PageHelper list(int pageNum, int pageSize, String cateId, String search){
        Criteria criteria = new Criteria();
        if(!StringUtils.isEmpty(cateId)){
            criteria.and("cateId").is(Integer.valueOf(cateId));
        }

        if(!StringUtils.isEmpty(search)){
            criteria.and("name").regex(search);
        }
        Query query = new Query(criteria);
        long count = mongoTemplate.count(query, Novel.class);

        mongoUtil.start(pageNum, pageSize, query);

        List<Novel> novels = mongoTemplate.find(query, Novel.class);
        //处理时间格式问题
        novels.stream().forEach((e)->{
            Date  chapterUpdatedAt= e.getChapterUpdatedAt();
            String format = DateUtil.format(chapterUpdatedAt, "yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTimeUtil.parse(format);
            e.setChapterUpdatedAt(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        });

        PageHelper pageHelper = mongoUtil.pageHelper(count, novels);
        return pageHelper;
    }

    public Novel book(String novId){
        Query query = new Query(Criteria.where("novId").is(novId));
        Novel entrY = mongoTemplate.findOne(query, Novel.class);
        return entrY;
    }
}
