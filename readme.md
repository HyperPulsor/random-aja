# Tutorial APAP
## Authors

* **Rakan Fasya Athhar Rayyan** - *2106750950* - *B*

## Tutorial 3
### What I have learned today
1. Penggunaan Annotation
2. 

### Pertanyaan
1. **Jelaskan apa itu ORM pada spring serta apa fungsi dan kegunaanya?**
   - ORM atau Object Relational Mapping merupakan *design pattern* yang digunakan
   untuk mengakses database relasional lewat pendekatan *object-oriented*. Pada
   Spring, JPA (Java Persistence API) merupakan implementasi dari ORM dimana menjembatani antara
   domain model *object-oriented* dan sistem database relasional. Fungsi dari ORM
   adalah untuk mengakses database relasional lewat suatu objek data tanpa harus
   menggunakan SQL.
2. **Jelaskan secara singkat apa itu dan kegunaan dari tag-tag dibawah ini.**
(`@Entity`, `@Table`, `@Column`)
3. **Pada relasi buku ke penulis, terdapat tag**
`@JoinTable(name = "penulis_buku", joinColumns = @JoinColumn(name = "id"),
inverseJoinColumns = @JoinColumn(name = "id_penulis"))`
**Jelaskan maksud dari tag @JoinTable tersebut beserta parameternya (name, joinColumns, inverseJoinColumns) dan implementasinya pada database.**
4. **Bagaimana cara kerja dari dependensi java mapper, yaitu mapstruct?**
5. **Apa keuntungan dari implementasi soft delete?**

### What I did not understand
- [x] Cara kerja @Autowired dibalik layar
- [x] Alasan DTO digunakan sebagai media transfer data

## Tutorial 2
### What I have learned today
1. Penggunaan DTO dalam pembuatan objek baru untuk passing data
2. Interkoneksi cara kerja MVC
3. Peran service dalam MVC
4. Cara melakukan pathing pada website lewat Controller
5. Passing data dari model ke view

### Pertanyaan
1. **Apa itu DTO? Jelaskan kegunaannya pada proyek ini?**
   - DTO atau Data Transfer Object merupakan objek yang digunakan untuk mengantarkan data antar layer pada aplikasi. Pada proyek ini, DTO digunakan untuk
   mentransfer data dari `form-create-buku.html` kepada `BukuController.java`. Pada saat terjadi request `GET` maka Controller akan membuat DTO baru yang dapat digunakan
   untuk input form baru dan `bukuDTO` dimasukkan dalam model sebagai objek DTO. Kemudian, setelah form disubmit atua terjadi request `POST`, maka Controller akan mengambil data field input dari form
   dan menggunakan data tersebut untuk membuat objek DTO baru pada file Controller sehingga objek tersebut dapat digunakan oleh service dan model.
2. **Apa itu UUID? Mengapa UUID digunakan?**
   - UUID atau Universal Unique Identifier merupakan angka yang memiliki panjang **128-bit** dengan banyak **32 karakter** dalam hexadesimal sebagai penanda unik suatu
   objek. Dalam tutorial ini, UUID digunakan untuk memetakan ID unik ke setiap pembuatan objek buku baru
   sehingga tidak ada buku dengan ID yang sama dan tidak mungkin terambil dua buku saat diambil menggunakan metode `getBukuById` berdasarkan satu ID yang sama. 
3. **Pada service, mengapa perlu ada pemisahan antara interface dan implementasinya?**
   - Pemisahan antar interface dan implementasinya bertujuan untuk mengatasi masalah skalabilitas program.
   Apabila ingin melakukan *scale up* dari program, maka pengembang hanya perlu mengubah bagian implementasi
   dari interface. Selain itu, setiap class bisa memiliki implementasi interface yang berbeda sehingga tidak harus membuat
   metode lagi yang berbeda. Lalu, apabila terdapat masalah, pengembang juga hanya perlu merubah implementasi dari interfacenya. 

4. **Menurut kamu anotasi `@Autowired` pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja `@Autowired` tersebut dalam konteks service dan controller yang telah kamu buat.**
   - `@Autowired` merupakan implementasi dari konsep *dependency injection*. Dalam tutorial ini, `@Autowired`digunakan pada objek `BukuService` dimana berarti terjadi *dependency injection* antara Controller dan Service.
   Dari injeksi tersebut, Spring secara otomatis menginjeksi objek Service kepada Contoller yang dimana dibutuhkan oleh Controller. Selain itu, `@Autowired` memungkinkan Controller untuk langsung memanggil method dari service yang sudah diimplementasi
   dari `BukuServiceImpl.java`. Cara kerja dari `@Autowired` adalah dimana ditaruh instance dari bean `BukuService` pada bean lain yakni Controller (proses ini merupakan dependency injection). Kemudian, dengan anotasi `@Autowired` kita dapat langsung menggunakan
   method yang sudah diimplementasikan yaitu dari `BukuServiceImpl`. Hal ini dikarenakan instance tersebut sudah otomatis diinjeksi pada bean Controller. Proses otomatis tersebut dilakukan oleh anotasi `@Autowired`.
