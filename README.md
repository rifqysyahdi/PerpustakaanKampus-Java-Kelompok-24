# Sistem Manajemen Perpustakaan Kampus
Program Java berbasis konsol untuk membantu petugas perpustakaan kampus mengurus kategori buku, koleksi, lokasi rak, antrean mahasiswa, peminjaman, sampai pembatalan transaksi. Semuanya jalan dalam satu program dengan menu interaktif, dan di dalamnya kami memakai enam struktur data yang diminta di tugas: Array 1D, 2D, 3D, ArrayList, Stack, dan Queue.

## Tugas Pemrograman Java
- **Mata Kuliah:** Pemrograman Berorientasi Objek
- **Dosen Pengampu:** M. Ibadurrahman Arrasyid Supriyanto M.Kom.
- **Program Studi:** Sistem Informasi
- **Angkatan:** 2025
- **Kelompok:** 24

## Anggota Kelompok 24
1. Kardila Varani Barutu - 2509116009 - Kelas A
2. Zahra Maysita - 2509116015 - Kelas A
3. Mohammed Rifqy Syahdi - 2509116037 - Kelas A
4. Nabila Fitriani - 2509116063 - Kelas B

## Struktur File Proyek
```text
PerpustakaanKampus-Java-Kelompok-24/
├── assets/                                  
│   ├── 01-kategori-buku.jpeg
│   ├── 02-tambah-buku.jpeg
│   ├── 03-tampilkan-buku.jpeg
│   ├── 04-hapus-buku.jpeg
│   ├── 05-lokasi-valid.jpeg
│   ├── 06-lokasi-invalid.jpeg
│   ├── 07-antrean-tambah.jpeg
│   ├── 08-antrean-panggil.jpeg
│   ├── 09-peminjaman-valid.jpeg
│   ├── 10-peminjaman-kategori-salah.jpeg
│   ├── 11-peminjaman-salah-3x.jpeg
│   ├── 12-undo-transaksi.jpeg
│   └── 13-keluar-program.jpeg
├── src/
│   └── com/mycompany/sistemperpustakaan/
│       └── SistemPerpustakaan.java          
├── .gitignore
└── README.md
```

## Studi Kasus dan Tujuan Program
Bayangkan petugas perpustakaan kampus di hari yang ramai. Mahasiswa antre di loket, buku baru datang dan harus dicatat, ada yang menanyakan di rak mana buku tertentu disimpan, dan di akhir pekan petugas ingin tahu kategori apa yang paling sering dipinjam. Kalau ada salah catat, transaksinya juga harus bisa dibatalkan.

Program ini kami buat untuk menjawab keadaan seperti itu. Tujuan kami ada dua. Yang pertama, menyediakan satu aplikasi konsol sederhana yang menunya gampang diikuti. Yang kedua, belajar memakai enam struktur data di tempat yang memang cocok untuknya, supaya kami paham kapan array dipakai dan kapan koleksi dinamis lebih pas.

## Penerapan Enam Struktur Data

### Array 1 Dimensi
Variabelnya `String[] kategoriBuku`, isinya empat kategori: Pemrograman, Matematika, Jaringan Komputer, dan Basis Data. Jumlah kategori kami anggap tetap, jadi array cocok. Indeks 0 sampai 3 dipakai lagi di menu peminjaman sebagai nomor kategori.

### Array 2 Dimensi
Variabelnya `int[][] statPinjam`, ukurannya 4 x 7. Barisnya mewakili kategori, kolomnya mewakili hari dari Senin (0) sampai Minggu (6). Jadi `statPinjam[2][4]` adalah jumlah peminjaman buku Jaringan Komputer di hari Jumat. Setiap peminjaman yang valid menambah satu pada sel yang sesuai.

### Array 3 Dimensi
Variabelnya `String[][][] lokasiBuku`, ukurannya 3 x 5 x 10. Kami membayangkannya sebagai gedung dengan 3 lantai, tiap lantai punya 5 rak, dan tiap rak punya 10 posisi buku. Isi selnya adalah judul buku yang disimpan di koordinat itu, dan sel yang kosong berarti belum ada buku di sana.

