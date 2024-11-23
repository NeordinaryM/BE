package neordinaryHackathon.neordinaryHackathon.dto.house;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


public class HouseResponseDto {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class getHousesResult {
        List<HouseDto> houses; //하우스들의 정보를 담는 리스트

    }
}
