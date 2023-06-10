package com.example.raportani.service.impl;

import com.example.raportani.common.GeneralException;
import com.example.raportani.entity.Rapor;
import com.example.raportani.repository.IRaporRepository;
import com.example.raportani.service.IRaporService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RaporService implements IRaporService {
    private final IRaporRepository raporRepository;

    public RaporService(IRaporRepository raporRepository) {
        this.raporRepository = raporRepository;
    }

    @Override
    public Rapor save(Rapor rapor) {
        if (StringUtils.isEmpty(rapor.getTaniBaslik())){
            throw new GeneralException("Tanı başlığı giriniz!");
        }
        if (rapor.getDoktor() == null) {
            throw new GeneralException("Raporu kim tanımlıyor, lütfen isminizi seçiniz!");
        }
        if (StringUtils.isEmpty(rapor.getTaniDetay())){
            throw new GeneralException("Tanının detaylarını açıklayınız!");
        }
        if (StringUtils.isEmpty(rapor.getRaporTarih())){
            throw new GeneralException("Lütfen rapor tarihini belirtiniz!");
        }
        if (StringUtils.isEmpty(rapor.getDosyaNumarasi())){
            throw new GeneralException("Dosya numarası boş bırakılamaz!");
        }
        return raporRepository.save(rapor);
    }

    @Override
    public Rapor getById(Integer id) {
        return raporRepository.findById(id).orElseThrow(()-> new GeneralException("Rapor kayıtlı değil!"));
    }

    @Override
    public List<Rapor> getAll() {
        return raporRepository.findAll();
    }

    @Override
    public Page<Rapor> getAll(Pageable pageable) {
        return raporRepository.findAllBy(pageable);
    }

    @Override
    public void delete(Integer id) {
        if (!raporRepository.existsById(id)) {
            throw new GeneralException("Rapor bulunamadı!");
        }
        raporRepository.deleteById(id);
    }

    public List<Rapor> getSiralananRaporlar() {
        return raporRepository.findAllByOrderByRaporTarihAsc();
    }
}
