package gg.om.omgg.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RenewRequestDTO {
    private String name;

    @Builder
    public RenewRequestDTO(String name) {
        this.name = name;
    }
}
