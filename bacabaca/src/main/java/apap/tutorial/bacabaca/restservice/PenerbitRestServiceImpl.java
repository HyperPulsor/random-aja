package apap.tutorial.bacabaca.restservice;

import apap.tutorial.bacabaca.model.Penerbit;
import apap.tutorial.bacabaca.repository.PenerbitDb;
import apap.tutorial.bacabaca.service.PenerbitService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PenerbitRestServiceImpl implements PenerbitRestService {
    @Autowired
    private PenerbitDb penerbitDb;

    private final String mockUrl = "https://eca68086-804e-4503-b27b-1c95c7c986a6.mock.pstmn.io";
    private final WebClient webClient;

    public PenerbitRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(mockUrl).build(); // mock server
    }

    @Override
    public List<Penerbit> retrieveRestAllPenerbit() { return penerbitDb.findAll(); }

    @Override
    public void createRestPenerbit(Penerbit penerbit){
        penerbitDb.save(penerbit);
    }

    @Override
    public Penerbit getRestPenerbitById(Long id){
        for (Penerbit penerbit : retrieveRestAllPenerbit()){
            if (penerbit.getIdPenerbit().equals(id)){
                return penerbit;
            }
        }
        return null;
    }

    @Override
    public void updateRestPenerbit(Penerbit penerbitDTO, Long id){
        Penerbit penerbit = getRestPenerbitById(id);
        if (penerbit != null){
            penerbit.setIdPenerbit(id);
            penerbit.setNamaPenerbit(penerbitDTO.getNamaPenerbit());
            penerbit.setAlamat(penerbitDTO.getAlamat());
            penerbit.setEmail(penerbitDTO.getEmail());
            penerbitDb.save(penerbit);
        }
    }

    @Override
    public void deleteRestPenerbit(Penerbit penerbit){
        penerbitDb.delete(penerbit);
    }
}
