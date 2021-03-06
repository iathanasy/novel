package top.icss.novel.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.icss.novel.entity.Maici;
import top.icss.novel.utils.MongoUtil;
import top.icss.novel.utils.PageHelper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cd.wang
 * @create 2020-10-19 14:36
 * @since 1.0.0
 */
@Service
public class MaiciService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Resource
    private MongoUtil mongoUtil;

    public PageHelper list(int pageNum, int pageSize, String search){
        Criteria criteria = new Criteria();

        if(!StringUtils.isEmpty(search)){
            criteria.and("title").regex(search);
        }
        Query query = new Query(criteria);
//        query.fields().include("title"); //包含该字段
        //不包含该字段
        query.fields().exclude("content");
        long count = mongoTemplate.count(query, Maici.class);

        mongoUtil.start(pageNum, pageSize, query);

        List<Maici> novels = mongoTemplate.find(query, Maici.class);

        PageHelper pageHelper = mongoUtil.pageHelper(count, novels);
        return pageHelper;
    }

    public Maici details(String id){
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        Maici entrY = mongoTemplate.findOne(query, Maici.class);
        return entrY;
    }
}
