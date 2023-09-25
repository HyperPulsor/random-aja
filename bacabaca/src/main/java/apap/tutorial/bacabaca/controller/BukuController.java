package apap.tutorial.bacabaca.controller;
import apap.tutorial.bacabaca.dto.BukuMapper;
import apap.tutorial.bacabaca.dto.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.dto.request.UpdateBukuRequestDTO;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.service.BukuService;
import apap.tutorial.bacabaca.service.PenerbitService;
import jakarta.persistence.OrderBy;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Controller
public class BukuController {
    @Autowired
    private BukuMapper bukuMapper;
    @Autowired
    private BukuService bukuService;
    @Autowired
    private PenerbitService penerbitService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("buku/create")
    public String formAddBuku(Model model){
        //Membuat DTO BARU sebagai isian form pengguna
        var bukuDTO = new CreateBukuRequestDTO();
        model.addAttribute("bukuDTO", bukuDTO);
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());

        return "form-create-buku";
    }

    @GetMapping("buku/viewall")
    public String listBuku(Model model){
        //Mendapatkan semua buku
        //List<Buku> listBuku = bukuService.getAllBuku();
        List<Buku> listBuku = bukuService.orderBukuJudul();
        //Add variabel semua bukuModel ke "ListBuku" untuk dirender pada thymeleaf
        model.addAttribute("listBuku", listBuku);
        return "viewall-buku";
    }

    @GetMapping("buku/{id}/update")
    public String formUpdateBuku(@PathVariable("id") UUID id, Model model){
        var buku = bukuService.getBukuById(id);
        var bukuDTO = bukuMapper.bukuToUpdateBukuRequestDTO(buku);
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", bukuDTO);
        return "form-update-buku";
    }

    @PostMapping("buku/update")
    public String updateBuku(@Valid @ModelAttribute UpdateBukuRequestDTO bukuDTO, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            List<ObjectError> listErrors = bindingResult.getAllErrors();
            var errorMessage = "";
            for (int i = 0; i < listErrors.size(); i++){
                errorMessage += listErrors.get(i).getDefaultMessage() + "\n";
            }
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }

        if (bukuService.isJudulExist(bukuDTO.getJudul(), bukuDTO.getId())){
            var errorMessage = "Maaf, judul buku sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }
        var bukuFromDto = bukuMapper.updateBukuRequestDTOToBuku(bukuDTO);
        var buku = bukuService.updateBuku(bukuFromDto);
        model.addAttribute("id", buku.getId());
        model.addAttribute("judul", buku.getJudul());
        return "success-update-buku";
    }

    @RequestMapping("buku/{id}/delete")
    public String deleteBuku(@PathVariable(value = "id") UUID id, Model model){
        var buku = bukuService.getBukuById(id);
        bukuService.deleteBuku(buku);
        model.addAttribute("id", id);
        return "success-delete-buku";
    }

    @GetMapping("buku/{id}")
    public String detailBuku(@PathVariable(value = "id") UUID id, Model model){
        //Mendapatkan buku melalui kodeBuku
        var buku = bukuService.getBukuById(id);
        var bukuDTO = bukuMapper.bukuToReadBukuResponseDTO(buku);
        model.addAttribute("buku", bukuDTO);
        return "view-buku";
    }

    @PostMapping("buku/search")
    public String searchBuku(@RequestParam(value="search", required = false)String judul, Model model){
        List<Buku> listFilter;
        if (!judul.isEmpty()){
            listFilter = bukuService.searchBukuJudul(judul);
        } else {
            listFilter = bukuService.getAllBuku();
        }
        model.addAttribute("listBuku", listFilter);
        return "viewall-buku";
    }

    @PostMapping("buku/create")
    public String addBuku(@Valid @ModelAttribute CreateBukuRequestDTO bukuDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            List<ObjectError> listErrors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder();
            for (int i = 0; i < listErrors.size(); i++){
                errorMessage.append(listErrors.get(i).getDefaultMessage()).append("\n");
            }
            model.addAttribute("errorMessage", errorMessage.toString());
            return "error-view";
        }

        if (bukuService.isJudulExist(bukuDTO.getJudul())){
            var errorMessage = "Maaf, judul buku sudah ada";
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }

        var buku = bukuMapper.createBukuRequestDTOToBuku(bukuDTO);

        //Memanggil Service addBuku
        bukuService.saveBuku(buku);

        //Add variabel id buku ke 'id' untuk dirender di thymeleaf
        model.addAttribute("id", buku.getId());

        //Add variabel judul ke 'judul' untuk dirender di thymeleaf
        model.addAttribute("judul", buku.getJudul());

        return "success-create-buku";
    }
}
