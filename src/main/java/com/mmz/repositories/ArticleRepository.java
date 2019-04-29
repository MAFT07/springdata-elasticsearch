package com.mmz.repositories;

import com.mmz.entity.Article;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.awt.print.Pageable;
import java.util.List;

/**
 * @author: mamingze
 * @date: 2019-04-29 11:49
 * @description:
 */

public interface ArticleRepository extends ElasticsearchRepository<Article,Long> {
    List<Article> findByTitle(String title);
    List<Article> findByTitleOrContent(String title,String content);
    List<Article> findByTitleOrContent(String title, String content, Pageable pageable);
}

