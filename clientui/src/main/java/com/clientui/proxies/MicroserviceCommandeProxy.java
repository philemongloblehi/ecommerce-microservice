package com.clientui.proxies;

import com.clientui.beans.CommandeBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
@FeignClient(name = "microservice-commandes", url = "localhost:9002")
public interface MicroserviceCommandeProxy {
    @PostMapping(value = "/commandes")
    CommandeBean ajouterCommande(@RequestBody CommandeBean commande);
}