### ArrayList
Variabelnya `ArrayList<String> daftarBuku`. Jumlah buku di perpustakaan terus berubah, sehingga kami butuh wadah yang ukurannya bisa menyesuaikan sendiri. ArrayList mendukung tambah, tampil, dan hapus buku tanpa kami mengatur kapasitas.

### Stack
Variabelnya `Stack<String> riwayatTransaksi`. Tiap peminjaman yang berhasil dicatat dimasukkan ke tumpukan lewat `push()`, jadi transaksi paling baru selalu ada di puncak. Sifat LIFO ini yang kami manfaatkan saat membatalkan transaksi. Di menu pembatalan, pengguna menyebut nama peminjam, lalu program menelusuri tumpukan dari atas dan membatalkan transaksi terbaru atas nama itu.

### Queue
Variabelnya `Queue<String> antrianMhs`, dengan `LinkedList` sebagai objek di belakangnya. Mahasiswa yang datang lebih dulu dipanggil lebih dulu (FIFO), seperti antrean di loket sungguhan.

## Penjelasan Source Code
Bagian ini menelusuri `SistemPerpustakaan.java` dari atas sampai bawah. Kodenya kami tampilkan utuh per bagian, lalu dijelaskan satu per satu dengan bahasa kami sendiri.

### 1. Package dan import
```java
package com.mycompany.sistemperpustakaan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
```
Baris `package` menunjukkan bahwa file ini berada di paket `com.mycompany.sistemperpustakaan`. Letak foldernya di `src/` harus mengikuti nama paket itu.

Lima baris `import` memanggil kelas yang tidak otomatis tersedia di Java. `ArrayList` dipakai untuk daftar buku, `LinkedList` dan `Queue` untuk antrean, `Scanner` untuk membaca ketikan dari keyboard, dan `Stack` untuk riwayat transaksi.

### 2. Kelas utama dan deklarasi variabel
```java
public class SistemPerpustakaan {
    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {

            String[] kategoriBuku = {"Pemrograman", "Matematika", "Jaringan Komputer", "Basis Data"};
            int[][] statPinjam = new int[4][7]; // 4 kategori, 7 hari
            String[][][] lokasiBuku = new String[3][5][10]; // Lantai, Rak, Posisi

            ArrayList<String> daftarBuku = new ArrayList<>();
            Stack<String> riwayatTransaksi = new Stack<>();
            Queue<String> antrianMhs = new LinkedList<>();

            boolean lanjut = true;
```
`public class SistemPerpustakaan` adalah kelas utama, dan namanya harus sama dengan nama file. Program mulai berjalan dari `public static void main`, yang selalu jadi pintu masuk program Java.

Baris `try (Scanner input = new Scanner(System.in))` membuat objek `Scanner` yang membaca dari keyboard (`System.in`). Dengan bentuk try-with-resources ini, `Scanner` ditutup otomatis saat blok `try` selesai, jadi kami tidak perlu menulis `close()` sendiri.

`kategoriBuku` adalah array 1D bertipe `String` yang langsung diisi empat nama kategori. `statPinjam` adalah array 2D bertipe `int` berukuran 4 x 7, dan semua selnya otomatis bernilai 0. `lokasiBuku` adalah array 3D bertipe `String` berukuran 3 x 5 x 10, dan semua selnya awalnya `null`, artinya belum ada buku. Tulisan setelah `//` hanyalah komentar pengingat dan tidak ikut dijalankan.

Tiga baris berikutnya membuat koleksi yang masih kosong: `daftarBuku` (ArrayList), `riwayatTransaksi` (Stack), dan `antrianMhs` (Queue). Tulisan `<String>` memberi tahu Java bahwa isinya berupa teks. Antrean dideklarasikan sebagai `Queue` tetapi dibuat dari `LinkedList`, karena `Queue` hanya aturan main dan butuh kelas nyata yang menjalankannya.

Variabel `lanjut` bertipe `boolean` dan jadi penanda perulangan utama. Selama nilainya `true`, menu akan terus tampil.

