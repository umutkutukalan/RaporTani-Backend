package com.example.raportani.entity;

import com.example.raportani.entity.enums.Gender;
import com.example.raportani.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(name = "tc_kimlik",length=11)
    private String tcKimlik;
    @Column
    private String ad;
    @Column
    private String soyad;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @Column(name = "urole")
    private Role role;
}