package apap.tutorial.bacabaca.controller;

import apap.tutorial.bacabaca.dto.PenulisMapper;
import apap.tutorial.bacabaca.dto.request.CreatePenulisRequestDTO;
import apap.tutorial.bacabaca.dto.request.DeleteMultiplePenulisDTO;
import apap.tutorial.bacabaca.dto.request.UpdateBukuRequestDTO;
import apap.tutorial.bacabaca.model.Penulis;
import apap.tutorial.bacabaca.model.Sertifikasi;
import apap.tutorial.bacabaca.service.PenulisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class PenulisController {
    @Autowired
    PenulisService penulisService;

    @Autowired
    PenulisMapper penulisMapper;

    @GetMapping("penulis/create")
    public String formAddPenulis(Model model){
        var penulisDTO = new CreatePenulisRequestDTO();

        model.addAttribute("penulisDTO", penulisDTO);
        return "form-create-penulis";
    }

    @PostMapping(value = "penulis/create", params = {"addRow"})
    public String addRowSertifikasi(@ModelAttribute CreatePenulisRequestDTO createPenulisRequestDTO, Model model){
        if (createPenulisRequestDTO.getListSertifikasi() == null || createPenulisRequestDTO.getListSertifikasi().isEmpty()){
            createPenulisRequestDTO.setListSertifikasi(new ArrayList<>());
        }
        createPenulisRequestDTO.getListSertifikasi().add(new Sertifikasi());
        model.addAttribute("penulisDTO", createPenulisRequestDTO);
        return "form-create-penulis";
    }

    @PostMapping(value = "penulis/create", params = {"deleteRow"})
    public String deleteRowSertifikasi(@ModelAttribute CreatePenulisRequestDTO penulisDTO, @RequestParam("deleteRow") int row, Model model){
        penulisDTO.getListSertifikasi().remove(row);
        model.addAttribute("penulisDTO", penulisDTO);
        return "form-create-penulis";
    }

    @PostMapping("penulis/create")
    public String addPenulis(@ModelAttribute CreatePenulisRequestDTO createPenulisRequestDTO, Model model){
        var penulis = penulisMapper.createPenulisRequestDTOToPenulis(createPenulisRequestDTO);
        if (!penulis.getListSertifikasi().isEmpty()){
            for (int i = 0; i < penulis.getListSertifikasi().size(); i++){
                penulis.getListSertifikasi().get(i).setPenulis(penulis);
            }
        }
        penulisService.createPenulis(penulis);
        model.addAttribute("penulis", createPenulisRequestDTO);
        return "success-create-penulis";
    }

    @GetMapping("penulis/viewall")
    public String listPenulis(Model model){
        var listPenulis = penulisService.getAllPenulis();
        var deleteDTO = new DeleteMultiplePenulisDTO();
        model.addAttribute("listPenulis", listPenulis);
        model.addAttribute("deleteDTO", deleteDTO);
        return "viewall-penulis";
    }

    @PostMapping("penulis/delete")
    public String deleteMultiplePenulis(@ModelAttribute DeleteMultiplePenulisDTO deleteDTO){
        penulisService.deleteListPenulis(deleteDTO.getListPenulis());
        return "success-delete-penulis";
    }
}
