package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.repository.BukuDb;
import apap.tutorial.bacabaca.rest.BukuDetail;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class BukuRestServiceImpl implements BukuRestService {
    @Autowired
    private BukuDb bukuDb;

    private final String mockUrl = "https://eca68086-804e-4503-b27b-1c95c7c986a6.mock.pstmn.io";
    private final WebClient webClient;

    public BukuRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(mockUrl).build(); // mock server
    }

    @Override
    public Mono<String> getStatus() {
        return this.webClient.get().uri("/rest/buku/status").retrieve().bodyToMono(String.class);
    };

    @Override
    public Mono<BukuDetail> postStatus() {
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("judul", "Buku Cara Membaca Jilid 2");
        data.add("tahunTerbit", "2003");
        var response = this.webClient.post()
                .uri("/rest/buku/full-status")
                .bodyValue(data)
                .retrieve()
                .bodyToMono(BukuDetail.class);
        return response;
    }

    @Override
    public void createRestBuku(Buku buku) {
        bukuDb.save(buku);
    }

    @Override
    public List<Buku> retrieveRestAllBuku() {
        return bukuDb.findAllByOrderByJudulLowerAsc();
    }

    @Override
    public Buku getRestBukuById(UUID id) {
        for (Buku buku : retrieveRestAllBuku()) {
            if (buku.getId().equals(id)) {
                return buku;
            }
        }
        return null;
    }
}
