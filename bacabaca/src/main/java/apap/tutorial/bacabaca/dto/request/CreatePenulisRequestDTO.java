package apap.tutorial.bacabaca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import apap.tutorial.bacabaca.model.Sertifikasi;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePenulisRequestDTO {
    private String namaPenulis;
    private String biografi;
    // setelah panduan selesai, di bagian resources
    private List<Sertifikasi> listSertifikasi;
}