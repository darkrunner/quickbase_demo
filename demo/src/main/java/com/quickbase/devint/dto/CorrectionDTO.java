package com.quickbase.devint.dto;

import com.quickbase.devint.model.Correction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorrectionDTO {
    private String source;
    private String target;

    public static CorrectionDTO fromEntity(Correction correction) {
        return CorrectionDTO.builder()
                .source(correction.getSource())
                .target(correction.getTarget())
                .build();
    }
}
