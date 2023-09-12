package apap.tutorial.bacabaca.service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import apap.tutorial.bacabaca.model.Buku;
@Service
public class BukuServiceImpl implements  BukuService{
    private List<Buku> listBuku;

    public BukuServiceImpl() {
        listBuku = new ArrayList<>();
    }

    @Override
    public void createBuku(Buku buku) {
        listBuku.add(buku);
    }

    @Override
    public List<Buku> getAllBuku() {
        return listBuku;
    }

    @Override
    public Buku getBukuById(UUID id) {
        for (Buku buku : listBuku) {
            if (buku.getId().equals(id)) {
                return buku;
            }
        }
        return null;
    }
}
