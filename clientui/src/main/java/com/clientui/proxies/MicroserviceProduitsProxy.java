package com.clientui.proxies;

import com.clientui.beans.ProductBean;
import com.clientui.configuration.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
@FeignClient(name = "microservice-produits", url = "localhost:9001", configuration = FeignClientConfiguration.class)
public interface MicroserviceProduitsProxy {
    @GetMapping(value = "/Produits")
    List<ProductBean> listeDesProduits();

    @GetMapping(value = "/Produits/{id}")
    ProductBean recupererUnProduit(@PathVariable("id") int id);
}
