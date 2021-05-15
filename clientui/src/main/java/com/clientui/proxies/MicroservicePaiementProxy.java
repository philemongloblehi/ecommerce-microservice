package com.clientui.proxies;

import com.clientui.beans.PaiementBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-paiement")
public interface MicroservicePaiementProxy {
    @PostMapping(value = "/microservice-paiement/paiement")
    ResponseEntity<PaiementBean> payerUneCommande(@RequestBody PaiementBean paiement);
}
