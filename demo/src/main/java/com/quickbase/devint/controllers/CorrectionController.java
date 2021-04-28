package com.quickbase.devint.controllers;

import com.quickbase.devint.dto.CorrectionDTO;
import com.quickbase.devint.services.CorrectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/correction")
public class CorrectionController {

    @Autowired
    private CorrectionService correctionService;


    @GetMapping
    public List<CorrectionDTO> getAllCorrections() {
        return correctionService.getAllCorrections();
    }

    @PostMapping
    public CorrectionDTO createCorrection(@RequestBody CorrectionDTO correctionDTO) {
        return correctionService.createCorrection(correctionDTO);
    }

    @PutMapping("/{source}")
    public CorrectionDTO updateCorrection(@PathVariable String source, @RequestBody CorrectionDTO correctionDTO) {
        return correctionService.updateCorrection(source, correctionDTO);
    }

    @DeleteMapping("/{source}")
    public void deleteCorrection(@PathVariable String source) {
        correctionService.deleteCorrection(source);
    }

}
