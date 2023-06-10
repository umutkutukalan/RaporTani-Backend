package com.example.raportani.service.impl;

import com.example.raportani.common.GeneralException;
import com.example.raportani.entity.User;
import com.example.raportani.entity.enums.Role;
import com.example.raportani.repository.IUserRepository;
import com.example.raportani.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        if (user.getRole() == Role.HASTA) {
            if (user.getTcKimlik() == null || user.getTcKimlik().length() != 11) {
                throw new GeneralException("Geçersiz TC Kimlik!");
            }
            else if (userRepository.existsByTcKimlik(user.getTcKimlik())) {
                throw new GeneralException("Bu TC Kimlik Numarası Mevcut!");
            }
        }
        return userRepository.save(user);
    }

    @Override
    public User getById(Integer id) throws GeneralException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new GeneralException("User not found!");
        }
        return userOptional.get();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new GeneralException("User Not Found");
        }
        userRepository.deleteById(id);
    }


    @Override
    public List<User> getUserByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public List<User> getPotentialUsers(List<Integer> ids) {
        if (ids.isEmpty()) {
            return getUserByRole(Role.HASTA);
        }
        return userRepository.findAllByRoleAndIdIsNotIn(Role.HASTA,ids);
    }
}
