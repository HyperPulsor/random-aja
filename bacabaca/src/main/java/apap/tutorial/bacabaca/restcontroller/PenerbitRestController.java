package apap.tutorial.bacabaca.restcontroller;

import apap.tutorial.bacabaca.model.Penerbit;
import apap.tutorial.bacabaca.restservice.PenerbitRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PenerbitRestController {
    @Autowired
    private PenerbitRestService penerbitRestService;

    @GetMapping(value="/penerbit/view-all")
    private List<Penerbit> retrieveAllPenerbit() { return penerbitRestService.retrieveRestAllPenerbit(); }
}
