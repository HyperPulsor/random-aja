package apap.tutorial.bacabaca.dto.request;
import apap.tutorial.bacabaca.model.Penerbit;
import apap.tutorial.bacabaca.model.Penulis;
import lombok.*;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateBukuRequestDTO extends CreateBukuRequestDTO {
    private UUID id;
}
