# Tutorial APAP
## Authors

* **Rakan Fasya Athhar Rayyan** - *2106750950* - *B*

## Tutorial 4
### What I have learned today
1. Fungsi pagination dan Datatables
2. Implementasi Dynamic Form
3. Environment Database
4. Error handling

### Pertanyaan
1. **Pada file html project bacabaca, terdapat baris kode berikut. `<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://thymeleaf.org">`
Apa itu xmlns? Jawab dengan singkat dan padat.**
    - Xmlns merupakan singkatan dari XML Namespace. Pada dasarnya, semua elemen atau atribut pada XML
   terletak ke suatu ruang nama atau *namespace*. Namespace digunakan supaya ketika terdapat dua XML pada satu dokumen,
   tidak terjadi konflik apabila terdapat nama atribut yang sama.

2. **Jelaskan perbedaan th:include dan th:replace! Jawab dengan singkat dan padat.**
    - Pada saat menggunakan `th:include`, fragments akan ditaruh dalam tag yang menggunakannya. Sedangkan, saat
   menggunakan `th:replace`, fragments akan menggantikan tag yang menggunakannya.

3. **Kapan sebaiknya kita menggunakan static files dibandingkan dengan file eksternal menggunakan link?  Jawab dengan singkat dan padat.**
    - Static files digunakan apabila kita ingin memiliki kendali penuh terhadap konten tersebut dan file
   tidak berganti-ganti. Sedangkan, dengan file eksternal link dapat diganti-diganti isi kontennya dan tidak perlu
   mengubah link. Penggunaan file eksternal link juga dapat menghemat penyimpanan.

4. **Jelaskan caramu menyelesaikan latihan no 2.**
    - Menambahkan method `addRowPenulisBukuUpdate` dan `deleteRowPenulisBukuUpdate` untuk handle penambahan dan pengurangan 
    list Penulis baru. Untuk buku dengan tidak ada Penulis, membuat listPenulis baru. Sedangkan, buku dengan Penulis menambahkan
   Penulis kosong pada List yang akan dimasukkan.
    - Mengubah file html `form-update-buku` dengan iterasi listPenulis dan listPenulisExisting sehingga
   mendapatkan idPenulis dan namaPenulis. Selain itu, menambahkan tombol untuk Add Row dengan nama yang sesuai
   pada parameter method `deleteRowPenulisBukuUpdate` sehingga menjadi parameter untuk row yang akan dihapus.

5. **Jelaskan apa itu pagination! Jawab dengan singkat dan padat.**
    - Pagination adalah pembagian konten menjadi beberapa halaman terpisah sehingga
   lebih mudah untuk di navigasi. Selain itu, pada pagination dapat dilakukan sort secara
   ascending atau descending.

6. **Sebutkan salah satu skenario yang mengharuskan adanya perbedaan dev dan prod dan jelaskan alasannya!**
    - Skenarionya adalah pada saat kita mengelola database dengan data yang besar. Apabila, tidak terdapat perbedaan
   environment, maka database untuk production akan digabung. Hal ini berpotensi bahaya karena apabila pengembang melakukan
   implementasi dan pengubahan database, akan terganti atau terhapus data-data asli yang digunakan pada website. Dengan adanya
   perbedaan dev dan prod, akan menghindarkan dari pergantian atau penghapusan data karena dataabse asli website berbeda
   dengan database implementasi pengembangan.

