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

    @Override
    public void setBuku(Buku bukuUpdate){
        for (int i =0; i < listBuku.size(); i++){
            if (listBuku.get(i).getId().equals(bukuUpdate.getId())){
                listBuku.set(i, bukuUpdate);
            }
        }
    }

    @Override
    public void removeBuku(UUID id){
        for (int i =0; i < listBuku.size(); i++){
            if (listBuku.get(i).getId().equals(id)){
                listBuku.remove(listBuku.get(i));
            }
        }
    }

    @Override
    public boolean validateBuku(String judul){
        for (int i = 0; i < listBuku.size(); i++){
            if (listBuku.get(i).getJudul().equals(judul)){
                return false; // Ada judul yang sama pada listBuku
            }
        }
        return true;
    }
}
