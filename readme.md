# Tutorial APAP
## Authors

* **Rakan Fasya Athhar Rayyan** - *2106750950* - *B*

## Tutorial 6
### What I have learned today
1. Git Revert, Rebase, Merge, Stash, Reset
2. Commit Tree, Git Flow, dan Git Branching
3. Load Testing dengan JMeter
4. Resolve Conflict pada Git
5. Monitoring Performance dengan JConsole

### Pertanyaan
1. **Apa yang menjadi penyebab dari CONFLICT tersebut?**
   - Penyebab dari konflik tersebut adalah perbedaan perubahan yang bertentangan atau *contradictory changes*.
   Perbedaan tersebut terletak pada baris ke-6 bagian body dimana pada branch `feat/tutorial-6-advancedgit-1` berbeda
   dengan branch `tut6-for-merge`. Isi teks antara dua branch tersebut berbeda sehingga mengakibatkan
   terjadinya konflik pada saat dilakukan merge dari branch `tut6-for-merge` ke branch `feat/tutorial-6-advancedgit-1`.
   Konflik terjadi karena Git bingung apakah kedua teks tersebut yang berbeda ingin disimpan dua-duanya, dihapus salah 
   satu, atau dilakukan tindakan solve lainnya.

2. **Jelaskan perbedaan dari "rebase –continue", "rebase –skip", dan "rebase –abort"!**
   - Command `rebase -continue` merupakan perintah yang digunakan ketika konflik sudah selesai disolve dan akan melanjutkan
   proses rebase. Apabila terjadi konflik, maka Git akan menghentikan proses rebase dan memberikan kesempatan user untuk
   mengsolve konflik tersebut. Setelah disolve, maka proses rebase dilanjutkan dengan perintah `rebase -continue`.
   - Command `rebase –skip` merupakan perintah yang digunakan apabila kita ingin mengskip suatu commit yang konflik 
   atau tidak ingin diapply sehingga tidak diikutsertakan pada perintah rebase.
   - Command `rebase -abort` merupakan perintah yang digunakan apabila kita ingin membatalakan proses rebase sepenuhnya.
   Setelah proses rebase berhasil dibatalkan, maka kondisi repostiory akan berubah kembali seperti awal sebelum proses rebase.