7. **Lampirkan screenshot kalau kamu sudah berhasil membuat user untuk environment production serta bukti bahwa kamu sudah berhasil mengakses database production dengan user tersebut!**
   ![Screenshot_671](https://github.com/HyperPulsor/Lab1Repo/assets/101686378/f960307d-ccd1-4240-b095-e8e8194ff600)
   ![Screenshot_673](https://github.com/HyperPulsor/Lab1Repo/assets/101686378/d8c6ce6c-2f9d-44f1-9035-89b22e8e6609)

### What I did not understand
- [X] None

### Referensi
https://stackoverflow.com/questions/1181888/what-does-xmlns-in-xml-mean
https://stackoverflow.com/questions/37103958/difference-between-thymeleaf-include-and-replace

## Tutorial 3
### What I have learned today
1. Penggunaan Annotation dalam Spring Boot
2. Konversi antar dua objek dengan MapStruct
3. Penggunaan Derived Query Method untuk pencarian data
4. Penggunaan JPA validation constraint untuk validasi input

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
   - `@Entity` merupakan anotasi yang memetakan suatu class Java
   ke dalam suatu tabel pada database relasional. Fungsinya adalah
   supaya JPA mengenali bahwa class Java merepresentasikan suatu
   tabel pada database relasional.
   - `@Table` meruapakan anotasi yang memetakan suatu tabel pada relational database
   ke dalam *object-oriented* berupa class Java. Fungsinya sama dengan `@Entity`, hanya saja
   `@Table` bersifat *relation-oriented* sebagai representasi tabel pada database relational (SQL)
   dan`@Entity` bersifat *object-oriented* sebagai representasi tabel pada class Java atau *logical-side*.
   Selain itu, kita dapat memberikan detail tabel, seperti nama tabel dan nama skema.
   - `@Column` merupakan anotasi yang merepresentasikan detail kolom pada tabel. Fungsi
   nya adalah untuk memetakan *field* atau atribut dari class Java ke dalam database relasional.
3. **Pada relasi buku ke penulis, terdapat tag**
`@JoinTable(name = "penulis_buku", joinColumns = @JoinColumn(name = "id"),
inverseJoinColumns = @JoinColumn(name = "id_penulis"))`
   **Jelaskan maksud dari tag @JoinTable tersebut beserta parameternya (name, joinColumns, inverseJoinColumns) dan implementasinya pada database.**
   - `@JoinTable` merupakan tag yang berfungsi untuk menggabungkan dua entitas relasi untuk mengatur
   hubungan **Many-To-Many** dalam suatu tabel gabungan.
     - Parameter `name` berfungsi untuk mendefinisikan nama *output* tabel akhir dari hasil penggabungan
     dua entitas.
     - Parameter `joinColumns` berfungsi untuk mendefinisikan kolom *foreign key* (**id**) dari entitas pemilik (**Buku**) ke
     tabel gabungan sehingga dapat digabungkan. Kolom ini merepresentasikan hubungan antara entitias pemilik dengan tabel gabungan.
     - Parameter `inverseJoinColumns` berfungsi untuk mendefinisikan kolom *foreign key* (**id_penulis**) dari entitas lain (**Penulis**) ke tabel gabungan
     sehingga dapat digabungkan. Kolom ini merepresentasikan hubungan antara entitas lain dengan tabel gabungan.
   - Implementasinya dalam database adalah untuk hubungan **Many-To-Many** antara **Buku** dan **Penulis** akan disimpan
   pada tabel gabungan dengan nama **penulis_buku**. Menggunakan `@JoinTable`, database akan mengambil informasi untuk membuat
   tabel gabungan tersebut dari parameter-parameter pada `@JoinTable`, yaitu nama tabel gabungan, *foreign key* yang menghubungkan
   entitas **Buku** dengan tabel **penulis_buku**, dan *foreign key* yang menghubungkan entitas **Penulis** dan **penulis_buku**.
   Kemudian, database akan membuat tabel **penulis_buku** yang menyimpan informasi kolom-kolom gabungan antara tabel **Buku** dan **Penulis**
   beserta *foreign key* yang terhubung ke masing-masing entitas.
4. **Bagaimana cara kerja dari dependensi java mapper, yaitu mapstruct?**
   - Map Struct merupakan sebuah mapper yang berfungsi untuk secara otomatis mengonversi antar objek Java.
   Biasanya dilakukan untuk konversi dari suatu objek DTO menjadi sebuah entitas. Cara kerja dari MapStruct adalah
   dengan pertama menggunakan anotasi `@Mapper` pada suatu interface. Kemudian, definisikan metode pemetaan antar dua tipe objek
   yang berbeda. Setelah metode didefinisikan, maka MapStruct akan secara otomatis mengimplementasi kode pemetaan antar dua tipe objek,
   tetapi perlu digenerate oleh Gradle. Setelah digenerate, kita dapat langsung menggunakannya dengan membuat instance dari interface mapper
   dan menggunakan metode yang telah digenerate.
   
5. **Apa keuntungan dari implementasi soft delete?**
   - Dengan mengimplementasi *soft delete*, terdapat beberapa keuntungan seperti :
     - **Restorasi data**, adanya *soft delete* memungkinkan kita untuk melakukan restorasi data karena data pada
     database tidak benar-benar "hilang" atau terhapus secara permanen.
     - **Log Data**, adanya *soft delete* memungkinkan kita untuk melakukan *tracking* atau audit terhadap data-data bahkan setelah
     "dihapus". Data-data ini dapat digunakan untuk kebutuhan analitis atau untuk permasalahan legal.
     - **Penghapusan secara dua tahap**, adanya *soft delete* mencegah kecelakaan dalam ketidaksengajaan penghapusan
     data. Dengan implementasi ini, penghapusan data benar-benar harus berdasarkan keputusan bulat milik penghapus.
     Pendekatan ini juga memungkinkan izin dari banyak user untuk benar-benar menghapus data secara permanen dari database.
     - 
### What I did not understand
- [ ] Perbedaan @BeforeMapping dan @AfterMapping

### Referensi
- https://www.baeldung.com/jpa-entities
- https://forum.hibernate.org/viewtopic.php?p=2370232#:~:text=Entity%20is%20object%2Doriented%20and,name%20in%20the%20native%20SQL.
- https://www.baeldung.com/jpa-many-to-many
- https://www.javaguides.net/2023/07/jpa-jointable-annotation.html
- https://refactorizando.com/en/guide-to-mapstruct-with-spring-boot/
- https://stackabuse.com/guide-to-mapstruct-in-java-advanced-mapping-library/
- https://www.honeybadger.io/blog/a-guide-to-soft-deletes-in-laravel/#:~:text=Data%20recovery%20and%20restoration,advantage%20of%20using%20soft%20deletes.
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



