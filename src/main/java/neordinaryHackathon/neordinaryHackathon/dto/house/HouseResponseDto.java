package neordinaryHackathon.neordinaryHackathon.dto.house;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


public class HouseResponseDto {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class getHousesResult {
        List<HouseDto> houses; //하우스들의 정보를 담는 리스트

    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class CreateHouseResult {
        private Long houseId;
    }

    //하우스 단 건 요청 결과를 담는 DTO
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class getHouseResult{
        Long houseId; // 찾을 하우스의 아이디
        String name;
        LocalDate date;
        String location;
    }


}
