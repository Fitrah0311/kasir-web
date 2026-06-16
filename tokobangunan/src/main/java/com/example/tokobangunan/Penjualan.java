package com.example.tokobangunan;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Penjualan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomorNota;
    private String namaPembeli;
    private String metodePembayaran;
    
    // Kita buat bertipe TEXT karena isinya kumpulan banyak barang (Format JSON String)
    @Column(columnDefinition = "TEXT")
    private String rincianBarang; 
    
    private Double totalHargaSemua; // Total akhir belanjaan
    private LocalDate tanggalTransaksi; 
}