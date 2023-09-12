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

    @GetMapping("buku")
    public String detailBuku(@RequestParam("id") UUID id, Model model){
        //Mendapatkan buku melalui kodeBuku
        var buku = bukuService.getBukuById(id);
        model.addAttribute("buku", buku);
        return "view-buku";
    }

    @PostMapping("buku/create")
    public String addBuku(@ModelAttribute BukuDTO bukuDTO, Model model){
        //Generate id buku dengan UUID
        UUID newId = UUID.randomUUID();

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