5. Apa perbedaan `@GetMapping` dan `@PostMapping`?
   - `@GetMapping` melakukan *handling* terhadap metode request dengan tipe `GET`. Metode dengan anotasi ini merupakan implementasi metode yang diterapkan pada page saat menerima request bertipe `GET`.
   - `@PostMapping` melakukan *handling* terhadap metode request dengan tipe `POST`. Metode dengan anotasi ini
   merupakan implementasi metode yang diterapkan pada page saat menerima request bertipe `POST`.
6. **Jelaskan proses yang terjadi di controller, model, dan service pada proses create buku, mulai dari fungsi formAddBuku hingga pengguna menerima halaman success-create-buku.**
   - Pada saat proses create buku terjadi, metode `formAddBuku()` akan dilakukan karena tipe metode request awal berupa `GET` pada saat page untuk form dibuka, lalu objek DTO baru yang akan dibuat untuk isian form baru. Objek tersebut akan disimpan pada model
   dengan variabel `bukuDTO` dan return ke html untuk form penambahan buku.Kemudian, form pada html dengan `th:object="${bukuDTO}"` akan membentuk semua hubungan antara
   form dengan objek `bukuDTO` yang tersimpan pada model. Hal ini menyebabkan form diisi dengan properti value dari objek `bukuDTO` dan saat form disubmit maka data input akan disesuaikan kembali dengan properti value dari objek tersebut pada model. Saat form disubmit, maka akan mengirimkan request dengan tipe `POST` yang dimana akan menjalankan fungsi `addBuku()`. Pada metode ini, diterima
   parameter `bukuDTO` dengan anotasi `@ModelAttribute` supaya objek yang digunakan adalah objek tersimpan pada model. Dari parameter tersebut, akan dibuat objek buku baru dari data parameter `bukuDTO` dan UUID. Setelah objek buku tersebut dibuat,
   maka akan memanggil metode `createBuku()` dari service yang sudah diimplementasi (tidak dari interface service karena `@Autowired`) dan menyimpan objek buku tersebut pada `listBuku`. Setelah itu, `ID` dan `Judul` dari objek buku tersebut disimpan pada model. Terakhir, akan direturn
   ke halaman html `success-create-buku.html` yang menunjukkan bahwa buku baru sudah berhasil ditambahkan.
7. **Jelaskan mengenai th:object!**
   - `th:object` merupakan atribut HTML yang digunakan pada Thymeleaf untuk menspesifikan suatu objek (bisa dari model)
   ke dalam suatu form. Form dengan hubungan ini bisa menggunakan properti value dari objek sehingga input form bisa sesuai dengan model. Kemudian, saat form disubmit akan disesuaikan kembali
   input form sudah sesuai dengan properti value objek pada model. Akan tetapi, input form tidak akan disimpan ke dalam model saat form disubmit.
8. **Jelaskan mengenai th:field!** 
   - `th:field` merupakan atrirbut HTML yang digunakan pada Thymeleaf untuk menspesifikan suatu input field kepada suatu properti value objek di model. Contohnya, yaitu
   `th:field="*{judul}` dimana akan mengambil properti field `judul` dari objek `bukuDTO` pada model dan akan digunakan untuk input field pada form. Hal ini supaya input field mengetahui properti mana dari objek model terkait yang diwakilinya.

### What I did not understand
- [ ] Cara kerja @Autowired dibalik layar
- [ ] Alasan DTO digunakan sebagai media transfer data

### Referensi
- https://www.geeksforgeeks.org/videos/data-transfer-object-dto-in-spring-mvc-with-example/
- https://www.baeldung.com/spring-autowire

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
- [x] Pengembangan website menggunakan Java, Spring Boot, dan Spring Framework
- [ ] Fungsionalitas Maven untuk proyek skala besar
- [x] Library & dependencies yang dibutuhkan untuk proyek skala besar
- [x] Fungsionalitas lebih dari Issue Tracker

### Referensi
https://docs.gitlab.com/ee/user/project/issues/
https://javarevisited.blogspot.com/2017/10/differences-between-requestparam-and-pathvariable-annotations-spring-mvc.html#:~:text=1)
https://www.simplilearn.com/tutorials/devops-tutorial/version-control
https://www.simplilearn.com/tutorials/maven-tutorial/what-is-maven



