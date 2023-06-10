package com.example.raportani.controller;

import com.example.raportani.entity.Doktor;
import com.example.raportani.entity.enums.Role;
import com.example.raportani.service.IDoktorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/doktors")
public class DoktorController {
    private final IDoktorService doktorService;

    public DoktorController(IDoktorService doktorService) {
        this.doktorService = doktorService;
    }
    @GetMapping
    ResponseEntity<Page<Doktor>> getUsers(@RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "10") Integer pageSize ){
        return ResponseEntity.ok(doktorService.getAll(PageRequest.of(page,pageSize, Sort.by("id"))));
    }

    @GetMapping("/by-role")
    public ResponseEntity<List<Doktor>> getUsersByRole(@RequestParam Role role){
        return ResponseEntity.ok(doktorService.getUserByRole(role));
    }

    @GetMapping("/{id}")
    ResponseEntity<Doktor> getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(doktorService.getById(id));
    }
    @PostMapping
    ResponseEntity<Doktor> createUser(@RequestBody Doktor doktor){
        return ResponseEntity.ok(doktorService.save(doktor));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Void> getDeleteUserById(@PathVariable Integer id){
        doktorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
