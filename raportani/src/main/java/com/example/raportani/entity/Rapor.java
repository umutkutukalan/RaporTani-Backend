package com.example.raportani.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rapor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rapor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(nullable = false)
    private String dosyaNumarasi;

    @Column()
    private String taniBaslik;
    @Column()
    private String taniDetay;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column()
    private Date raporTarih;

    @Column(nullable = true, length = 64)
    private String raporFotograf;

    @ManyToOne
    @JoinColumn(name = "doktor_id")
    private Doktor doktor;

    public Integer getDoktorId(){
        if (doktor != null) {
            return doktor.getId();
        }
        return null;
    }

    @ManyToMany
    @JoinTable(
            name = "user_rapor",
            joinColumns = { @JoinColumn(name = "rapor_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }
    )
    private List<User> hastalar;
}
