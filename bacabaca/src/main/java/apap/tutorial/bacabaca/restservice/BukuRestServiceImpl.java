package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.dto.request.TranslateBukuRequestDTO;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.repository.BukuDb;
import apap.tutorial.bacabaca.rest.BukuDetail;
import apap.tutorial.bacabaca.rest.BukuPopuler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class BukuRestServiceImpl implements BukuRestService {
    @Autowired
    private BukuDb bukuDb;

    private final String mockUrl = "https://text-translator2.p.rapidapi.com/translate";
    private final String hapiUrl = "https://hapi-books.p.rapidapi.com/month/2022/3";
    private final WebClient webClient;

    public BukuRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(hapiUrl).build(); // mock server
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

    @Override
    public Buku translateRestBuku(TranslateBukuRequestDTO translateBukuRequestDTO) throws IOException, InterruptedException {
        Buku buku = getRestBukuById(UUID.fromString(translateBukuRequestDTO.getBookId()));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://text-translator2.p.rapidapi.com/translate"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "5ca91ec980mshb7b72bd44602907p168a6ejsn0ae1dd00f28a")
                .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString(
                        "source_language=" + translateBukuRequestDTO.getSourceLanguage() +
                                "&target_language=" + translateBukuRequestDTO.getTargetLanguage() +
                                "&text=" + buku.getJudul()))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200){
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(response.body());
            String translatedJudul = node.get("data").get("translatedText").asText();
            Buku bukuTranslated = saveTranslatedJudul(buku, translatedJudul);
            return bukuTranslated;
        }
        return null;
    }

    @Override
    public Buku saveTranslatedJudul(Buku buku, String translatedJudul){
        buku.setJudul(translatedJudul);
        bukuDb.save(buku);
        return buku;
    }

    @Override
    public Map<String, Double> getPopularRestBuku(String tahun, String bulan) {
        Mono<BukuPopuler[]> listBukuPopulerMono = this.webClient
                .get()
                .uri(URI.create("https://hapi-books.p.rapidapi.com/month/" + tahun + "/" + bulan))
                .header("X-RapidAPI-Key", "5ca91ec980mshb7b72bd44602907p168a6ejsn0ae1dd00f28a")
                .header("X-RapidAPI-Host", "hapi-books.p.rapidapi.com")
                .retrieve()
                .bodyToMono(BukuPopuler[].class);
        Mono<Map<String,Double>> mapMono = listBukuPopulerMono
                .map(bukuPopulers -> Arrays.stream(bukuPopulers)
                        .collect(Collectors.toMap(BukuPopuler::getName, BukuPopuler::getRating)));
        Map<String, Double> listBukuPopuler = mapMono.block();
        return listBukuPopuler;
    }
}