### 3. Perulangan menu utama
```java
while (lanjut) {
    System.out.println("\n=== SISTEM MANAJEMEN PERPUSTAKAAN KAMPUS ===");
    System.out.println("1. Tampilkan Kategori Buku");
    System.out.println("2. Kelola Daftar Buku");
    System.out.println("3. Cari/Simpan Lokasi Buku");
    System.out.println("4. Kelola Antrian Mahasiswa");
    System.out.println("5. Catat Peminjaman & Statistik");
    System.out.println("6. Batalkan Transaksi Terakhir (Undo)");
    System.out.println("7. Keluar");
    System.out.print("Pilih menu : ");

    int pilihan = input.nextInt();
    input.nextLine();

    switch (pilihan) {
```
`while (lanjut)` mengulang seluruh isi menu sampai `lanjut` diubah menjadi `false`, yang terjadi di menu 7.

Delapan baris `System.out.println` pertama menampilkan judul dan tujuh pilihan menu. Tanda `\n` di awal judul memberi satu baris kosong supaya tampilannya tidak menempel dengan keluaran sebelumnya. Baris `System.out.print("Pilih menu : ")` memakai `print` tanpa `ln`, sehingga kursor tetap sebaris dengan pertanyaannya.

`int pilihan = input.nextInt()` membaca angka pilihan dan menyimpannya di `pilihan`. Ada satu hal yang sempat membuat kami bingung: `nextInt()` tidak ikut mengambil tombol Enter yang diketik. Kalau dibiarkan, `nextLine()` berikutnya akan langsung terlewati. Karena itu ada `input.nextLine()` yang hasilnya tidak disimpan, tugasnya hanya membuang sisa Enter.

`switch (pilihan)` memilih blok yang dijalankan sesuai angka tadi. Kami memakai bentuk `case 1 -> { ... }` dari Java modern, yang tidak butuh `break` untuk mencegah eksekusi tembus ke case berikutnya.

### 4. Case 1: tampilkan kategori (Array 1D)
```java
case 1 -> {
    System.out.println("\n-- Kategori Buku --");
    for (int i = 0; i < kategoriBuku.length; i++) {
        System.out.println((i + 1) + ". " + kategoriBuku[i]);
    }
}
```
Baris pertama mencetak judul bagian. Perulangan `for` berjalan dari `i = 0` selama `i` masih lebih kecil dari `kategoriBuku.length` (yaitu 4), dan `i++` menambah `i` satu per putaran. Di tiap putaran, program mencetak nomor urut dan nama kategori pada indeks `i`. Nomornya ditulis `i + 1` karena indeks array dimulai dari 0, sedangkan orang terbiasa melihat daftar yang dimulai dari 1.

### 5. Case 2: kelola daftar buku (ArrayList)
```java
case 2 -> {
    System.out.println("\n-- Kelola Daftar Buku --");
    System.out.println("1. Tambah Buku");
    System.out.println("2. Tampilkan Buku");
    System.out.println("3. Hapus Buku");
    System.out.print("Pilih aksi: ");
    String aksiBuku = input.nextLine();

    if (aksiBuku.equals("1")) {
        System.out.print("Masukkan nama buku: ");
        String bukuBaru = input.nextLine();
        daftarBuku.add(bukuBaru);
        System.out.println("buku berhasil ditambah.");
    } else if (aksiBuku.equals("2")) {
        System.out.println("Koleksi Buku saat ini: " + daftarBuku);
    } else if (aksiBuku.equals("3")) {
        System.out.print("Masukkan nama buku yang mau dihapus: ");
        String bukuHapus = input.nextLine();
        if (daftarBuku.remove(bukuHapus)) {
            System.out.println("Buku berhasil dihapus.");
        } else {
            System.out.println("Buku tidak ketemu.");
        }
    } else {
        System.out.println("Pilihan salah!");
    }
}
```
Empat baris `println` pertama menampilkan judul dan tiga pilihan submenu, lalu `print("Pilih aksi: ")` menanyakan pilihannya. Jawabannya dibaca dengan `nextLine()` sebagai teks ke variabel `aksiBuku`, dan dibandingkan dengan `.equals()`. Untuk membandingkan isi teks kami memakai `equals`, bukan `==`, karena `==` hanya memeriksa apakah dua variabel menunjuk objek yang sama.

