package neordinaryHackathon.neordinaryHackathon.dto;

import lombok.Getter;

import java.time.LocalDate;

public class HouseRequestDto {


    @Getter
    public static class CreateHouse {
        private String groupName;
        private String location;
        private LocalDate date;
        private String ownerName;
    }

    @Getter
    public static class UpdateHouse {
        private Long houseId;
        private String name;
        private String location;
        private LocalDate date;
    }
}
