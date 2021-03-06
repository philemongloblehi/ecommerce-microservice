package com.mproduits.web.controller;

import com.mproduits.configurations.ApplicationPropertiesConfiguration;
import com.mproduits.dao.ProductDao;
import com.mproduits.model.Product;
import com.mproduits.web.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
@RestController
public class ProductController implements HealthIndicator {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final ProductDao productDao;
    private final ApplicationPropertiesConfiguration appProperties;

    @Autowired
    public ProductController(ProductDao productDao, ApplicationPropertiesConfiguration appProperties) {
        this.productDao = productDao;
        this.appProperties = appProperties;
    }

    @GetMapping(value = "/Produits")
    public List<Product> listeDesProduits(){

        List<Product> products = productDao.findAll();

        if(products.isEmpty()) throw new ProductNotFoundException("Aucun produit n'est disponible à la vente");

        List<Product> listeProduitsLimitee = products.subList(0, appProperties.getLimitDeProduits());

        log.info("Récupération de la liste des produits");
        return listeProduitsLimitee;

    }

    @GetMapping( value = "/Produits/{id}")
    public Optional<Product> recupererUnProduit(@PathVariable int id) {

        Optional<Product> product = productDao.findById(id);

        if(!product.isPresent())  throw new ProductNotFoundException("Le produit correspondant à l'id " + id + " n'existe pas");

        return product;
    }

    @Override
    public Health health() {
        List<Product> products = productDao.findAll();
        if(products.isEmpty()) {
            return Health.down().build();
        }

        return Health.up().build();
    }
}

