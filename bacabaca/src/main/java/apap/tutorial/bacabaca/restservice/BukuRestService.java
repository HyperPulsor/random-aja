package apap.tutorial.bacabaca.restservice;
import apap.tutorial.bacabaca.dto.request.TranslateBukuRequestDTO;
import apap.tutorial.bacabaca.model.Buku;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import apap.tutorial.bacabaca.rest.BukuDetail;
import reactor.core.publisher.Mono;
public interface BukuRestService {
    void createRestBuku(Buku buku);
    List<Buku> retrieveRestAllBuku();
    Buku getRestBukuById(UUID id);
    Mono<String> getStatus();
    Mono<BukuDetail> postStatus();
    Buku saveTranslatedJudul(Buku buku, String translatedJudul);
    Buku translateRestBuku(TranslateBukuRequestDTO translateBukuRequestDTO) throws IOException, InterruptedException;
    public Map<String, Double> getPopularRestBuku(String tahun, String bulan) throws IOException, InterruptedException;
}
