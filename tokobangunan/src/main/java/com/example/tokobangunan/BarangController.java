package com.example.tokobangunan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BarangController {

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private PenjualanRepository penjualanRepository;

    // 1. HALAMAN UTAMA: STOK BARANG
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("daftarBarang", barangRepository.findAll());
        return "index";
    }

    @PostMapping("/tambah")
    public String tambahBarang(@ModelAttribute Barang barang) {
        barangRepository.save(barang);
        return "redirect:/";
    }

    // 2. HALAMAN BARU: HASIL PENJUALAN KASIR
    @GetMapping("/penjualan")
    public String halamanPenjualan(Model model) {
        List<Penjualan> semuaPenjualan = penjualanRepository.findAll();
        
        // Hitung Total Pendapatan Kumulatif
        double totalPendapatan = semuaPenjualan.stream().mapToDouble(Penjualan::getTotalHarga).sum();
        
        model.addAttribute("daftarPenjualan", semuaPenjualan);
        model.addAttribute("totalPendapatan", totalPendapatan);
        model.addAttribute("daftarBarang", barangRepository.findAll()); // Buat dropdown pilihan barang di kasir
        return "penjualan";
    }

    // LOGIKA SAKTI KASIR: Simpan Penjualan + Potong Stok Otomatis (Nilai A+ di PBO)
    @PostMapping("/penjualan/simpan")
    public String simpanPenjualan(@RequestParam Long barangId, @RequestParam Integer jumlahJual) {
        Barang barang = barangRepository.findById(barangId).orElse(null);
        
        if (barang != null && barang.getStok() >= jumlahJual) {
            // A. Kurangi stok barang
            barang.setStok(barang.getStok() - jumlahJual);
            barangRepository.save(barang);

            // B. Catat ke tabel penjualan
            Penjualan p = new Penjualan();
            p.setNamaBarang(barang.getNamaBarang());
            p.setJumlahJual(jumlahJual);
            p.setTotalHarga(barang.getHargaJual() * jumlahJual);
            p.setTanggalTransaksi(LocalDate.now()); // otomatis tanggal hari ini (2026)
            penjualanRepository.save(p);
        }
        return "redirect:/penjualan";
    }
}