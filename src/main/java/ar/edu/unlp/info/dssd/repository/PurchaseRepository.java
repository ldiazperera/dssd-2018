package ar.edu.unlp.info.dssd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.dssd.model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