Percabangan `if` pertama (pilihan "1") membaca judul buku ke `bukuBaru`, lalu `daftarBuku.add(bukuBaru)` menaruhnya di ujung ArrayList. Cabang `else if` kedua (pilihan "2") mencetak `daftarBuku` langsung, dan Java otomatis menampilkannya dalam bentuk `[buku1, buku2]`. Cabang ketiga (pilihan "3") membaca judul yang mau dihapus ke `bukuHapus`, lalu memanggil `daftarBuku.remove(bukuHapus)`. Method ini mengembalikan `true` kalau judulnya ada dan berhasil dihapus, atau `false` kalau tidak ditemukan, dan nilai itu langsung dipakai oleh `if` bersarang untuk memilih pesan "Buku berhasil dihapus." atau "Buku tidak ketemu.". Bagian `else` paling akhir menangkap semua ketikan selain 1, 2, dan 3.

### 6. Case 3: simpan lokasi buku (Array 3D)
```java
case 3 -> {
    System.out.println("\n-- Lokasi Buku --");
    System.out.print("Masukkan nama buku: ");
    String namaBuku = input.nextLine();

    int salahInputLokasi = 0;
    while (salahInputLokasi < 3) {
        System.out.print("Lantai (0-2): ");
        int lantai = input.nextInt();
        System.out.print("Rak (0-4): ");
        int rak = input.nextInt();
        System.out.print("Posisi (0-9): ");
        int posisi = input.nextInt();

        // cek validasi lokasi
        if (lantai >= 0 && lantai < 3 && rak >= 0 && rak < 5 && posisi >= 0 && posisi < 10) {
            lokasiBuku[lantai][rak][posisi] = namaBuku;
            System.out.println("Buku '" + namaBuku + "' disimpen di Lantai " + lantai + ", Rak " + rak + ", Posisi " + posisi);
            break;
        } else {
            salahInputLokasi++;
            System.out.println("Lokasi nggak valid!");
            if (salahInputLokasi == 3) {
                System.out.println("Udah 3 kali salah input!, kembali ke menu utama.");
            } else {
                System.out.println("Coba lagi ya.");
            }
        }
    }
}
```
Setelah judul dan prompt ditampilkan, judul buku dibaca ke `namaBuku`. Variabel `salahInputLokasi` dibuat sebagai penghitung salah ketik yang mulai dari 0, dan `while (salahInputLokasi < 3)` hanya berjalan selama penghitung itu masih di bawah 3.

Di dalam perulangan, program meminta tiga angka dan menyimpannya ke `lantai`, `rak`, dan `posisi`. Kondisi `if` memakai operator `&&` (dan) untuk memastikan ketiganya ada dalam rentang yang sesuai ukuran array. Pemeriksaan ini penting. Tanpanya, angka seperti 5 untuk lantai akan membuat Java melempar `ArrayIndexOutOfBoundsException` dan programnya langsung berhenti. Komentar `// cek validasi lokasi` di atasnya hanya penanda untuk kami.

Kalau lokasinya valid, `lokasiBuku[lantai][rak][posisi] = namaBuku` menyimpan judul ke koordinat itu, pesan sukses ditampilkan dengan menggabungkan teks dan variabel memakai tanda `+`, dan `break` mengeluarkan program dari `while`. Kalau tidak valid, `salahInputLokasi++` menambah penghitung satu, lalu muncul pesan "Lokasi nggak valid!". `if` bersarang di dalamnya memeriksa apakah ini kesalahan ketiga. Kalau iya, pengguna diberi tahu bahwa program kembali ke menu utama, dan karena syarat `< 3` sudah tidak terpenuhi, perulangan berhenti sendiri. Kalau belum, pengguna diminta mencoba lagi.

