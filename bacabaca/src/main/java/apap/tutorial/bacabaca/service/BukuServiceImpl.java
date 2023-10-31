package apap.tutorial.bacabaca.service;
import java.util.List;
import java.util.UUID;

import apap.tutorial.bacabaca.repository.BukuDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import apap.tutorial.bacabaca.model.Buku;
@Service
public class BukuServiceImpl implements  BukuService{
    @Autowired
    BukuDb bukuDb;

    @Override
    public void saveBuku(Buku buku){
        bukuDb.save(buku);
    }

    @Override
    public List<Buku> getAllBuku(){
        var listNoDelete = bukuDb.findAllByOrderByJudulLowerAsc();
        for (int i = 0; i < listNoDelete.size(); i++){
            if (listNoDelete.get(i).isIsdDeleted() == Boolean.TRUE){
                listNoDelete.remove(i);
            }
        }
        return listNoDelete;
    }

    @Override
    public Buku getBukuById(UUID kodeBuku){
        for (Buku buku : getAllBuku()){
            if (buku.getId().equals(kodeBuku)){
                return buku;
            }
        }
        return null;
    }

    @Override
    public Buku updateBuku(Buku bukuFromDto){
        Buku buku = getBukuById(bukuFromDto.getId());
        if (buku != null){
            buku.setHarga(bukuFromDto.getHarga());
            buku.setJudul(bukuFromDto.getJudul());
            buku.setListPenulis(bukuFromDto.getListPenulis());
            buku.setTahunTerbit(bukuFromDto.getTahunTerbit());
            buku.setPenerbit(bukuFromDto.getPenerbit());
            bukuDb.save(buku);
        }
        return buku;
    }

    @Override
    public boolean isJudulExist(String judul){
        return getAllBuku().stream().anyMatch(b -> b.getJudul().equals(judul));
    }

    @Override
    public boolean isJudulExist(String judul, UUID id){
        return getAllBuku().stream().anyMatch(b -> b.getJudul().equals(judul) && !b.getId().equals(id));
    }

    @Override
    public void deleteBuku(Buku buku){
        bukuDb.delete(buku);
    }

    @Override
    public List<Buku> searchBukuJudul(String judul){
        return bukuDb.findByJudulContainingIgnoreCase(judul);
    }

    @Override
    public List<Buku> orderBukuJudul(){
        return bukuDb.findAllByOrderByJudulLowerAsc();
    }
}
