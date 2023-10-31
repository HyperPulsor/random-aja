package apap.tutorial.bacabaca.restcontroller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.NoSuchElementException;

import apap.tutorial.bacabaca.dto.BukuMapper;
import apap.tutorial.bacabaca.dto.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.dto.request.TranslateBukuRequestDTO;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.rest.BukuDetail;
import apap.tutorial.bacabaca.restservice.BukuRestService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class BukuRestController {
    @Autowired
    private BukuMapper bukuMapper;

    @Autowired
    private BukuRestService bukuRestService;

    @GetMapping(value = "/buku/view-all")
    private List<Buku> retrieveAllBuku() {
        return bukuRestService.retrieveRestAllBuku();
    }

    @GetMapping(value = "/buku/{id}")
    private Buku retrieveBuku(@PathVariable("id") String id) {
        try {
            return bukuRestService.getRestBukuById(UUID.fromString(id));
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id Buku" + id + "not found"
            );
        }
    }

    @PostMapping(value = "/buku/create")
    public Buku restAddBuku(@Valid @RequestBody CreateBukuRequestDTO bukuDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            var buku = bukuMapper.createBukuRequestDTOToBuku(bukuDTO);
            bukuRestService.createRestBuku(buku);
            return buku;
        }
    }

    @GetMapping(value = "/buku/status")
    private Mono<String> getStatus() {
        return bukuRestService.getStatus();
    }

    @PostMapping(value = "/full")
    private Mono<BukuDetail> postStatus() {
        return bukuRestService.postStatus();
    }

    @PostMapping(value = "/buku/translate")
    private Buku translateBuku(@RequestBody TranslateBukuRequestDTO translateDTO, BindingResult bindingResult) throws IOException, InterruptedException {
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            UUID idBuku = UUID.fromString(translateDTO.getBookId());
            Buku buku = bukuRestService.getRestBukuById(idBuku);
            return bukuRestService.translateRestBuku(translateDTO);
        }
    }
}
