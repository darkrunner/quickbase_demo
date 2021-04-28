package com.quickbase.devint.dto;

import com.quickbase.devint.model.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateDTO {
    private Integer stateId;
    private String stateName;

    public static StateDTO fromEntity(State state) {
        return StateDTO.builder()
                .stateId(state.getStateId())
                .stateName(state.getStateName())
                .build();
    }
}
