package com.example.pfe.Repo;


import com.example.pfe.Domain.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepo extends JpaRepository<Paiement, Long> {
}
