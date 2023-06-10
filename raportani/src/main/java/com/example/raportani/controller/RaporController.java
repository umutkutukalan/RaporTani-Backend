package com.example.raportani.controller;

import com.example.raportani.entity.Rapor;
import com.example.raportani.repository.IRaporRepository;
import com.example.raportani.service.IRaporService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
@RequestMapping("/api/rapor")
public class RaporController {
    private final IRaporService raporService;
    private IRaporRepository repo;

    public RaporController(IRaporService raporService) {
        this.raporService = raporService;
    }

    @GetMapping
    public ResponseEntity<Page<Rapor>> getLectures(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer pageSize ){
        return ResponseEntity.ok(raporService.getAll(PageRequest.of(page,pageSize, Sort.by("id"))));
    }

    @PostMapping
    public ResponseEntity<Rapor> createRapor(@RequestBody Rapor rapor){
        return ResponseEntity.ok(raporService.save(rapor));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Rapor> getRaporById(@PathVariable Integer id){
        return ResponseEntity.ok(raporService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Rapor> deleteRaporById(@PathVariable Integer id){
        raporService.delete(id);
        return ResponseEntity.ok().build();
    }
}
