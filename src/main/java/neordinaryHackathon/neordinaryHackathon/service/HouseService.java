package neordinaryHackathon.neordinaryHackathon.service;

import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.code.status.ErrorStatus;
import neordinaryHackathon.neordinaryHackathon.apiPayload.exception.HouseHandler;
import neordinaryHackathon.neordinaryHackathon.converter.HouseConverter;
import neordinaryHackathon.neordinaryHackathon.domain.House;
import neordinaryHackathon.neordinaryHackathon.domain.Member;
import neordinaryHackathon.neordinaryHackathon.domain.Room;
import neordinaryHackathon.neordinaryHackathon.dto.house.HouseDto;
import neordinaryHackathon.neordinaryHackathon.dto.house.HouseRequestDto;
import neordinaryHackathon.neordinaryHackathon.repository.HouseRepository;
import neordinaryHackathon.neordinaryHackathon.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HouseService {
    private final HouseRepository houseRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<House> getHouses(String nickname) {
        //request로 멤버 조회
        Member member = memberRepository.findByName(nickname).orElseThrow(() -> new HouseHandler(ErrorStatus.MEMBER_NOT_FOUND));
        List<House> houses = houseRepository.findByMember(member);
        return houses;
    }

    public House createHouse(HouseRequestDto.CreateHouse createHouse) {
        List<Room> roomList = new ArrayList<>();

        for (int i = 0 ; i < 5; i ++) {
            roomList.add(Room.builder().openDate(i + 1).build());
        }

        return houseRepository.save(HouseConverter.toHouse(createHouse, roomList));
    }
}
