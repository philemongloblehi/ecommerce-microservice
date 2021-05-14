package com.clientui.controller;

import com.clientui.beans.CommandeBean;
import com.clientui.beans.PaiementBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceCommandeProxy;
import com.clientui.proxies.MicroservicePaiementProxy;
import com.clientui.proxies.MicroserviceProduitsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
@Controller
public class ClientController {
    private final MicroserviceProduitsProxy produitsProxy;
    private final MicroserviceCommandeProxy commandeProxy;
    private final MicroservicePaiementProxy paiementProxy;

    @Autowired
    public ClientController(MicroserviceProduitsProxy produitsProxy, MicroserviceCommandeProxy commandeProxy, MicroservicePaiementProxy paiementProxy) {
        this.produitsProxy = produitsProxy;
        this.commandeProxy = commandeProxy;
        this.paiementProxy = paiementProxy;
    }

    @GetMapping(value = "/")
    public String accueil(Model model) {
        List<ProductBean> produits = produitsProxy.listeDesProduits();
        model.addAttribute("produits", produits);
        return "Accueil";
    }

    @GetMapping(value = "/details-produit/{id}")
    public String ficheProduit(@PathVariable int id, Model model) {
        ProductBean produit = produitsProxy.recupererUnProduit(id);
        model.addAttribute("produit", produit);
        return "FicheProduit";
    }

    @GetMapping(value = "/commander-produit/{idProduit}/{montant}")
    public String passerCommande(@PathVariable int idProduit, @PathVariable Double montant, Model model) {
        CommandeBean commande = new CommandeBean();
        commande.setProductId(idProduit);
        commande.setQuantite(1);
        commande.setDateCommande(new Date());

        CommandeBean commandeAjoutee = commandeProxy.ajouterCommande(commande);

        model.addAttribute("commande", commandeAjoutee);
        model.addAttribute("montant", montant);

        return "Paiement";
    }

    @GetMapping(value = "/payer-commande/{idCommande}/{montantCommande}")
    public String payerCommande(@PathVariable int idCommande, @PathVariable Double montantCommande, Model model) {
        PaiementBean paiementAExcecuter = new PaiementBean();
        paiementAExcecuter.setIdCommande(idCommande);
        paiementAExcecuter.setMontant(montantCommande);
        paiementAExcecuter.setNumeroCarte(this.numcarte());

        ResponseEntity<PaiementBean> paiement = paiementProxy.payerUneCommande(paiementAExcecuter);

        boolean paiementAccepte = HttpStatus.CREATED == paiement.getStatusCode();

        model.addAttribute("paiementOk", paiementAccepte);
        return "Confirmation";
    }

    private Long numcarte() {
        return ThreadLocalRandom.current().nextLong(1000000000000000L,9000000000000000L );
    }

}
