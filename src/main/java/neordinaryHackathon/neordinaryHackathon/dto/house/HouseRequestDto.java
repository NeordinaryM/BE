package neordinaryHackathon.neordinaryHackathon.dto.house;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class HouseRequestDto {
    @Getter
    public static class getHouses {
        String nickname; // 하우스를 조회할 닉네임
    }

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
    }

}
