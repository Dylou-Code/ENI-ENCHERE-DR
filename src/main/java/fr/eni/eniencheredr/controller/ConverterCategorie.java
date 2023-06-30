package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.service.CategorieService.CategorieService;
import fr.eni.eniencheredr.service.CategorieService.CategorieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConverterCategorie implements Converter<String, Categories> {
    private CategorieServiceImpl service;

    public ConverterCategorie(CategorieServiceImpl service) {
        this.service = service;
    }

    public Categories convert(String no_categorie) {
        Integer theId = Integer.parseInt(no_categorie);

        return service.getCategory(theId);
    }

}
