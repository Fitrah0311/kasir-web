package com.example.tokobangunan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BarangController {

    @Autowired
    private BarangRepository barangRepository;

    @Autowired
    private PenjualanRepository penjualanRepository;

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

    @GetMapping("/penjualan")
    public String halamanPenjualan(Model model) {
        List<Penjualan> semuaPenjualan = penjualanRepository.findAll();
        // Menghitung total omset dari kolom baru totalHargaSemua
        double totalPendapatan = semuaPenjualan.stream().mapToDouble(Penjualan::getTotalHargaSemua).sum();
        
        model.addAttribute("daftarPenjualan", semuaPenjualan);
        model.addAttribute("totalPendapatan", totalPendapatan);
        model.addAttribute("daftarBarang", barangRepository.findAll());
        return "penjualan";
    }

    @PostMapping("/penjualan/simpan")
    public String simpanPenjualan(@RequestParam String namaPembeli,
                                  @RequestParam String metodePembayaran,
                                  @RequestParam Double totalHargaSemua,
                                  @RequestParam String dataKeranjangJson) {
        try {
            // Logika PBO Tingkat Lanjut: Parsing JSON string data barang menggunakan Jackson ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(dataKeranjangJson);

            // Loop tiap barang yang ada di dalam keranjang untuk potong stok di MySQL
            for (JsonNode node : rootNode) {
                Long barangId = node.get("id").asLong();
                int qtyJual = node.get("qty").asInt();

                Barang barang = barangRepository.findById(barangId).orElse(null);
                if (barang != null && barang.getStok() >= qtyJual) {
                    barang.setStok(barang.getStok() - qtyJual);
                    barangRepository.save(barang); // Update potong stok berhasil
                }
            }

            // Generate 1 Nomor Nota untuk semua barang tersebut
            String nomorNota = "INV-" + java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now()) + "-" + (int)(Math.random() * 900 + 100);

            // Simpan Nota Penjualan Utama ke Database
            Penjualan p = new Penjualan();
            p.setNomorNota(nomorNota);
            p.setNamaPembeli(namaPembeli);
            p.setMetodePembayaran(metodePembayaran);
            p.setRincianBarang(dataKeranjangJson); // Menyimpan struktur JSON kumpulan barang sebagai teks
            p.setTotalHargaSemua(totalHargaSemua);
            p.setTanggalTransaksi(LocalDate.now());
            penjualanRepository.save(p);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/penjualan";
    }
}