package neordinaryHackathon.neordinaryHackathon.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

    public LetterResponseDTO.letterDto createLetter(String nickname, LetterRequestDTO.letterDto letterDTO) {
        // Guest 찾기
        Guest guest = guestRepository.findByName(nickname)
                .orElseThrow(() -> new IllegalArgumentException("해당 게스트를 찾을 수 없습니다."));

        Letter letter = LetterConverter.toLetter(guest, letterDTO);

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
