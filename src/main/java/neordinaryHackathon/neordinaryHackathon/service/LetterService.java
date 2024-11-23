package neordinaryHackathon.neordinaryHackathon.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.apiPayload.exception.GuestException;
import neordinaryHackathon.neordinaryHackathon.apiPayload.exception.HouseHandler;
import neordinaryHackathon.neordinaryHackathon.apiPayload.exception.LetterHandler;
import neordinaryHackathon.neordinaryHackathon.apiPayload.exception.RoomHandler;
import neordinaryHackathon.neordinaryHackathon.converter.LetterConverter;
import neordinaryHackathon.neordinaryHackathon.domain.Guest;
import neordinaryHackathon.neordinaryHackathon.domain.House;
import neordinaryHackathon.neordinaryHackathon.domain.Letter;
import neordinaryHackathon.neordinaryHackathon.domain.Room;
import neordinaryHackathon.neordinaryHackathon.dto.LetterRequestDTO;
import neordinaryHackathon.neordinaryHackathon.dto.LetterResponseDTO;
import neordinaryHackathon.neordinaryHackathon.repository.GuestRepository;
import neordinaryHackathon.neordinaryHackathon.repository.HouseRepository;
import neordinaryHackathon.neordinaryHackathon.repository.LetterRepository;
import neordinaryHackathon.neordinaryHackathon.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static neordinaryHackathon.neordinaryHackathon.apiPayload.code.status.ErrorStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final HouseRepository houseRepository;

    public LetterResponseDTO.letterDto createLetter(LetterRequestDTO.letterDto letterDTO) {
        House house = houseRepository.findById(letterDTO.getHouseId())
                .orElseThrow(() -> new HouseHandler(HOUSE_NOT_FOUND));

        boolean writerExists = house.getRoomList().stream()
                .flatMap(room -> room.getGuestList().stream())
                .anyMatch(guest -> guest.getName().equals(letterDTO.getWriter()));

        if (!writerExists) {
            throw new GuestException(GUEST_NOT_FOUND);
        }

        boolean receiverExists = house.getRoomList().stream()
                .flatMap(room -> room.getGuestList().stream())
                .anyMatch(guest -> guest.getName().equals(letterDTO.getReceiver()));

        if (!receiverExists) {
            throw new GuestException(GUEST_NOT_FOUND);
        }

        Guest newGuest = house.getRoomList().stream()
                .flatMap(room -> room.getGuestList().stream()) // 모든 Guest 스트림으로 평탄화
                .filter(guest -> guest.getName().equals(letterDTO.getReceiver())) // receiver 이름과 일치하는 Guest 필터링
                .findFirst() // 첫 번째 매칭된 Guest 반환
                .orElseThrow(() -> new GuestException(GUEST_NOT_FOUND)); // 없으면 예외 발생


        Letter letter = LetterConverter.toLetter(newGuest, letterDTO);

        // Letter 저장
        letterRepository.save(letter);
        return LetterResponseDTO.letterDto.builder()
                .letterId(letter.getLetterId())
                .createdAt(letter.getCreatedAt())
                .build();
    }

    public List<LetterResponseDTO.letterListDto> letterList(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomHandler(ROOM_NOT_FOUND));

        House house = room.getHouse();

        LocalDate targetDate = house.getDate().minusDays(room.getOpenDate());
        if (LocalDate.now().isBefore(targetDate)) {
            throw new RoomHandler(ROOM_CANNOT_BE_ACCESSED);
        }

        List<Guest> guests = guestRepository.findByRoomRoomId(room.getRoomId());

        return guests.stream()
                .map(LetterConverter::toLetterListDto)
                .collect(Collectors.toList());
    }

    public LetterResponseDTO.letterDetailDto letterDetail(Long letterId) {
        // Letter 엔티티 조회
        Letter letter = letterRepository.findById(letterId)
                .orElseThrow(() -> new LetterHandler(LETTER_NOT_FOUND));

        // Letter 데이터를 DTO로 변환
        return LetterResponseDTO.letterDetailDto.builder()
                .letterId(letter.getLetterId())
                .content(letter.getContent())
                .writer(letter.getWriter()).build();
    }

    public void editLetter(Long letterId, String content) {
        Letter letter = letterRepository.findById(letterId)
                .orElseThrow(() -> new LetterHandler(LETTER_NOT_FOUND));
        letter.updateContent(content);
        letterRepository.save(letter);
    }

    public void deleteLetter(Long letterId) {
        Letter letter = letterRepository.findById(letterId)
                .orElseThrow(() -> new LetterHandler(LETTER_NOT_FOUND));
        letterRepository.delete(letter);
    }
}
