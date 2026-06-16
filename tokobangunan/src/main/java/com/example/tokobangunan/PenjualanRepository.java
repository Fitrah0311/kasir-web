package com.example.tokobangunan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenjualanRepository extends JpaRepository<Penjualan, Long> {
    // Otomatis mewarisi fungsi CRUD database penjualan
}