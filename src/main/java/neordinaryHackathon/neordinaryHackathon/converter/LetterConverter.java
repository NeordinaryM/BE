package neordinaryHackathon.neordinaryHackathon.converter;

import neordinaryHackathon.neordinaryHackathon.domain.Guest;
import neordinaryHackathon.neordinaryHackathon.domain.Letter;
import neordinaryHackathon.neordinaryHackathon.dto.LetterRequestDTO;
import neordinaryHackathon.neordinaryHackathon.dto.LetterResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class LetterConverter {
    public static Letter toLetter(Guest guest, LetterRequestDTO.letterDto letterDto){
        return Letter.builder()
                .writer(letterDto.getWriter())
                .content(letterDto.getContent())
                .guest(guest)
                .build();
    }

    public static LetterResponseDTO.letterDetailDto toLetterDetailDto(Letter letter) {
        return LetterResponseDTO.letterDetailDto.builder()
                .letterId(letter.getLetterId())
                .content(letter.getContent())
                .writer(letter.getWriter())
                .build();
    }

    public static LetterResponseDTO.letterListDto toLetterListDto(Guest guest) {
        List<LetterResponseDTO.letterDetailDto> letterDetails = guest.getLetterList().stream()
                .map(letter -> toLetterDetailDto(letter))
                .collect(Collectors.toList());

        return LetterResponseDTO.letterListDto.builder()
                .guest(guest.getName())
                .letters(letterDetails)
                .build();
    }
}
