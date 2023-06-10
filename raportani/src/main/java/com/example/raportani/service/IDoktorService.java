package com.example.raportani.service;

import com.example.raportani.entity.Doktor;
import com.example.raportani.entity.User;
import com.example.raportani.entity.enums.Role;

import java.util.List;

public interface IDoktorService extends IService<Doktor>{
    List<Doktor> getUserByRole(Role role);
}
