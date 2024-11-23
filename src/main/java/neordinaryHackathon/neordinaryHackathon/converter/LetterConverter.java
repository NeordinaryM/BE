package neordinaryHackathon.neordinaryHackathon.converter;

import neordinaryHackathon.neordinaryHackathon.domain.Guest;
import neordinaryHackathon.neordinaryHackathon.domain.Letter;
import neordinaryHackathon.neordinaryHackathon.dto.LetterRequestDTO;

public class LetterConverter {
    public static Letter toLetter(Guest guest, LetterRequestDTO.letterDto letterDto){
        return Letter.builder()
                .writer(letterDto.getWriter())
                .content(letterDto.getContent())
                .guest(guest)
                .build();
    }
}
