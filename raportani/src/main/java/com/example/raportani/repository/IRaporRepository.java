package com.example.raportani.repository;

import com.example.raportani.entity.Rapor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRaporRepository extends JpaRepository<Rapor, Integer> {
    Page<Rapor> findAllBy(Pageable pageable);
    List<Rapor> findAllByOrderByRaporTarihAsc();

}
