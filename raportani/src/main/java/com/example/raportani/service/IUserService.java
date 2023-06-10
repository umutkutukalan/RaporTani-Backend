package com.example.raportani.service;

import com.example.raportani.entity.User;
import com.example.raportani.entity.enums.Role;

import java.util.List;

public interface IUserService extends IService<User> {
    List<User> getUserByRole(Role role);
    List<User> getPotentialUsers(List<Integer> ids);
}
