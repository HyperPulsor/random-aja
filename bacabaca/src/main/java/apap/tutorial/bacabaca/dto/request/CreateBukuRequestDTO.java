package apap.tutorial.bacabaca.dto.request;
import java.util.List;
import java.util.UUID;

import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Penerbit;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBukuRequestDTO {
    @NotEmpty(message = "Judul tidak boleh kosong")
    private String judul;

    @NotEmpty(message = "Tahun terbit tidak boleh kosong")
    @Pattern(regexp = "\\d{4}", message = "Tahun terbit harus dengan format yang valid")
    private String tahunTerbit;

    @NotNull(message = "Harga tidak boleh kosong")
    @DecimalMin(value = "0.0", message = "Harga tidak boleh negatif")
    private BigDecimal harga;

    @NotNull(message = "Pilih suatu penerbit")
    private Penerbit penerbit;

    private boolean isDeleted;

    private String judulLower;

    public void setJudul(String judul){
        this.judul = judul;
        this.judulLower = judul.toLowerCase();
    }
}
