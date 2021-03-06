package com.clientui.proxies;

import com.clientui.beans.CommandeBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-commandes")
public interface MicroserviceCommandeProxy {
    @PostMapping(value = "/microservice-commandes/commandes")
    CommandeBean ajouterCommande(@RequestBody CommandeBean commande);
}
