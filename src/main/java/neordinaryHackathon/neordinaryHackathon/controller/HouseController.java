package neordinaryHackathon.neordinaryHackathon.controller;

import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.BaseResponse;
import neordinaryHackathon.neordinaryHackathon.converter.HouseConverter;
import neordinaryHackathon.neordinaryHackathon.domain.House;
import neordinaryHackathon.neordinaryHackathon.dto.house.HouseDto;
import neordinaryHackathon.neordinaryHackathon.dto.house.HouseRequestDto;
import neordinaryHackathon.neordinaryHackathon.dto.house.HouseResponseDto;
import neordinaryHackathon.neordinaryHackathon.service.HouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class HouseController {
    private final HouseService houseService;

    @GetMapping("/houses")
    public BaseResponse<HouseResponseDto.getHousesResult> getHouses(@RequestParam("nickname") String nickname) {
        List<House> houses = houseService.getHouses(nickname);
        List<HouseDto> houseDto = HouseConverter.toHouseDto(houses);
        return BaseResponse.onSuccess(HouseConverter.toGetHousesResult(houseDto));
    }

    @DeleteMapping("/houses")
    public BaseResponse<Void> deleteHouses(@RequestParam("houseId") Long houseId) {
        houseService.deleteHouse(houseId);
        return BaseResponse.onSuccess(null);
    }
}
