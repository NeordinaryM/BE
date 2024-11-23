package neordinaryHackathon.neordinaryHackathon.dto.house;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class HouseRequestDto {


    @Getter
    public static class CreateHouse {
        private String groupName;
        private String location;
        private LocalDate date;
        private String ownerName;
    }

}
