package neordinaryHackathon.neordinaryHackathon.converter;

import neordinaryHackathon.neordinaryHackathon.domain.House;
import neordinaryHackathon.neordinaryHackathon.dto.house.HouseDto;
import neordinaryHackathon.neordinaryHackathon.dto.house.HouseResponseDto;

import java.util.ArrayList;
import java.util.List;

public class HouseConverter {

    public static HouseResponseDto.getHousesResult toGetHousesResult(List<HouseDto> houseDtos) {
        return HouseResponseDto.getHousesResult.builder()
                .houses(houseDtos)
                .build();
    }

    //하우스 리스트에서 필요한 정보만 빼서, dto로 변환한다.
    public static List<HouseDto> toHouseDto(List<House> houses) {
        List<HouseDto> result = new ArrayList<>();
        for (House h : houses) {
            HouseDto houseDto = HouseDto.builder()
                    .date(h.getDate())
                    .name(h.getName())
                    .houseId(h.getHouseId())
                    .build();
            result.add(houseDto);
        }
        return result;
    }

}
