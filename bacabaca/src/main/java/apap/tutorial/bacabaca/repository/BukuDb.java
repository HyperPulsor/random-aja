package apap.tutorial.bacabaca.repository;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Penerbit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface BukuDb extends  JpaRepository<Buku, UUID> {
    List<Buku> findByJudulIgnoreCase(String judul);
    List<Buku> findAllByOrderByJudulLowerAsc();
    List<Buku> findByPenerbitAndTahunTerbit(Penerbit penerbit, String tahunTerbit);
}
