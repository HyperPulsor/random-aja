package apap.tutorial.bacabaca.service;
import apap.tutorial.bacabaca.model.Buku;
import java.util.List;
import java.util.UUID;

public interface BukuService {
    // Method untuk menambahkan buku
    void createBuku(Buku buku);

    void setBuku(Buku buku);

    //Method untuk mendapatkan buku yang telah tersimpan
    List<Buku> getAllBuku();

    void removeBuku(UUID id);

    boolean validateBuku(String judul);

    //Method untuk mendapatkan data buku berasarkan kode buku
    Buku getBukuById(UUID id);
}
