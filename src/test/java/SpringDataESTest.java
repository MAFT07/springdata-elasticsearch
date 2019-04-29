import com.mmz.entity.Article;
import com.mmz.repositories.ArticleRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;
import java.util.Optional;

/**
 * @author: mamingze
 * @date: 2019-04-29 13:52
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringDataESTest {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ElasticsearchTemplate template;
    @Test
    public void createIndex() throws Exception {
        //创建索引，并配置映射关系
        template.createIndex(Article.class);
        //配置映射关系
        //template.putMapping();
    }

    @Test
    public void addDocument() throws Exception {
        //创建一个Article对象
        for (int i = 10; i <= 20; i++) {
            Article article = new Article();
            article.setId(i);
            article.setTitle("交通运输部：“五一”假期四天收费公路小客车免费通行"+i);
            article.setContent("根据《重大节假日免收小型客车通行费实施方案》，今年“五一”4天假期，全国"+i);
            articleRepository.save(article);
        }

    }
    @Test
    public void deleteDocumentById() throws Exception {
        articleRepository.deleteById(1l);

    }
    @Test
    //更新基于Lucene,本质是先删除再增加,直接使用add
    public void updateDocument() throws Exception {

    }
    @Test
    public void findAll() throws Exception {
        Iterable<Article> articles = articleRepository.findAll();
        articles.forEach(a -> System.out.println(a));
    }
    @Test
    public void findById() throws Exception {
        Optional<Article> optional = articleRepository.findById(2l);
        Article article = optional.get();
        System.out.println(article);
    }
    @Test
    public void testFindByTitle() throws Exception {
        List<Article> list = articleRepository.findByTitle("运输部通行免费");
        list.stream().forEach(a -> System.out.println(a));
    }
    @Test
    public void testFindByTitleOrContent() throws Exception {
        PageRequest pageable = PageRequest.of(1,5);
        List<Article> list = articleRepository.findByTitleOrContent("20", "重大节假日免收费小型客车");
        list.stream().forEach(a-> System.out.println(a));
    }
    @Test
    //使用原声的查询条件查询
    public void testNativeSearchQuery() throws Exception {
        //创建一个查询对象
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("运输部通行免费").defaultField("title"))
                .withPageable(PageRequest.of(0,5))
                .build();
        List<Article> articleList = template.queryForList(query,Article.class);
        articleList.forEach(a-> System.out.println(a));
    }


}
