package neordinaryHackathon.neordinaryHackathon.service;

import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.code.status.ErrorStatus;
import neordinaryHackathon.neordinaryHackathon.apiPayload.exception.HouseHandler;
import neordinaryHackathon.neordinaryHackathon.converter.HouseConverter;
import neordinaryHackathon.neordinaryHackathon.domain.Guest;
import neordinaryHackathon.neordinaryHackathon.domain.House;
import neordinaryHackathon.neordinaryHackathon.domain.Member;
import neordinaryHackathon.neordinaryHackathon.domain.Room;
import neordinaryHackathon.neordinaryHackathon.dto.HouseRequestDto;
import neordinaryHackathon.neordinaryHackathon.dto.HouseResponseDto;
import neordinaryHackathon.neordinaryHackathon.repository.GuestRepository;
import neordinaryHackathon.neordinaryHackathon.repository.HouseRepository;
import neordinaryHackathon.neordinaryHackathon.repository.MemberRepository;
import neordinaryHackathon.neordinaryHackathon.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;

    @Transactional(readOnly = true)
    public List<House> getHouses(String nickname) {
        //request로 멤버 조회
        Member member = memberRepository.findByName(nickname).orElseThrow(() -> new HouseHandler(ErrorStatus.MEMBER_NOT_FOUND));
        List<House> houses = houseRepository.findByMember(member);
        return houses;
    }

    @Transactional
    public House createHouse(HouseRequestDto.CreateHouse createHouse) {

        Member member = memberRepository.findByName(createHouse.getOwnerName()).orElseThrow(() -> new HouseHandler(ErrorStatus.MEMBER_NOT_FOUND));

        House house = houseRepository.save(HouseConverter.toHouse(createHouse, member));

        for (int i = 0 ; i < 5; i ++) {
            Room room =  Room.builder().openDate(i + 1).house(house).build();
            roomRepository.save(room);

            if (i == 0) {
                Guest guest = Guest.builder().name(member.getName()).room(room).build();
                guestRepository.save(guest);
            }
        }

        return house;
    }
    //하우스 단 건 조회
    @Transactional(readOnly = true)
    public House getHouse(Long houseId) {
        House house = houseRepository.findById(houseId).orElseThrow(() -> new HouseHandler(ErrorStatus.HOUSE_NOT_FOUND));
        return house;

    }



    public void deleteHouse(Long houseId) {
        //하우스 조회 -> 없으면 에러
        House house = houseRepository.findById(houseId).orElseThrow(() -> new HouseHandler(ErrorStatus.HOUSE_NOT_FOUND));
        //하우스 삭제
        houseRepository.delete(house);

    }

    public House updateHouse(HouseRequestDto.UpdateHouse request) {
        House house = houseRepository.findById(request.getHouseId()).orElseThrow(() -> new HouseHandler(ErrorStatus.HOUSE_NOT_FOUND));
        house.updateHouse(request.getName(), request.getLocation(), request.getDate());
        return house;
    }

    @Transactional(readOnly = true)
    public HouseResponseDto.GetHouseResultWithRoomList getHouseWithRoomList(Long houseId) {
        House house = houseRepository.findById(houseId).orElseThrow(() -> new HouseHandler(ErrorStatus.HOUSE_NOT_FOUND));
        List<HouseResponseDto.RoomInfo> list = house.getRoomList().stream().map((room) -> {
            return HouseResponseDto.RoomInfo.builder()
                    .roomId(room.getRoomId())
                    .openDate(room.getOpenDate())
                    .build();
        }).toList();

        return HouseResponseDto.GetHouseResultWithRoomList.builder()
                .houseId(house.getHouseId())
                .date(house.getDate())
                .location(house.getLocation())
                .roomInfoList(list)
                .build();
    }
}
