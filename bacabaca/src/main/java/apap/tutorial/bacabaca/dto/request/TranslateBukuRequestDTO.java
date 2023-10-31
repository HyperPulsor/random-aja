package apap.tutorial.bacabaca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TranslateBukuRequestDTO extends CreateBukuRequestDTO{
    private String sourceLanguage;
    private String targetLanguage;
    private String bookId;
}
