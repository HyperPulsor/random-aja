package apap.tutorial.bacabaca.controller;
import apap.tutorial.bacabaca.dto.BukuMapper;
import apap.tutorial.bacabaca.dto.request.CreateBukuRequestDTO;
import apap.tutorial.bacabaca.dto.request.UpdateBukuRequestDTO;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.model.Penulis;
import apap.tutorial.bacabaca.service.BukuService;
import apap.tutorial.bacabaca.service.PenerbitService;
import apap.tutorial.bacabaca.service.PenulisService;
import jakarta.persistence.OrderBy;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
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
    @Autowired
    private PenulisService penulisService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("buku/create")
    public String formAddBuku(Model model){
        //Membuat DTO BARU sebagai isian form pengguna
        var bukuDTO = new CreateBukuRequestDTO();
        model.addAttribute("bukuDTO", bukuDTO);
        var listPenerbit = penerbitService.getAllPenerbit();
        model.addAttribute("listPenerbit", listPenerbit);
        var listPenulisExisting = penulisService.getAllPenulis();
        model.addAttribute("listPenulisExisting", listPenulisExisting);

        return "form-create-buku";
    }

    @GetMapping("buku/viewall-with-datatables")
    public String viewAllDatatables(Model model){
        List<Buku> listBuku = bukuService.orderBukuJudul();
        //Add variabel semua bukuModel ke "ListBuku" untuk dirender pada thymeleaf
        model.addAttribute("listBuku", listBuku);
        return "viewall-with-datatables";
    }

    @PostMapping(value = "buku/create", params = {"addRow"})
    public String addRowPenulisBuku(@ModelAttribute CreateBukuRequestDTO createBukuRequestDTO, Model model){
        if (createBukuRequestDTO.getListPenulis() == null || createBukuRequestDTO.getListPenulis().size() == 0){
            createBukuRequestDTO.setListPenulis(new ArrayList<>());
        }

        createBukuRequestDTO.getListPenulis().add(new Penulis());
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", createBukuRequestDTO);
        return "form-create-buku";
    }

    @PostMapping(value = "buku/update", params = {"addRow"})
    public String addRowPenulisBukuUpdate(@ModelAttribute UpdateBukuRequestDTO updateBukuRequestDTO, Model model){
        if (updateBukuRequestDTO.getListPenulis() == null || updateBukuRequestDTO.getListPenulis().isEmpty()){
            updateBukuRequestDTO.setListPenulis(new ArrayList<>());
        }
        updateBukuRequestDTO.getListPenulis().add(new Penulis());
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", updateBukuRequestDTO);
        return "form-update-buku";
    }

    @PostMapping(value = "buku/update", params = {"deleteRow"})
    public String deleteRowPenulisBukuUpdate(@ModelAttribute UpdateBukuRequestDTO updateBukuRequestDTO, @RequestParam("deleteRow") int row, Model model){
        updateBukuRequestDTO.getListPenulis().remove(row);
        model.addAttribute("bukuDTO", updateBukuRequestDTO);
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        return "form-update-buku";
    }

    @PostMapping(value = "buku/create", params = {"deleteRow"})
    public String deleteRowPenulisBuku(@ModelAttribute CreateBukuRequestDTO createBukuRequestDTO, @RequestParam("deleteRow") int row, Model model){
        createBukuRequestDTO.getListPenulis().remove(row);
        model.addAttribute("bukuDTO", createBukuRequestDTO);
        model.addAttribute("listPenulisExisting", penulisService.getAllPenulis());
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
        var listPenulisExisting = penulisService.getAllPenulis();
        model.addAttribute("listPenerbit", penerbitService.getAllPenerbit());
        model.addAttribute("bukuDTO", bukuDTO);
        model.addAttribute("listPenulisExisting", listPenulisExisting);
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
