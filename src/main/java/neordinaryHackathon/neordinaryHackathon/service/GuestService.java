package neordinaryHackathon.neordinaryHackathon.service;


import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.code.status.ErrorStatus;
import neordinaryHackathon.neordinaryHackathon.apiPayload.exception.GuestException;
import neordinaryHackathon.neordinaryHackathon.apiPayload.exception.HouseHandler;
import neordinaryHackathon.neordinaryHackathon.domain.Guest;
import neordinaryHackathon.neordinaryHackathon.domain.House;
import neordinaryHackathon.neordinaryHackathon.domain.Room;
import neordinaryHackathon.neordinaryHackathon.dto.GuestRequestDTO;
import neordinaryHackathon.neordinaryHackathon.dto.GuestResponseDTO;
import neordinaryHackathon.neordinaryHackathon.repository.GuestRepository;
import neordinaryHackathon.neordinaryHackathon.repository.HouseRepository;
import neordinaryHackathon.neordinaryHackathon.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final HouseRepository houseRepository;

    @Transactional
    public Guest joinRoom(GuestRequestDTO.CreateGuestDTO guestRequestDTO) {
        House house = houseRepository.findById(guestRequestDTO.getHouseId()).orElseThrow(() -> new HouseHandler(ErrorStatus.HOUSE_NOT_FOUND));

        Optional<Guest> newGuest = house.getRoomList().stream()
                .flatMap(room -> room.getGuestList().stream()) // 모든 guest를 단일 스트림으로 병합
                .filter(guest -> guest.getName().equals(guestRequestDTO.getGuestName())) // 조건 필터
                .findFirst();

        if (newGuest.isPresent()) {

            return newGuest.get();
        } else {
            Random random = new Random();
            int randomNumber = random.nextInt(5) + 1;

            Room randomRoom = house.getRoomList().stream()
                    .filter(room -> room.getOpenDate().equals(randomNumber)) // 조건에 맞는 Room 필터링
                    .findFirst() // 첫 번째 Room 가져오기
                    .orElse(null); // 조건에 맞는 Room이 없을 경우 null 반환

            if (randomRoom == null) {
                throw new HouseHandler(ErrorStatus.HOUSE_INVALID);
            }

            return guestRepository.save(Guest.builder().name(guestRequestDTO.getGuestName()).room(randomRoom).build());
        }
    }
}
