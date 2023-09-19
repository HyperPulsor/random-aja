package apap.tutorial.bacabaca.repository;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Penulis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;
public interface PenulisDb extends  JpaRepository<Penulis, Long> {
}
