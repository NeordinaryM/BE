package neordinaryHackathon.neordinaryHackathon.dto.house;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
//하우스 조회시, 필요한 정보를 담기 위한 DTO
public class HouseDto {
    LocalDate date; //d-day
    String name; //하우스 이름
}
