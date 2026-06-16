package com.example.tokobangunan;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity
@Data // Sakti! Lombok otomatis bikinin Getter, Setter, dan Constructor di balik layar
public class Barang {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String namaBarang;   // Contoh: Semen Tiga Roda, Besi Beton
    private String kategori;     // Contoh: Material Dasar, Cat, Besi
    private Double hargaJual;    // Harga per satuan
    private Integer stok;        // Jumlah stok yang tersisa
}