package neordinaryHackathon.neordinaryHackathon.converter;

import neordinaryHackathon.neordinaryHackathon.domain.House;
import neordinaryHackathon.neordinaryHackathon.domain.Member;
import neordinaryHackathon.neordinaryHackathon.dto.HouseDto;
import neordinaryHackathon.neordinaryHackathon.dto.HouseRequestDto;
import neordinaryHackathon.neordinaryHackathon.dto.HouseResponseDto;

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

    public static House toHouse(HouseRequestDto.CreateHouse createHouse, Member member) {
        return House.builder()
                .date(createHouse.getDate())
                .name(createHouse.getGroupName())
                .location(createHouse.getLocation())
                .houseImagesNumber(createHouse.getImageNumber())
                .content(createHouse.getContent())
                .member(member)
                .build();
    }

    public static HouseResponseDto.CreateHouseResult toCreateHouseResult(House house) {
        return HouseResponseDto.CreateHouseResult
                .builder()
                .houseId(house.getHouseId())
                .build();
    }

    public static HouseResponseDto.GetHouseResult toHouseDto(House house) {
        return HouseResponseDto.GetHouseResult.builder()
                .houseId(house.getHouseId())
                .name(house.getName())
                .date(house.getDate())
                .location(house.getLocation()).build();

    }

    //업데이트 결과
    public static HouseResponseDto.UpdateHouseResult toHouseUpdateResult(House house) {
        return HouseResponseDto.UpdateHouseResult.builder()
                .houseId(house.getHouseId()).build();
    }

}
