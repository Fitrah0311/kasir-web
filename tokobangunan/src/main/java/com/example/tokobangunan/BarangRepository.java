package com.example.tokobangunan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangRepository extends JpaRepository<Barang, Long> {
    // Otomatis mewarisi semua fungsi CRUD (findAll, save, delete) dari Spring Data JPA!
}