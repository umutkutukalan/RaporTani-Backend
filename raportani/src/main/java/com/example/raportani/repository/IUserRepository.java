package com.example.raportani.repository;

import com.example.raportani.entity.User;
import com.example.raportani.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    boolean existsByTcKimlik(String tcKimlik);
    List<User> findAllByRole(Role role);

    List<User> findAllByRoleAndIdIsNotIn(Role role,List<Integer> idList);

}
