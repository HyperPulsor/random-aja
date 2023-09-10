# Tutorial APAP
## Authors

* **Rakan Fasya Athhar Rayyan** - *2106750950* - *B*

## Tutorial 1
### What I have learned today
1. Dasar penggunaan GitLab (Membuat branch baru dan *merge request* ke branch main)
2. Cara membuat dan memantau *issue*
3. Cara menginstall dan menjalankan Spring Boot pada proyek
4. Struktur file pada Spring Boot
5. Pengenalan konsep MVC (Model-View-Controller) pada Spring Boot
6. Passing data lewat `@PathVariable` dan `@RequestParam`
### GitLab
1. **Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?**
- Issue merupakan fitur kolaborasi untuk merencanakan & mengatur pekerjaan, diskusi implementasi ide, melacak pekerjaan serta statusnya, elaborasi implementasi kode, dan masih banyak lagi masalah yang terkait pekerjaan kolaborasi.**
2. **Apa perbedaan dari git merge dan git merge --squash?**
- git merge akan melakukan merge dari suatu branch ke branch lain dengan menciptakan commit merge baru. Sedangkan, git merge --squash tidak akan membuat merge commit baru dengan banyak parent commit.
3. **Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan suatu aplikasi?**
- Memberikan riwayat dan detail perubahan dari pengembangan aplikasi dan siapa saja yang melakukan perubahan tersebut
- Memungkinkan pengembang untuk berkolaborasi dengan pengerjaan proyek secara bersamaan dan real-time
- Mengatur dan menjaga kode asli atau *source code* aplikasi
- Melakukan komparasi dengan versi lebih awal dari code dan menemukan konflik pada kode
### Spring
4. **Apa itu library & dependency?**
- Library merupakan kumpulan kode yang sudah dikembangkan dan siap dipakai oleh suatu aplikasi atau proyek. Saat suatu proyek menggunakan library tersebut, maka library itu disebut sebagai dependency karena proyek tersebut memiliki ketergantungan terhadap library yang digunakannya.
5. **Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?**
- Maven adalah alat open source yang digunakan untuk  membuat, mempublikasi, dan deploy beberapa proyek sekaligus untuk manajemen proyek yang lebih baik. Dengan Maven, pengembang dapat membuat dan mendokumentasi lifecycle dari framework yang digunakan pada proyek. Maven mempermudah pengembang karena berfokus pada simplifikasi, standarisasi, dan automasi dari proses building proyek.
- Maven digunakan supaya pengembang bisa fokus pada implementasi business logic pada proyek tanpa harus mengkhawatirkan dari segi teknis terkait pengelolaan dependensi, konfigurasi build, dan lain-lain. Penggunaan Maven juga memastikan bahwa proyek konsisten dalam pengembangannya dan mengurangi kompleksitas dari proyek.
- Iya, terdapat alternatif yang dapat digunakan selain Maven, seperti Gradle. Akan tetapi, pemilihan tools-tools lain sepenuhnya merupakan pilihan preferensi pengembang karena setiap tools memiliki keunggulan dan kekurangannya masing-masing.
6. Jelaskan bagaimana alur ketika kita menjalankan http://localhost:8080/celsius-converter/90 sampai dengan muncul keluarannya di browser!
- Alur browser sama seperti model `MVC (Model-View-Controller)`. Dimana `Controller` berperan sebagai interface penyambung `View` dan `Model`, `View` berperan untuk tampilan yang akan dilihat pengguna, dan `Model` berperan untuk mengakses dan modifikasi data yang ada di daatabase.
  1. Request akses ke localhost berupa HTTP GET akan dikirimkan ke port 8080
  2. Setelah sampai, request akan diakses oleh `Controller(CelsiusConverterController.java)` dengan mengakses fungsi `@GetMapping(value = "/celsius-converter/{celsius}")` dari url yang didapatkan
  3. Fungsi tersebut akan meneruskan ke `Model(CelsiusConverter.java)` dimana value parameter yang telah diambil akan dilakukan kalkulasi menjadi skala suhu lain menggunakan fungsi yang ada pada `Model`
  4. Setelah kalkulasi dilakukan, maka hasil kalkulasi dari `Model` akan dikirimkan ke `View (celsiusConverterPage.html)` lewat `Controller` supaya angka tersebut bisa digunakan untuk menampilkan hasil pada html.
7. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring framework?
- Spring framework dapat digunakan juga untuk pengembangan aplikasi konsol, aplikasi mobile, aplikasi Service-Oriented Architecture (SOA), aplikasi keamanan, aplikasi dengan big data, aplikasi desktop, dan masih banyak lagi. Oleh karena itu, Spring framework tidak terbatas hanya untuk pengembangan website.
8. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya menggunakan @RequestParam atau @PathVariable?
- `@RequestParam` digunakan untuk mengambil data langsung dari kueri parameter yang dipassing. Sedangkan, `@PathVariable` digunakan untuk mengambil data langsung dari path URL
- `@RequestParam` lebih cocok digunakan untuk pengembangan web yang statis dimana mayoritas data di passing lewat kueri parameter. Sedangkan, `@PathVariable` lebih cocok untuk pengembangan website dinamis dimana setiap URL memiliki value yang dapat dilakukan passing seperti pada website yang menggunakan RESTful.

### What I did not understand
- [ ] Pengembangan website menggunakan Java, Spring Boot, dan Spring Framework
- [ ] Fungsionalitas Maven untuk proyek skala besar
- [ ] Library & dependencies yang dibutuhkan untuk proyek skala besar
- [ ] Fungsionalitas lebih dari Issue Tracker

### Referensi
https://docs.gitlab.com/ee/user/project/issues/
https://javarevisited.blogspot.com/2017/10/differences-between-requestparam-and-pathvariable-annotations-spring-mvc.html#:~:text=1)
https://www.simplilearn.com/tutorials/devops-tutorial/version-control
https://www.simplilearn.com/tutorials/maven-tutorial/what-is-maven