### 7. Case 4: antrean mahasiswa (Queue)
```java
case 4 -> {
    System.out.println("\n-- Antrian Mahasiswa --");
    System.out.println("1. Tambah Mahasiswa ke Antrian");
    System.out.println("2. Panggil Mahasiswa Berikutnya");
    System.out.print("Pilih aksi: ");
    String aksiAntrean = input.nextLine();

    switch (aksiAntrean) {
        case "1" -> {
            System.out.print("Masukkan nama mahasiswa: ");
            String namaMhs = input.nextLine();
            antrianMhs.add(namaMhs);
            System.out.println(namaMhs + " masuk ke antrian.");
        }
        case "2" -> {
            if (!antrianMhs.isEmpty()) {
                System.out.println("Melayani mahasiswa: " + antrianMhs.poll());
            } else {
                System.out.println("Antrian lagi kosong.");
            }
        }
        default -> System.out.println("Pilihan salah.");
    }
}
```
Tiga baris `println` pertama menampilkan judul dan dua pilihan, lalu pilihan dibaca ke `aksiAntrean`. Setelah itu ada `switch` di dalam `switch`, untuk memilih antara menambah atau memanggil mahasiswa. Karena pilihannya dibaca sebagai teks, case-nya ditulis `"1"` dan `"2"`.

Case `"1"` membaca nama ke `namaMhs`, memasukkannya ke belakang antrean dengan `antrianMhs.add(namaMhs)`, lalu menampilkan pesan bahwa nama itu sudah masuk. Case `"2"` mengecek `!antrianMhs.isEmpty()` lebih dulu (tanda `!` berarti "tidak"), supaya program tidak mengambil dari antrean kosong. Kalau ada isinya, `antrianMhs.poll()` mengambil sekaligus menghapus nama yang paling depan, jadi mahasiswa yang datang paling awal dilayani paling awal. Kalau kosong, muncul pesan "Antrian lagi kosong.". Bagian `default` menangkap pilihan selain 1 dan 2.

### 8. Case 5: catat peminjaman dan statistik (Array 2D dan Stack)
```java
case 5 -> {
    System.out.println("\n-- Catat Peminjaman --");

    System.out.print("Masukkan nama peminjam: ");
    String namaPeminjam = input.nextLine();
    System.out.print("Masukkan judul buku yang dipinjam: ");
    String judulDipinjam = input.nextLine();

    int salahInputPinjam = 0;
    while (salahInputPinjam < 3) {
        System.out.println("\nUntuk data statistik, lengkapi info berikut:");

        System.out.println("Kategori Buku:");
        for (int i = 0; i < kategoriBuku.length; i++) {
            System.out.println(i + " : " + kategoriBuku[i]);
        }
        System.out.print("Pilih kategori (0-4): ");
        int kat = input.nextInt();

        System.out.println("\nHari Peminjaman:");
        System.out.println("0 : Senin\n1 : Selasa\n2 : Rabu\n3 : Kamis\n4 : Jumat\n5 : Sabtu\n6 : Minggu");
        System.out.print("Pilih hari (0-6): ");
        int hari = input.nextInt();
        input.nextLine();

        if (kat >= 0 && kat < 4 && hari >= 0 && hari < 7) {
            statPinjam[kat][hari]++;
            String transaksi = namaPeminjam + " meminjam '" + judulDipinjam + "' (Kategori: " + kategoriBuku[kat] + ")";
            riwayatTransaksi.push(transaksi);

            System.out.println("\nSip, transaksi berhasil dicatat!");
            System.out.println("Total peminjaman kategori " + kategoriBuku[kat] + " di hari ke-" + hari + " : " + statPinjam[kat][hari]);
            break;
        } else {
            salahInputPinjam++;
            System.out.println("\n[!] Input kategori atau hari salah!");
            if (salahInputPinjam == 3) {
                System.out.println("Udah 3 kali salah input!, kembali ke menu utama. <<");
            } else {
                System.out.println("Ayo masukin angka yang bener.");
            }
        }
    }
}
```
Judul ditampilkan, lalu nama peminjam dan judul buku dibaca sebagai teks ke `namaPeminjam` dan `judulDipinjam`. Setelah itu kami memakai pola yang sama dengan menu 3: penghitung `salahInputPinjam` dan `while` yang memberi maksimal tiga kesempatan.

Di dalam perulangan, `for` mencetak daftar kategori dari array 1D dengan format `nomor : nama`, lalu program meminta nomor kategori ke variabel `kat`. Teks petunjuknya tertulis "(0-4)", padahal kategori yang sah hanya 0 sampai 3, dan ini akan kami bahas di bagian analisis. Satu `println` yang berisi banyak `\n` mencetak tujuh nama hari sekaligus, masing-masing di baris baru. Nomor hari dibaca ke `hari`, dan `input.nextLine()` sesudahnya membuang sisa Enter.

