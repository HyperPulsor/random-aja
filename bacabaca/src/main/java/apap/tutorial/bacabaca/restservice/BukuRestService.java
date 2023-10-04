package apap.tutorial.bacabaca.restservice;
import apap.tutorial.bacabaca.model.Buku;
import java.util.List;
import java.util.UUID;

import apap.tutorial.bacabaca.rest.BukuDetail;
import reactor.core.publisher.Mono;
public interface BukuRestService {
    void createRestBuku(Buku buku);
    List<Buku> retrieveRestAllBuku();
    Buku getRestBukuById(UUID id);
    Mono<String> getStatus();
    Mono<BukuDetail> postStatus();

}
