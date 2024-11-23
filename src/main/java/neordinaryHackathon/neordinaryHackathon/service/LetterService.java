package neordinaryHackathon.neordinaryHackathon.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import neordinaryHackathon.neordinaryHackathon.converter.LetterConverter;
import neordinaryHackathon.neordinaryHackathon.domain.Guest;
import neordinaryHackathon.neordinaryHackathon.domain.Letter;
import neordinaryHackathon.neordinaryHackathon.dto.LetterRequestDTO;
import neordinaryHackathon.neordinaryHackathon.dto.LetterResponseDTO;
import neordinaryHackathon.neordinaryHackathon.repository.GuestRepository;
import neordinaryHackathon.neordinaryHackathon.repository.LetterRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;
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
}
