package com.example.raportani.service.impl;

import com.example.raportani.common.GeneralException;
import com.example.raportani.entity.Doktor;
import com.example.raportani.entity.enums.Role;
import com.example.raportani.repository.IDoktorRepository;
import com.example.raportani.service.IDoktorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoktorService implements IDoktorService {
    private final IDoktorRepository doktorRepository;

    public DoktorService(IDoktorRepository doktorRepository) {
        this.doktorRepository = doktorRepository;
    }

    @Override
    public Doktor save(Doktor doktor) {
        if (doktor.getRole() == Role.DOKTOR) {
            if (doktor.getHastaneKimlik() == null || doktor.getHastaneKimlik().length() != 7) {
                throw new GeneralException("Geçersiz Hastane Kimlik!");
            }
            else if (doktorRepository.existsByHastaneKimlik(doktor.getHastaneKimlik())) {
                throw new GeneralException("Bu Hastane Kimlik Numarası Mevcut!");
            }
        }
        return doktorRepository.save(doktor);
    }

    @Override
    public Doktor getById(Integer id) throws GeneralException {
        Optional<Doktor> doktorOptional = doktorRepository.findById(id);
        if (doktorOptional.isEmpty()) {
            throw new GeneralException("User not found!");
        }
        return doktorOptional.get();
    }

    @Override
    public List<Doktor> getAll() {
        return doktorRepository.findAll();
    }

    @Override
    public Page<Doktor> getAll(Pageable pageable) {
        return doktorRepository.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        if (!doktorRepository.existsById(id)) {
            throw new GeneralException("User Not Found");
        }
        doktorRepository.deleteById(id);
    }


    @Override
    public List<Doktor> getUserByRole(Role role) {
        return doktorRepository.findAllByRole(role);
    }

}
