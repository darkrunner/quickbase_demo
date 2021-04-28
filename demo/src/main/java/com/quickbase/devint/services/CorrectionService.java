package com.quickbase.devint.services;

import com.quickbase.devint.dto.CorrectionDTO;
import com.quickbase.devint.model.Correction;
import com.quickbase.devint.repository.CorrectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CorrectionService {

    private static final Logger logger = LoggerFactory.getLogger(CorrectionService.class);

    @Autowired
    private CorrectionRepository correctionRepository;

    public List<CorrectionDTO> getAllCorrections() {
        logger.debug("Retrieve all corrections.");
        return  StreamSupport.stream(correctionRepository.findAll().spliterator(), false)
                .map(CorrectionDTO::fromEntity).collect(Collectors.toList());
    }

    public CorrectionDTO createCorrection(CorrectionDTO correctionDTO) {
        logger.debug("Creating correction {} with target {}.", correctionDTO.getSource(), correctionDTO.getTarget());
        Correction correction = new Correction(correctionDTO.getSource(), correctionDTO.getTarget());
        correctionRepository.save(correction);

        return CorrectionDTO.fromEntity(correction);
    }

    public CorrectionDTO updateCorrection(String source, CorrectionDTO correctionDTO) {
        logger.debug("Updating correction {} with new target {}.", source, correctionDTO.getTarget());
        Optional<Correction> correctionOptional = correctionRepository.findById(source);
        CorrectionDTO result = null;
        if(correctionOptional.isPresent()) {
            Correction correction = correctionOptional.get();
            correction.setTarget(correctionDTO.getTarget());
            correctionRepository.save(correction);
            result = CorrectionDTO.fromEntity(correction);
        }
        return result;
    }

    public void deleteCorrection(String source) {
        logger.debug("Deleting correction {}.", source);
        correctionRepository.deleteById(source);
    }


}
