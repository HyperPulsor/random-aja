package apap.tutorial.bacabaca.controller;
import apap.tutorial.bacabaca.controller.DTO.BukuDTO;
import apap.tutorial.bacabaca.model.Buku;
import apap.tutorial.bacabaca.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Controller
public class BukuController {
    @Autowired
    private BukuService bukuService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("buku/create")
    public String formAddBuku(Model model){
        //Membuat DTO BARU sebagai isian form pengguna
        var bukuDTO = new BukuDTO();
        model.addAttribute("bukuDTO", bukuDTO);
        return "form-create-buku";
    }

    @GetMapping("buku/viewall")
    public String listBuku(Model model){
        //Mendapatkan semua buku
        List<Buku> listBuku = bukuService.getAllBuku();

        //Add variabel semua bukuModel ke "ListBuku" untuk dirender pada thymeleaf
        model.addAttribute("listBuku", listBuku);
        return "viewall-buku";
    }

    @GetMapping("buku/{id}/update")
    public String getUpdateBuku(@PathVariable(value = "id") UUID id, Model model){
        var bukuUpdate = bukuService.getBukuById(id);
        var bukuDTOUpdate = new BukuDTO(id, bukuUpdate.getJudul(), bukuUpdate.getPenulis(),
                bukuUpdate.getTahunTerbit(), bukuUpdate.getHarga());
        model.addAttribute("bukuDTOUpdate", bukuDTOUpdate);
        return "form-update-buku";
    }

    @PostMapping("buku/{id}/update")
    public String updateBuku(@ModelAttribute BukuDTO bukuDTOUpdate, Model model){
        if (!bukuService.validateBuku(bukuDTOUpdate.getJudul())){
            model.addAttribute("id", bukuDTOUpdate.getId());
            return "failed-create-buku";
        }
        var buku = new Buku(bukuDTOUpdate.getId(),bukuDTOUpdate.getJudul(),
                bukuDTOUpdate.getPenulis(), bukuDTOUpdate.getTahunTerbit(), bukuDTOUpdate.getHarga());
        bukuService.setBuku(buku);
        return redirectPage(buku, model);
    }

    @GetMapping("buku/update")
    public String redirectPage(Buku buku, Model model){
        //Add variabel id buku ke 'id' untuk dirender di thymeleaf
        model.addAttribute("id", buku.getId());
        return "success-update-buku";
    }

    @RequestMapping("buku/{id}/delete")
    public String deleteBuku(@PathVariable(value = "id") UUID id, Model model){
        model.addAttribute("id", id);
        bukuService.removeBuku(id);
        return "success-delete-buku";
    }

    @GetMapping("buku/{id}")
    public String detailBuku(@PathVariable(value = "id") UUID id, Model model){
        //Mendapatkan buku melalui kodeBuku
        var buku = bukuService.getBukuById(id);
        model.addAttribute("buku", buku);
        return "view-buku";
    }

    @PostMapping("buku/create")
    public String addBuku(@ModelAttribute BukuDTO bukuDTO, Model model){
        //Generate id buku dengan UUID
        UUID newId = UUID.randomUUID();
        if (!bukuService.validateBuku(bukuDTO.getJudul())){
            model.addAttribute("id", newId);
            return "failed-create-buku";
        }
        //Membuat object Buku dengan data yang berasal dari DTO
        var buku = new Buku(newId, bukuDTO.getJudul(), bukuDTO.getPenulis(),
                bukuDTO.getTahunTerbit(), bukuDTO.getHarga());

        //Memanggil Service addBuku
        bukuService.createBuku(buku);

        //Add variabel id buku ke 'id' untuk dirender di thymeleaf
        model.addAttribute("id", buku.getId());

        //Add variabel judul ke 'judul' untuk dirender di thymeleaf
        model.addAttribute("judul", buku.getJudul());

        return "success-create-buku";
    }
}
