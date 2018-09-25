package ar.edu.unlp.info.dssd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unlp.info.dssd.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
