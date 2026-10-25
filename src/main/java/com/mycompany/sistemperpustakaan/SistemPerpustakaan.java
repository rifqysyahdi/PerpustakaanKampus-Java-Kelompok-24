package com.mycompany.sistemperpustakaan;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

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

                    case 1 -> {
                        System.out.println("\n-- Kategori Buku --");
                        for (int i = 0; i < kategoriBuku.length; i++) {
                            System.out.println((i + 1) + ". " + kategoriBuku[i]);
                        }
                    }

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

                    case 7 -> {
                        System.out.println("\nTerima kasih udah pakai sistem ini!");
                        lanjut = false;
                        break;
                    }

                    default -> {
                        System.out.println("\nPilihan menu nggak ada. Coba angka yang bener.");
                        break;
                    }
                }
            }
        }
    }
}