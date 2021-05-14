package com.mpaiement.web.controller;

import com.mpaiement.dao.PaiementDao;
import com.mpaiement.model.Paiement;
import com.mpaiement.web.exceptions.PaiementExistantException;
import com.mpaiement.web.exceptions.PaiementImpossibleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
@RestController
public class PaiementController {

    PaiementDao paiementDao;

    @Autowired
    public PaiementController(PaiementDao paiementDao) {
        this.paiementDao = paiementDao;
    }

    @PostMapping(value = "/paiement")
    public ResponseEntity<Paiement>  payerUneCommande(@RequestBody Paiement paiement){
        Paiement paiementExistant = paiementDao.findByidCommande(paiement.getIdCommande());
        if(paiementExistant != null) throw new PaiementExistantException("Cette commande est déjà payée");

        Paiement nouveauPaiement = paiementDao.save(paiement);

        if(nouveauPaiement == null) throw new PaiementImpossibleException("Erreur, impossible d'établir le paiement, réessayez plus tard");

        return new ResponseEntity<Paiement>(nouveauPaiement, HttpStatus.CREATED);

    }




}
