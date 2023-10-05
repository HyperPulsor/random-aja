package apap.tutorial.bacabaca.restcontroller;

import apap.tutorial.bacabaca.dto.PenerbitMapper;
import apap.tutorial.bacabaca.dto.request.CreatePenerbitRequestDTO;
import apap.tutorial.bacabaca.dto.request.UpdatePenerbitRequestDTO;
import apap.tutorial.bacabaca.model.Penerbit;
import apap.tutorial.bacabaca.restservice.PenerbitRestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class PenerbitRestController {
    @Autowired
    private PenerbitRestService penerbitRestService;
    @Autowired
    private PenerbitMapper penerbitMapper;

    @GetMapping(value="/penerbit/view-all")
    private List<Penerbit> retrieveAllPenerbit() { return penerbitRestService.retrieveRestAllPenerbit(); }

    @PostMapping(value="/penerbit/create")
    private Penerbit createPenerbit(@Valid @RequestBody CreatePenerbitRequestDTO penerbitDTO, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            var penerbit = penerbitMapper.createPenerbitRequestDTOToPenerbit(penerbitDTO);
            penerbitRestService.createRestPenerbit(penerbit);
            return penerbit;
        }
    }

    @PutMapping(value = "/penerbit/{id}")
    private Penerbit updatePenerbit(@Valid @RequestBody UpdatePenerbitRequestDTO penerbitDTO, @PathVariable("id")String id, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field"
            );
        } else {
            var penerbit = penerbitMapper.updatePenerbitRequestDTOToPenerbit(penerbitDTO);
            penerbitRestService.updateRestPenerbit(penerbit, Long.parseLong(id));
            var penerbitResponse = penerbitRestService.getRestPenerbitById(Long.parseLong(id));
            return penerbitResponse;
        }
    }

    @GetMapping(value = "/penerbit/{id}")
    private Penerbit getPenerbitById(@PathVariable("id")String id){
        try{
            return penerbitRestService.getRestPenerbitById(Long.parseLong(id));
        } catch(NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id Penerbit" + id + "not found"
            );
        }
    }

    @DeleteMapping(value = "/penerbit/{id}")
    private String deletePenerbit(@PathVariable("id")String id){
        try{
            Penerbit penerbit = penerbitRestService.getRestPenerbitById(Long.parseLong(id));
            penerbitRestService.deleteRestPenerbit(penerbit);
            return "Penerbit has been deleted";
        } catch(NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id Penerbit" + id + "not found"
            );
        }
    }
}