Kalau `kat` ada di 0 sampai 3 dan `hari` di 0 sampai 6, baris `statPinjam[kat][hari]++` menambah satu pada sel statistik yang bersangkutan. Lalu teks transaksi dirangkai dari nama peminjam, judul buku, dan nama kategori (diambil dari `kategoriBuku[kat]`), dan dimasukkan ke tumpukan dengan `riwayatTransaksi.push(transaksi)`. Dua `println` berikutnya menampilkan pesan sukses dan total terbaru di sel tadi, lalu `break` mengakhiri perulangan.

Kalau input di luar rentang, `salahInputPinjam++` menambah penghitung dan muncul peringatan. `if` bersarang memeriksa apakah ini kesalahan ketiga. Kalau iya, program kembali ke menu utama. Kalau belum, pengguna diminta memasukkan angka lagi.

### 9. Case 6: batalkan transaksi (Stack)
```java
case 6 -> {
    System.out.println("\n-- Batalkan Transaksi Berdasarkan Nama --");
    if (!riwayatTransaksi.isEmpty()) {
        System.out.print("Masukkan nama peminjam yang ingin dibatalkan: ");
        String namaBatal = input.nextLine();

        Stack<String> tempStack = new Stack<>();
        boolean ketemu = false;

        while (!riwayatTransaksi.isEmpty()) {
            String transaksi = riwayatTransaksi.pop();

            if (transaksi.toLowerCase().startsWith(namaBatal.toLowerCase() + " ") && !ketemu) {
                System.out.println("Berhasil membatalkan transaksi: " + transaksi);
                ketemu = true;

            } else {
                tempStack.push(transaksi);
            }
        }

        while (!tempStack.isEmpty()) {
            riwayatTransaksi.push(tempStack.pop());
        }

        if (!ketemu) {
            System.out.println("Transaksi atas nama '" + namaBatal + "' nggak ketemu tuh.");
        }

    } else {
        System.out.println("Nggak ada riwayat transaksi nih.");
    }
    break;
}
```
Bagian ini yang paling rumit di program kami. Judul menunya memang "berdasarkan nama", artinya pengguna menyebut nama peminjam yang transaksinya mau dibatalkan. Program mengecek `!riwayatTransaksi.isEmpty()` lebih dulu. Kalau riwayat kosong, tidak ada yang bisa dibatalkan, dan pesan di bagian `else` paling bawah yang tampil.

Kalau ada isinya, nama yang dicari dibaca ke `namaBatal`. Kendalanya, Stack hanya bisa diambil dari atas, sedangkan transaksi yang dicari bisa saja ada di tengah tumpukan. Karena itu kami menyiapkan `tempStack` sebagai tumpukan bantu dan `ketemu` sebagai penanda yang awalnya `false`.

Perulangan `while` pertama berjalan selama riwayat belum habis. Di tiap putaran, `riwayatTransaksi.pop()` mengambil transaksi dari puncak (yang terbaru dulu) dan menyimpannya di `transaksi`. Kondisi `if` memeriksa apakah transaksi itu diawali nama yang dicari. Kedua teks diubah ke huruf kecil dengan `toLowerCase()` supaya besar kecil huruf tidak jadi masalah. Spasi ditambahkan setelah nama di `startsWith`, supaya nama "Ani" tidak ikut cocok dengan transaksi atas nama "Anita". Syarat `!ketemu` memastikan hanya satu transaksi yang dibatalkan, yaitu yang paling baru atas nama itu. Kalau cocok, pesan berhasil ditampilkan dan `ketemu` jadi `true`. Kalau tidak cocok, transaksi dipindahkan ke `tempStack`.

Perulangan kedua memindahkan isi `tempStack` kembali ke `riwayatTransaksi`. Karena tumpukan dibalik dua kali, urutan transaksi yang tersisa kembali seperti semula. Terakhir, kalau `ketemu` masih `false`, pengguna diberi tahu bahwa nama itu tidak ada di riwayat. Pernyataan `break` di ujung blok sebenarnya tidak wajib untuk bentuk `->`, tetapi tidak menimbulkan masalah.

