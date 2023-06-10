package com.example.raportani.repository;

import com.example.raportani.entity.Doktor;
import com.example.raportani.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDoktorRepository extends JpaRepository<Doktor, Integer> {
    boolean existsByHastaneKimlik(String hastaneKimlik);
    List<Doktor> findAllByRole(Role role);

    List<Doktor> findAllByRoleAndIdIsNotIn(Role role,List<Integer> idList);
}
