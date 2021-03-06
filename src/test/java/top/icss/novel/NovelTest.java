package top.icss.novel;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import top.icss.novel.entity.Novel;
import top.icss.novel.entity.NovelChapter;
import top.icss.novel.utils.MongoUtil;
import top.icss.novel.utils.PageHelper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cd.wang
 * @create 2020-10-19 14:53
 * @since 1.0.0
 */
@SpringBootTest
public class NovelTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Resource
    private MongoUtil mongoUtil;


    //简单查
    @Test
    public void query(){
        String id = "115446";
        //单条
        Query query = new Query(Criteria.where("novId").is(id));//可累加条件
        Novel entrY = mongoTemplate.findOne(query, Novel.class);
        System.out.println(entrY);
        //多条
        Query query2 = new Query(Criteria.where("name").regex("凡人"));//可累加条件
        List<Novel> entries = mongoTemplate.find(query2, Novel.class);
        System.out.println(entries);
    }

    @Test
    void findByName(){
//        Query query = new Query(Criteria.where("name").is("凡人修仙传").and("novId").is("9148"));
        Novel novel = new Novel();
        novel.setName("凡人修仙传");
        novel.setNovId("9148");
        Query query = new Query(Criteria.byExample(novel));
        List<Novel> students = mongoTemplate.find(query, Novel.class);
        System.out.println(students);
    }

    @Test
    void findByPage(){
        Query query = new Query(new Criteria());
        mongoUtil.start(2, 2, query);

        List<Novel> novels = mongoTemplate.find(query, Novel.class);
        long count = mongoTemplate.count(query, Novel.class);
        PageHelper pageHelper = mongoUtil.pageHelper(count, novels);
        System.out.println(pageHelper);

    }

    @Test
    void findPre(){
//        Query query = new Query(Criteria.where("name").is("凡人修仙传").and("novId").is("9148"));
        String novId = "9148";
        Integer chapterId = 4309393;

        Query query = new Query(Criteria.where("novId").is(novId)
                .and("chapterId").is(Integer.valueOf(chapterId)));
        NovelChapter chapter = mongoTemplate.findOne(query, NovelChapter.class);
        System.out.println(chapter);

        Query queryPre = new Query(Criteria.where("novId").is(novId)
                .and("chapterId").lt(4309393)).limit(1); //上一页
        NovelChapter pre = mongoTemplate.findOne(queryPre, NovelChapter.class);
        System.out.println(pre);

        Query queryNext = new Query(Criteria.where("novId").is(novId)
                .and("chapterId").gt(4309393)).limit(1); //下一页
        NovelChapter next = mongoTemplate.findOne(queryNext, NovelChapter.class);
        System.out.println(next);
    }


}
