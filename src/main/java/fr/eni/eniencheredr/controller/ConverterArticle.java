package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.service.ArticleService.ArticleServiceImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConverterArticle implements Converter<String, Articles_Vendus> {

    private ArticleServiceImpl service;

    public ConverterArticle(ArticleServiceImpl service) {
        this.service = service;
    }

    public Articles_Vendus convert(String no_article) {
        Integer theid = Integer.parseInt(no_article);

        return service.findArticleById(theid);
    }
}
