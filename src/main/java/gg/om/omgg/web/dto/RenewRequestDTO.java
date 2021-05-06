package gg.om.omgg.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RenewRequestDTO {
    private String name;

    @Builder
    public RenewRequestDTO(String name) { // test code에서 사용 중
        this.name = name;
    }
}