3. **Apa perbedaan Git Merge dengan Git Rebase? Buatlah/carilah ilustrasi yang dapat menggambarkan perbedaanya!**
   - Perbedaan Git Merge dan Git Rebase adalah pada cara penggabungan perubahan atau migrasi ke branch tujuannya. Berikut
   merupakan proses yang terjadi untuk penggabungan perubahan pada Git Merge : 
     ![image](https://github.com/HyperPulsor/random-aja/assets/101686378/5725db2f-0ed7-4d15-b0ce-1e69d8160e44)
   - Contoh gambar diatas adalah proses merge branch main ke branch feature dengan Git Merge. Proses merge akan menghasilkan
   merge commit baru pada branch feature yang mencakup perubahan pada kedua branch (main dan feature).
   
     ![image](https://github.com/HyperPulsor/random-aja/assets/101686378/c61a7b85-93a3-4dcf-8300-fca6f304c7fe)
   - Contoh gambar diatas adalah proses merge branch main ke branch feature dengan Git Rebase. Proses merge akan memindahkan keseluruhan
   branch feature ke ujung dari branch main, sehingga memasukkan semua commit baru dari branch feature ke branch main. Berbeda dengan Git Merge
   yang membuat suatu merge commit baru, Git Rebase menggantikan riwayat branch main dengan menambahkan commit-commit baru dari branch
   feature pada branch main. Dengan demikian, riwayat commit pada proyek lebih *clean*.

4. **Mengapa hal pada langkah no 4 bisa terjadi? Mengapa git stash menjadi solusinya?**
   - Proses checkout pada langkan no 4 gagal karena perubahan lokal yang sudah dibuat yaitu penambahan line "this is the second line"
   akan hilang apabila kita pindah ke branch `feat/tutorial-6-advancedgit-1`. Hal ini dikarenakan pada branch `feat/tutorial-6-advancedgit-1`
   tidak terdapat folder git-stash, feature-stash.txt, ataupun isi di dalam file tersebut. Git stash menjadi salah satu solusi karena
   menyimpan perubahan lokal yang belum dilakukan commit. Setelah dilakukan command `git stash`, maka perubahan lokal tadi menghilang dari file
   dan disimpan secara lokal (dapat dimunculkan kembali dengan `git stash pop`. Oleh karena perubahan lokal baru sudah tidak ada, maka dapat pindah ke branch `feat/tutorial-6-advancedgit-1` karena
   tidak ada perubahan lokal baru dari branch `feature-stash` yang dimana struktur filenya sama dengan branch `feat/tutorial-6-advancedgit-1`.

5. **Sebutkan dan jelaskan tiga tipe dari Git Reset!**
   - `git reset --hard` merupakan perintah untuk membatalkan perubahan dimana merubah `HEAD` ke commit yang dituju/dispesifikan
   dan 
   - `git reset --soft` merupakan perintah untuk membatalkan perubahan dimana merubah `HEAD` ke commit yang dituju, tetapi
   menyimpan semua perubahan yang telah masuk di area staging (setelah `git add .`, sebelum `git commit`) dari commit terakhir.
   - `git reset --mixed` merupakan perintah untuk membatalkan perubahan dimana merubah `HEAD` ke commit yang dituju, tetapi
   menyimpan semua perubahan dan dikeluarkan dari area staging (sebelum `git add .`) dari commit terakhir.

6.  **Apa itu git revert? Apa perbedaannya dengan git reset?**
    - `git revert` merupakan perintah untuk mengembalikan kondisi file ke commit yang dituju dengan membuat suatu commit baru
   yang dinamakan "revert commit" sehingga sejarah commit itu tidak terhapus. Perbedaannya adalah perubahan pada
   sejarah commit dimana sejarah tersebut diubah pada `git reset`. Pada `git reset`, saat kita kembali ke commit
   tertentu, maka commit-commit setelahnya akan terhapus dan sejarah commit tersimpan hanya sebelumnya saja. Sedangkan,
   pada `git revert`, sejarah commit sebelum dan setelah commit yang dituju tetap tersimpan.

7. **Buatlah grafik yang menggambarkan alur commit pada bagian Git Flow and Branching ini serta jelaskan! Grafik dapat berupa tulis tangan maupun menggunakan software.**
   ![Screenshot_682](https://github.com/HyperPulsor/pbp-flutter-lab/assets/101686378/b13b3891-6a1a-4f78-852b-3e2fe9687a40)

   - Pertama, dilakukan merge request dari branch `feat/tutorial-6-advancedgit-1` ke branch `main` sehingga menghasilkan
   commit baru berupa merge request pada branch `main` dan ditambahkan pada commit tree.
   - Kedua, akan membuat branch baru `development` dengan command `git checkout -b development` dari branch `main`. Oleh,
   karena itu, file pada branch `development` akan sama dengan file yang ada di branch `main` dan tidak ada commit tambahan.
   - Ketiga, pembuatan branch baru yaitu `feature-a` dengan command `git checkout -b feature-a` dari branch `development`.
     Oleh, karena itu, file pada branch `feature-a` akan sama dengan file yang ada di branch `development`. Lalu, terdapat
     commit tambahan baru berupa pengerjaan fitur a pada branch tersebut dan merge request dari branch `feature-a` ke branch
     `development` sehingga ada dua commit tambahan (branch `feature-a` dan branch `development`).
   - Keempat, pembuatan branch baru yaitu `feature-b` dengan command `git checkout -b feature-b` dari branch `development`.
     Oleh, karena itu, file pada branch `feature-b` akan sama dengan file yang ada di branch `development`. Lalu, terdapat
     commit tambahan baru berupa pengerjaan fitur b pada branch tersebut. Akan tetapi, ternyata terdapat commit terbaru dari branch
     `development` (merge request pengerjaan fitur a) sehingga dilakukan rebase. Dengan melakukan rebase, maka riwayat commit dari pengerjaan
     fitur b akan dipindahkan ke setelah commit terbaru (tapi tidak dibuat commit baru). Oleh karena terdapat konflik, maka ditambahkan commit lagi untuk mensolve
     konflik.
   - Kelima, akan dilakukan merge request dari branch `feature-b` ke branch `development` untuk menggabungkan development dengan fitur b
     yang telah dikerjakan. Oleh karena itu, dibuat commit merge request baru pada branch `development`.

8. **Apa kegunaan dari langkah di atas?**
   - Langkah di atas terletak pada HTTP Header Manager dimana mengatur semua HTTP header yang akan dikirimkan ke server (localhost:8080).
   Pada langkah tersebut, kita mengisi header HTTP berupa content-type dengan value application/json. Setelah melakukan langkah ini, 
   setiap kali request dikirimkan ke server, maka header dengan informasi content-type json juga ikut dikirimkan. Hal ini
   akan memberi tahu server bahwa konten yang akan dikirimkan sebagai response akan berupa tipe file json.

9. **Apa itu JSON Extractor? Sebutkan semua kegunaannya di Test Plan ini!**
   - JSON Extractor merupakan suatu alat pada JMeter yang mengambil value-value yang didapatkan dari suatu JSON response.
   Dalam Extractor ini, diisi nama variabel untuk menyimpan value yang sudah diekstraksi yaitu `idBuku` dan JSON Path expressions untuk mengambil
   nilai yang akan disimpan. Pada Test Plan ini, JSON Path Expression diisi dengan `$.id` untuk mendapatkan value ID Buku dari
   response JSON setelah suatu buku dibuat dan akan disimpan pada variabel `idBuku`. Kemudian, variabel ini dapat diakses pada JSON Assertion
   untuk update Buku berdasarkan id buku yang telah disimpan.

10. **Apa itu Assertions dalam JMeter? Sebutkan contoh 3 Assertions dan kegunaannya!**
    - Assertions dalam JMeter merupakan alat yang digunakan untuk memverifikasi atau memvalidasi suatu response. Pada test plan ini,
    JSON Assertion mengecek apakah dalam JSON response memiliki ID Buku dengan JSON Path `$.id` dengan expected value `${idBuku}` dari variabel
    yang menyimpan ID Buku setelah dicreate. Berikut merupakan tiga contoh Assertions :
      1. Response Assertion, assertion yang berfungsi untuk memeriksa apakah response dari server sudah sesuai dengan ketentuan yang kita tetapkan
      2. Duration Assertion, assertion yang berfungsi untuk memeriksa apakah waktu response diterima sesuai rentang waktu yang ditentukan
      3. Size Assertion, assertion yang berfungsi untuk memeriksa apakah ukuran response sesuai ketentuan yang ditetapkan

11. **Apa itu Number of Threads dan Ramp-up Period? Apa hubungan antar keduanya?**
    - Number of Threads adalah jumlah "user" virtual yang akan menjalankan semua tes pada test plan yang sama secara independen.
    Ramp-up Period adalah waktu yang dibutuhkan oleh JMeter untuk mengeksekusi semua threads. Hubungannya adalah apabila kita menentukan
    1000 threads dengan 50 detik Ramp-up Period, maka Jmeter akan menambahkan atau mengeksekusi threads sebanyak 20 threads per detik (jumlah threads / ramp-up period).
    Oleh karena itu, butuh total waktu 50 detik untuk secara penuh menambahkan atau mengeksekusi 1000 threads atau semua threads.

12. **Gunakan angka 1000 untuk Number of Threads dan 100 untuk Ramp-up period. Jalankan Test Plan dengan konfigurasi tersebut. Kemudian, perhatikan Summary Report, View Result Tree, Graph Result, dan Assertion Result. Buatlah penjelasan minimal 2 paragraf untuk menjelaskan temuan menarik kalian terhadap hasil-hasil tersebut. Sertakan screenshot dari keempat result tersebut. Sertakan juga info mengenai prosesor, RAM, dan penggunaan hardisk HDD atau SSD dari perangkat Anda. 
(Jika perangkat Anda tidak kuat dengan angka konfigurasi tersebut, silakan turunkan angkanya.).**
    - Temuan menarik yang telah saya temukan adalah bahwa pada hasil View Result Tree dimana untuk test yang sama tetap bisa mengalami error.
    Padahal, untuk fitur search buku tetap menggunakan query judul yang sama, tetapi masih berpotensi untuk mengalami error. Error yang timbul
    adalah error dengan status kode "500 Internal Server Error". Hal ini menandakan bahwa server mengalami kondisi tak terduga yang mencegahnya memenuhi request.
    Ketidakmampuan server dalam memenuhi request dapat diakibatkan karena load server yang terlalu tinggi. Load yang tinggi dapat menyebabkan
    server hanya menerima request, tetapi tidak bisa memproses dan mengirimkan kembali response yang diinginkan. Oleh karena load server yang tinggi,
    maka beberapa test bisa mengalami error atau gagal karena ketidakmampuan server untuk memberikan kembali response yang diminta.
      ![Screenshot_686](https://github.com/HyperPulsor/Deploy_TKPBPA10/assets/101686378/3bfad3cd-8bfd-48b3-99ca-e0d5ada6ffaa)
    - Temuan menarik lain yang saya temukan pada hasil Assertion Results dimana mayoritas dari assertion yang dilakukan, request update buku
    banyak yang tidak berhasil diverifikasi. Akan tetapi, ada juga hasil Assertion dari update request buku yang berhasil diverifikasi.
    Permasalahan dari ketidakberhasilan verifikasi disebabkan oleh tidak adanya JSON path untuk `$['id']` dan `$['judul']`. Hal ini dikarenakan
    JSON response tidak mengandung field `id` ataupun `judul` di dalamnya. Penyebabnya adalah karena "Internal Server Error" yang terjadi sehingga
    menyebabkan response JSON tidak memiliki field `id` ataupun `judul`.
    
      ![image](https://github.com/HyperPulsor/Deploy_TKPBPA10/assets/101686378/eaaf3841-d480-465c-9fc5-75b55a0206b5)
      ![Screenshot_684](https://github.com/HyperPulsor/Deploy_TKPBPA10/assets/101686378/628994cb-7efb-4ede-9291-cc95203feb1c)
      ![Screenshot_685](https://github.com/HyperPulsor/Deploy_TKPBPA10/assets/101686378/fd776122-7efc-4e24-bb1a-b88fcaec25f8)
    - Info Spesifikasi
      - Prosessor : **AMD Ryzen 7 5800H (8 core, 16 threads)**
      - RAM : **16  GB**
      - Storage : **SSD 500 GB**

13. **Sembari menjalankan Test Plan, perhatikan pergerakan grafik pada JConsole. Buatlah penjelasan minimal 2 paragraf untuk menjelaskan temuan menarik 
kalian terhadap hasil-hasil tersebut. Sertakan screenshot dari grafik-grafik tersebut.**
    - Terdapat empat grafik yang ditampilkan pada JConsole, yaitu grafik Heap Memory Usage, Threads,
    Classes, dan CPU Usage. Grafik ini bertujuan untuk mengawasi penggunaan *resources* selama Load Testing dilakukan
    (berdasarkan timestamp jam). Temuan yang menarik adalah bahwa pada grafik Threads, Classes, dan CPU Usage mengalami kenaikan
    drastis sampai ke *peak*-nya pada awal Test Plan dijalankan. Berbeda dengan grafik Heap Memory Usage, dimana grafiknya fluktuatif,
    tetapi mengalami kenaikan secara konsisten sampai semua Test Plan selesai dieksekusi.
    - Setelah ketiga grafik mengalami kenaikan drastis,
    setiap grafik menunjukkan garis yang relatif stagnan atau konsisten. Hal ini menandakan bahwa Threads, Classes, dan CPU Usage (fluktuatif tapi tidak tinggi) dibutuhkan
    tingkat penggunaan yang tinggi di awal, tetapi turun sampai menjadi stagnan dan konsisten menggunakan *resources* tersebut seiring
    berjalannya Test Plan sampai selesai. Pada grafik Heap Memory Usage, menunjukkan bahwa terdapat penuruan drastis setelah semua
    Test Plan dieksekusi dan kemudian penggunaannya kembali normal.
    
      ![Screenshot_683](https://github.com/HyperPulsor/Deploy_TKPBPA10/assets/101686378/05cbfca1-7685-4997-90c7-8692d354a9f0)

14. **Apa itu Load Testing? Buatlah kesimpulan dari pengerjaan tutorial JMeter & JConsole ini.**
    - Load Testing merupakan suatu pengujian yang dilakukan untuk mengetahui perilaku server atau sistem pada saat kondisinya
    normal atau beban tinggi. Pengujian ini dilakukan dengan mensimulasikan user (yang dibuat JMeter) dan aksi mereka dalam melakukan 
    task-task yang sudah ditentukan untuk menguji fungsionalitas tersebut dan kemampuan servernya pada saat mengalami beban tinggi. 
    Kesimpulan dari pengerjaan tutorial JMeter & JConsole ini adalah untuk menguji 
    performa fungsionalitas & server, kapasitas maksimum server, cara server dalam menangani beban tinggi dari 
    beberapa fungsionalitas milik BacaBacaApplication (create, update, search by judul, dan random), dan validasi JSON response setiap fungsionalitas. Untuk melakukan pengujian tersebut,
    digunakan aplikasi JMeter untuk mensimulasikan "user" atau threads. Threads tersebut kemudian akan mengeksekusi berbagai Test Plan yang sama
    berdasarkan waktu ramp-up periode yang telah ditetapkan. Setelah semua threads berhasil dieksekusi, kita dapat menggunakan tools, seperti Assertion Results, 
    Graph Results, View Result Tree, dan Sumary Report untuk melihat performa dan hasil tes & validasi dari setiap fungsionalitas. Temuan menarik yang telah ditemukan adalah
    karena beban yang tinggi terkadang fungsionalitas bisa mengalami error "500 Internal Server Error" sehingga menghasilkan JSON response yang salah dan terkadang juga
    fungsionalitas berhasil dijalankan.
    
    - Lalu, kita dapat menggunakan aplikasi JConsole untuk melihat grafik penggunaan *resources* server
    (komputer lokal kita) pada saat menjalankan Test Plan pada JMeter, seperti Heap Memory Usage, Classes, Threads, dan CPU Usage.
    Dari grafik tersebut, kita dapat mengetahui bagaimana server menggunakan berbagai sumber daya miliknya untuk mengatasi
    *load* beban yang tinggi. Temuan menarik yang ditemukan adalah untuk grafik Threads, Classes, dan CPU Usage mengalami kenaikan drastis pada
    awal eksekusi Test Plan kemudian menjadi relatif konsisten atau stagnan setelah beberapa saat, selama Test Plan dijalankan.
    Kemudian, pada grafik Heap Memory Usage, dimana grafiknya fluktuatif, tetapi mengalami kenaikan secara konsisten sampai semua Test Plan selesai dieksekusi.

### What I did not understand
- [X] None

## Tutorial 5
### What I have learned today
1. Penggunaan API pada Spring Boot
2. Visualisasi data lewat Chart
3. HTTP Request dan HTTP Response
4. Penggunaan Postman untuk API

### Pertanyaan
1. **Apa itu Postman? Apa kegunaannya?**
   - Postman merupakan sebuah platform API yang digunakan untuk membangun, menguji, dan
   memodifikasi API. Postman memiliki kemampuan untuk membuat berbagai macam request HTTP,
   menyimpan *environments*, dan konversi API ke kode dalam banyak bahasa pemrograman. Kegunaan utama
   dari Postman adalah untuk mengirimkan dan menerima request ke suatu *server* untuk pembuatan dan pengetesan
   API.

2. **Apa yang terjadi ketika kita tidak menggunakan @JsonIgnoreProperties dan @JsonProperty pada model Buku dan 
Penulis? apabila terjadi error, mengapa hal tersebut dapat terjadi?**
   - Tidak error. Saat kita menggunakan @JsonIgnoreProperties pada model Buku dan Penulis, ktia hanya
   menentukan fields apa saja yang tidak diikutsertakan saat diserialisasikan menjadi suatu objek JSON. Ketidakhadiran
   @JsonIgnoreProperties berarti semua fields diikutsertakan dalam objek JSON (tidak ada data tersembunyi). Sedangkan
   @JsonProperty, menentukan nama dari fields yang akan digunakan sebagai representasi pada objek JSON.
   Ketidakhadiran @JsonProperty berarti fields akan menggunakan penamaan default dari nama fields yang ada di class Java.
3. **Pada tutorial ini, kita mencoba untuk memanggil data dengan menggunakan method GET. Namun, apakah kita dapat memanggil data dengan method lainya, seperti POST? Jelaskan pendapat kalian?**
   - Iya, kita dapat menggunakan method lain, seperti POST untuk memanggil data. Hal ini dikarenakan method request hanya sebatas
   untuk dibaca oleh server. Aksi dari request tersebut tidak ditentukan oleh tipe method request sehingga bisa saja
   method POST digunakan untuk mengambil data. Akan tetapi, hal ini bukan merupakan *best practice* dan pasti terdapat kelebihan
   lebih yang dimiliki oleh method GET untuk proses pengambilan data, seperti keamanan, performa, dan lain-lain.
4. **Selain method GET dan POST, sebutkan dan jelaskan secara singkat HTTP request methods lainnya yang dapat kita gunakan!**
   - PUT, metode request untuk memperbarui atau mengganti data yang sudah ada pada database server.
   - PATCH, metode request untuk menerapkan sebagian modifikasi dari suatu data.
   - DELETE, metode request untuk menghapus data-data tertentu dari database server.
   - HEAD, metode request yang mirip dengan GET tetapi tanpa menyertakan body dari response.
   - OPTIONS, metode reqeust yang mendeskripsikan opsi komunikasi ke target data.
5. **Apa kegunaan atribut WebClient?**
   - WebClient merupakan suatu interface yang merepresentasikan entri point utama dalam melakukan
   request web. Dengan menggunakan WebClient, kita dapat membuild suatu request dengan menetapkan url, headers, data,
   dan lain-lain. Selain mengirimkan request, kita juga dapat menerima response. Dengan menggunakan WebClient, baik 
   response dan request, dapat diintegrasikan langsung dengan Spring Boot. Dengan demikian, dalam implementasi Spring Boot
   kita dapat menggunakan API eskternal untuk mengirimkan request dan menerima response HTTP.

### What I did not understand
- [X] None

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