### 10. Case 7, default, dan penutup
```java
case 7 -> {
    System.out.println("\nTerima kasih udah pakai sistem ini!");
    lanjut = false;
    break;
}

default -> {
    System.out.println("\nPilihan menu nggak ada. Coba angka yang bener.");
    break;
}
```
Menu 7 menampilkan pesan penutup dan mengubah `lanjut` menjadi `false`. Setelah `switch` selesai, `while (lanjut)` mengecek ulang kondisinya, mendapati `false`, dan berhenti. Blok `try` pun berakhir, `Scanner` tertutup otomatis, dan program selesai.

Bagian `default` menangkap semua angka selain 1 sampai 7. Pesan kesalahan ditampilkan, lalu menu muncul lagi di putaran berikutnya.

Kurung kurawal penutup di akhir file menutup, berturut-turut dari yang paling dalam, `switch`, `while`, blok `try`, method `main`, dan kelas `SistemPerpustakaan`.

## Screenshot Hasil Eksekusi

### Menu 1: tampilkan kategori buku
![Menampilkan kategori buku](assets/01-kategori-buku.jpeg)

Empat kategori dari array 1D tampil berurutan, nomornya dimulai dari 1.

### Menu 2: kelola daftar buku
Pertama kami menambah buku "belajar java".

![Tambah buku](assets/02-tambah-buku.jpeg)

Lalu kami menampilkan isi koleksi, dan yang muncul `[belajar java]`.

![Tampilkan buku](assets/03-tampilkan-buku.jpeg)

Terakhir kami mencoba menghapus buku "buku java".

![Hapus buku](assets/04-hapus-buku.jpeg)

### Menu 3: lokasi buku
Buku "buku PBO" berhasil disimpan di Lantai 1, Rak 2, Posisi 5.

![Lokasi valid](assets/05-lokasi-valid.jpeg)

Untuk buku "ddp", kami sengaja mengetik lokasi yang salah tiga kali berturut-turut (lantai 5, lantai 3, lalu lantai 66). Ketiganya ditolak dan program kembali ke menu utama.

![Lokasi tidak valid](assets/06-lokasi-invalid.jpeg)

### Menu 4: antrean mahasiswa
Mahasiswa bernama "carla" kami masukkan ke antrean.

![Tambah antrean](assets/07-antrean-tambah.jpeg)

Lalu "carla" dipanggil karena dia yang paling depan.

![Panggil antrean](assets/08-antrean-panggil.jpeg)

### Menu 5: catat peminjaman dan statistik
"carla" meminjam "jaringan komputer" (kategori 2) pada hari ke-2, dan total peminjaman di sel itu menjadi 1.

![Peminjaman valid](assets/09-peminjaman-valid.jpeg)

Di percobaan lain, kategori 4 ditolak karena kategori yang ada hanya 0 sampai 3.

![Kategori salah](assets/10-peminjaman-kategori-salah.jpeg)

Kalau salah sampai tiga kali (hari 34 dan hari 8 juga ditolak), program kembali ke menu utama.

![Salah tiga kali](assets/11-peminjaman-salah-3x.jpeg)

### Menu 6: batalkan transaksi
Transaksi "carla meminjam 'jaringan komputer'" berhasil dibatalkan dengan mengetik nama peminjamnya.

![Undo transaksi](assets/12-undo-transaksi.jpeg)

### Menu 7: keluar
Program menampilkan pesan penutup lalu berhenti. Tulisan `BUILD SUCCESS` di bawahnya berasal dari NetBeans.

![Keluar program](assets/13-keluar-program.jpeg)

## Hasil Pengujian dan Analisis

