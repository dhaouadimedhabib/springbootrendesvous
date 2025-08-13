package com.example.pfe.Repo;


import com.example.pfe.Domain.Professionnel;
import com.example.pfe.Domain.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepo extends JpaRepository<Services, Long> {
    boolean existsByProfessionnel(Professionnel professionnel);
    Optional<Services> findByProfessionnel_IdProfessionnel(Long professionnelId);

    @Query("SELECT s FROM Services s WHERE s.professionnel = :professionnel")
    Services findByProfessionnel(@Param("professionnel") Professionnel professionnel);

    List<Services> findByNom(String nom); // Remplacez 'nom' par le bon attribut si nécessaire

}
