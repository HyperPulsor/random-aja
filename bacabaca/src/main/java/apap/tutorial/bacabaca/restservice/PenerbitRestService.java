package apap.tutorial.bacabaca.restservice;
import apap.tutorial.bacabaca.model.Penerbit;
import java.util.List;
public interface PenerbitRestService {
    List<Penerbit> retrieveRestAllPenerbit();
    void createRestPenerbit(Penerbit penerbit);
    void updateRestPenerbit(Penerbit penerbitDTO, Long id);
    void deleteRestPenerbit(Penerbit penerbit);
    Penerbit getRestPenerbitById(Long id);
}
