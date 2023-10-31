package apap.tutorial.bacabaca.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BukuPopuler {
    private String book_id;
    private String position;
    private String name;
    private String cover;
    private Double rating;
    private String url;
}
