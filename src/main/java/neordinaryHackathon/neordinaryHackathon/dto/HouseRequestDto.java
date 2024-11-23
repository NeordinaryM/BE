package neordinaryHackathon.neordinaryHackathon.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

public class HouseRequestDto {


    @Getter
    public static class CreateHouse {
        @NotNull
        @Size(min = 1, max = 255, message = "그룹 이름은 255자를 초과할 수 없습니다.")
        private String groupName;

        @NotNull
        @Size(min = 1, max = 255, message = "위치는 255자를 초과할 수 없습니다.")
        private String location;

        @NotNull(message = "날짜는 필수 값입니다.")
        @FutureOrPresent(message = "날짜는 오늘 또는 미래여야 합니다.")
        private LocalDate date;

        @NotNull
        @Size(min = 1, max = 255, message = "소유자 이름은 255자를 초과할 수 없습니다.")
        private String ownerName;

        @NotNull
        @Min(1)
        @Max(3)
        private Integer imageNumber;

        @NotNull
        @Size(min = 1, max = 3000)
        private String content;
    }

    @Getter
    public static class UpdateHouse {
        @NotNull
        private Long houseId;

        @NotNull(message = "그룹 이름은 필수 입니다.")
        @Size(min = 1, max = 255, message = "그룹 이름은 255자를 초과할 수 없습니다.")
        private String name;


        @NotNull
        @Size(min = 1, max = 255, message = "위치는 255자를 초과할 수 없습니다.")
        private String location;

        @NotNull(message = "날짜는 필수 값입니다.")
        @FutureOrPresent(message = "날짜는 오늘 또는 미래여야 합니다.")
        private LocalDate date;

        @NotNull
        @Size(min = 1, max = 3000)
        private String content;
    }
}
