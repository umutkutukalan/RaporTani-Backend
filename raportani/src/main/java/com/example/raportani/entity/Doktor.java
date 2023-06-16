package com.example.raportani.entity;

import com.example.raportani.entity.enums.Gender;
import com.example.raportani.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doktor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doktor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String ad;
    @Column
    private String soyad;

    @Column(name = "hastane_kimlik", length = 7)
    private String hastaneKimlik;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @Column(name = "drole")
    private Role role;

}
