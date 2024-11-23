package neordinaryHackathon.neordinaryHackathon.dto.house;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HouseResponseDto {
    List<HouseDto> houses; //하우스들의 정보를 담는 리스트
}
