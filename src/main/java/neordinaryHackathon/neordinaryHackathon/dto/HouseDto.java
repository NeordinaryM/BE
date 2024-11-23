package neordinaryHackathon.neordinaryHackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
//하우스 리스트 조회시, 필요한 정보를 담기 위한 DTO
public class HouseDto {
    Long houseId; //하우스 Id
    LocalDate date; //d-day
    String name; //하우스 이름
}
