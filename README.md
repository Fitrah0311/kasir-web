# 🛠️ Sistem Kasir & Inventaris Stok

Sistem aplikasi kasir dan manajemen inventaris stok barang berbasis web. Aplikasi ini dibangun menggunakan **Java Spring Boot** sebagai core backend server, dikombinasikan dengan teknologi **Thymeleaf** dan **Bootstrap** untuk menyajikan antarmuka pengguna (UI) yang dinamis, responsif, dan modern.
---

## 🚀 Fitur Utama
* **Dashboard Inventaris:** CRUD (Create, Read, Update, Delete) stok barang.
* **Sistem Kasir Otomatis:** Keranjang belanja multi-transaksi dengan pemotongan stok barang otomatis secara real-time.
* **Cetak Struk Thermal:** Fitur cetak nota struk belanja ramah kertas thermal (A4/80mm) langsung dari browser.
* **Laporan Excel:** Ekspor data inventaris ke format `.xlsx` menggunakan library SheetJS (kolom aksi otomatis tersembunyi demi kerapian).
* **Transient Session Security:** Autentikasi Admin Kasir yang aman, di mana sesi login akan otomatis keluar (*force logout*) jika tab/jendela browser ditutup.

---

## 🧰 Teknologi & Library yang Digunakan
* **Backend:** Java 17, Spring Boot 3.x (Spring Web, Spring Data JPA, Spring Security)
* **Frontend:** Thymeleaf Template Engine, Bootstrap 5.3, FontAwesome 6.4
* **Database:** MySQL / MariaDB (via XAMPP)
* **Excel Library:** SheetJS (XLSX.js v0.18.5 via CDN)

---

## ⚙️ Persyaratan Sistem (*Prerequisites*)
Sebelum menjalankan aplikasi, pastikan perangkat Anda sudah terpasang:
* **Java Development Kit (JDK) 17** atau versi di atasnya.
* **XAMPP** (untuk mengaktifkan layanan Apache & MySQL).
* **Maven** (sudah include via Maven Wrapper `mvnw`).
* **Browser Modern** (Google Chrome direkomendasikan).

---

## 🏃‍♂️ Cara Menjalankan Proyek dari Awal

### 1. Persiapan Database (XAMPP)
1. Buka **XAMPP Control Panel**, lalu aktifkan (**Start**) layanan **Apache** dan **MySQL**.
2. Buka browser dan pergi ke halaman [http://localhost/phpmyadmin](http://localhost/phpmyadmin).
3. Buat database baru bernama: `db_tokobangunan`.
4. *Konfigurasi tabel akan digenerate otomatis oleh Spring Data JPA saat aplikasi pertama kali dijalankan.*

### 2. Kloning Proyek
Buka terminal atau Git Bash, lalu jalankan perintah berikut:
```bash
git clone [https://github.com/](https://github.com/)[Fitrah0311]/[kasir-web].git
cd [kasir-web]
```

### 3. Run server
1. Cara Menjalankan Server
   - Pengguna Sistem Operasi Windows (VS Code): Buka terminal ketik perintah `\mvnw spring-boot:run`
   - Pengguna Sistem Operasi Linux / macOS: Buka terminal ketik perintah `chmod +x mvnw` `./mvnw spring-boot:run`
   - Tips Gamau Ribet : Buka file `TokoBangunanApplication.java` klik `Run`
   - <img width="1076" height="145" alt="image" src="https://github.com/user-attachments/assets/da6f042b-4eac-4755-a1c8-d4956db93e87" />
4. Akses aplikasi di: [http://localhost:8080](http://localhost:8080)

### 4. Preview Web
   ### Login Admin
   - Username: adminfajar
   - Password: fajarbaru2026
   <img width="1920" height="1015" alt="Screenshot 2026-06-24 052206" src="https://github.com/user-attachments/assets/f08740b6-5ee4-4883-a4bd-9652918139c1" />
   
   ### Tampilan Utama Web Kasir (Inventaris CRUD & Stok)
   <img width="1920" height="1019" alt="Screenshot 2026-06-23 092452" src="https://github.com/user-attachments/assets/e551d4d7-1e41-42a0-b889-c6c137de9135" />

   ### Pengelolaan Data Cetak Laporan
   <img width="1920" height="1017" alt="Screenshot 2026-06-23 092715" src="https://github.com/user-attachments/assets/385ae1c4-fc15-4142-a362-bf5dc2be9082" />

  ![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
   