### Pengujian
| No | Menu | Skenario | Input | Hasil yang diharapkan | Status |
|----|------|----------|-------|-----------------------|--------|
| 1 | 1 | Lihat kategori | `1` | Empat kategori tampil | Berhasil |
| 2 | 2 | Tambah buku | `2`, `1`, `belajar java` | Judul masuk ke ArrayList | Berhasil |
| 3 | 2 | Tampilkan buku | `2`, `2` | Muncul `[belajar java]` | Berhasil |
| 4 | 2 | Hapus buku | `2`, `3`, `buku java` | Buku dihapus, muncul pesan sukses | Berhasil |
| 5 | 3 | Lokasi valid | `3`, `buku PBO`, `1`, `2`, `5` | Tersimpan di `[1][2][5]` | Berhasil |
| 6 | 3 | Lokasi salah 3 kali | `3`, `ddp`, lalu `5 1 3`, `3 1 4`, `66 5 4` | Tiga pesan tidak valid, kembali ke menu | Berhasil |
| 7 | 4 | Tambah antrean | `4`, `1`, `carla` | Nama masuk antrean | Berhasil |
| 8 | 4 | Panggil antrean | `4`, `2` | Nama paling depan dilayani | Berhasil |
| 9 | 5 | Peminjaman valid | `5`, `carla`, `jaringan komputer`, kategori `2`, hari `2` | Statistik naik, transaksi masuk Stack | Berhasil |
| 10 | 5 | Kategori di luar rentang | `5`, `carla`, kategori `4`, hari `3` | Muncul peringatan input salah | Berhasil |
| 11 | 5 | Salah 3 kali | kategori dan hari di luar rentang | Kembali ke menu setelah 3 kali | Berhasil |
| 12 | 6 | Batalkan transaksi | `6`, `carla` | Transaksi carla keluar dari Stack | Berhasil |
| 13 | 7 | Keluar | `7` | Pesan penutup, program berhenti | Berhasil |

### Analisis
Dari pengujian, semua fitur utama berjalan seperti yang kami rancang, dan tiap struktur data punya operasi yang hasilnya terlihat langsung di layar. Validasi rentang di menu 3 dan menu 5 bekerja, dan batas tiga kali percobaan membuat pengguna tidak terjebak di perulangan input.

Ada juga beberapa kekurangan yang kami temukan, dan lebih baik kami tulis apa adanya:

1. Program memakai `nextInt()`, jadi kalau pengguna mengetik huruf di tempat yang meminta angka, program berhenti dengan `InputMismatchException`. Validasi kami baru memeriksa rentang angka, belum jenis ketikannya.
2. Petunjuk di menu 5 tertulis "Pilih kategori (0-4)", padahal kategori yang sah hanya 0 sampai 3. Ini cuma salah tulis di teks, validasinya sendiri sudah benar (lihat pengujian nomor 10).
3. Pembatalan transaksi hanya menghapus catatan di Stack. Angka di `statPinjam` tidak ikut dikurangi, jadi statistik masih menghitung transaksi yang sudah dibatalkan.
4. Semua data hilang begitu program ditutup, karena belum ada penyimpanan ke file atau database.
5. Menu 3 tidak memeriksa apakah judul buku sudah ada di `daftarBuku`, dan sel lokasi yang sudah terisi akan tertimpa tanpa peringatan.
6. Riwayat transaksi belum punya menu untuk dilihat. Isi Stack hanya muncul sebagai satu pesan saat sebuah transaksi dibatalkan, sehingga petugas tidak bisa melihat transaksi terakhir tanpa membatalkannya.

Kalau dikembangkan lagi, hal-hal di atas yang akan kami perbaiki lebih dulu, misalnya dengan `try-catch` untuk input dan pengurangan statistik saat undo.

## Kesimpulan
Program ini berhasil menyatukan enam struktur data dalam satu aplikasi yang jalan utuh. Beberapa strukturnya saling terhubung, misalnya array 1D dipakai di menu kategori sekaligus menu peminjaman, dan hasil peminjaman masuk ke array 2D dan Stack di saat yang sama.

Dari pengerjaan ini kami melihat sendiri kenapa tiap struktur punya tempatnya. Array 1D, 2D, dan 3D pas untuk data yang ukurannya sudah pasti seperti kategori, statistik mingguan, dan denah rak. ArrayList lebih enak untuk koleksi buku yang jumlahnya naik turun. Queue menjaga antrean tetap adil karena yang datang duluan dilayani duluan, sedangkan Stack membuat transaksi terbaru gampang dijangkau saat perlu dibatalkan.

Kami juga belajar bahwa input pengguna sering tidak sesuai bayangan kami. Karena itu validasi bukan tambahan, melainkan bagian yang harus dipikirkan dari awal.
