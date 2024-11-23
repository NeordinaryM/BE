package neordinaryHackathon.neordinaryHackathon.converter;

import neordinaryHackathon.neordinaryHackathon.domain.House;
import neordinaryHackathon.neordinaryHackathon.dto.house.HouseDto;
import neordinaryHackathon.neordinaryHackathon.dto.house.HouseResponseDto;

import java.util.ArrayList;
import java.util.List;

public class HouseConverter {
    //하우스 리스트 dto를 하우스 목록 조회 result로 변환
    public static HouseResponseDto.getHousesResult toGetHousesResult(List<HouseDto> houseDtos) {
        return HouseResponseDto.getHousesResult.builder()
                .houses(houseDtos)
                .build();
    }





    //하우스 리스트에서 필요한 정보만 빼서, dto로 변환한다.
    public static List<HouseDto> toHousesDto(List<House> houses) {
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

    //하우스 단 건 객체를 dto로 변환
    public static HouseResponseDto.getHouseResult toHouseDto(House house) {
        return HouseResponseDto.getHouseResult.builder()
                .houseId(house.getHouseId())
                .name(house.getName())
                .date(house.getDate())
                .location(house.getLocation()).build();

    }

}
