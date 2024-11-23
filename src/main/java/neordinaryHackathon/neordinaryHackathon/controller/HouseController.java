package neordinaryHackathon.neordinaryHackathon.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.BaseResponse;
import neordinaryHackathon.neordinaryHackathon.converter.HouseConverter;
import neordinaryHackathon.neordinaryHackathon.domain.House;
import neordinaryHackathon.neordinaryHackathon.dto.HouseDto;
import neordinaryHackathon.neordinaryHackathon.dto.HouseRequestDto;
import neordinaryHackathon.neordinaryHackathon.dto.HouseResponseDto;
import neordinaryHackathon.neordinaryHackathon.service.HouseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin("*")
@Validated
public class HouseController {
    private final HouseService houseService;

    @GetMapping("/houses")
    public BaseResponse<HouseResponseDto.getHousesResult> getHouses(@RequestParam("nickname") @Size(min = 1 ,max = 50, message = "소유자 이름은 255자를 초과할 수 없습니다.") String nickname) {
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
    public BaseResponse<HouseResponseDto.GetHouseResult> getHouse(@PathVariable(name = "houseId") Long houseId) {
        House house = houseService.getHouse(houseId);
        return BaseResponse.onSuccess(HouseConverter.toHouseDto(house));
    }

    @PatchMapping("/houses")
    public BaseResponse<HouseResponseDto.UpdateHouseResult> updateHouse(@RequestBody @Valid HouseRequestDto.UpdateHouse request) {
        House house = houseService.updateHouse(request);
        return BaseResponse.onSuccess(HouseConverter.toHouseUpdateResult(house));
    }
}
