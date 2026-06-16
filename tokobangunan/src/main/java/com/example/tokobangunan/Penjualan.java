package com.example.tokobangunan;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Penjualan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String namaBarang;
    private Integer jumlahJual;
    private Double totalHarga;
    private LocalDate tanggalTransaksi; // Penting buat filter mingguan / bulanan
}