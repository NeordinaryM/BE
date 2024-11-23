package neordinaryHackathon.neordinaryHackathon.controller;

import jakarta.websocket.server.PathParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.BaseResponse;
import neordinaryHackathon.neordinaryHackathon.converter.HouseConverter;
import neordinaryHackathon.neordinaryHackathon.domain.House;
import neordinaryHackathon.neordinaryHackathon.dto.house.HouseDto;
import neordinaryHackathon.neordinaryHackathon.dto.house.HouseRequestDto;
import neordinaryHackathon.neordinaryHackathon.dto.house.HouseResponseDto;
import neordinaryHackathon.neordinaryHackathon.service.HouseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
public class HouseController {
    private final HouseService houseService;

    @GetMapping("/houses")
    public BaseResponse<HouseResponseDto.getHousesResult> getHouses(@RequestParam("nickname") String nickname) {
        List<House> houses = houseService.getHouses(nickname);
        List<HouseDto> houseDto = HouseConverter.toHouseDto(houses);
        return BaseResponse.onSuccess(HouseConverter.toGetHousesResult(houseDto));
    }

    @DeleteMapping("/houses/{houseId}")
    public BaseResponse<Void> deleteHouses(@PathVariable(name = "houseId") Long houseId) {
        houseService.deleteHouse(houseId);
        return BaseResponse.onSuccess(null);
    }

    @PostMapping("/houses")
    public BaseResponse<HouseResponseDto.CreateHouseResult> createHouse(@RequestBody @Valid HouseRequestDto.CreateHouse createHouse) {
        House house = houseService.createHouse(createHouse);
        return BaseResponse.onSuccess(HouseConverter.toCreateHouseResult(house));
    }
    @GetMapping("/houses/{houseId}")
    public BaseResponse<HouseResponseDto.getHouseResult> getHouse(@PathVariable(name = "houseId") Long houseId) {
        House house = houseService.getHouse(houseId);
        return BaseResponse.onSuccess(HouseConverter.toHouseDto(house));
    }

}
