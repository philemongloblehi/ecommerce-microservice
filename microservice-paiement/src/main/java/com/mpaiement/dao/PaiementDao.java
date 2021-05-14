package com.mpaiement.dao;

import com.mpaiement.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Philémon Globléhi <philemon.globlehi@gmail.com>
 */
@Repository
public interface PaiementDao extends JpaRepository<Paiement, Integer>{

    Paiement findByidCommande(int idCommande);
}
