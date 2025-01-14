package neordinaryHackathon.neordinaryHackathon.apiPayload.code.status;


import lombok.AllArgsConstructor;
import lombok.Getter;
import neordinaryHackathon.neordinaryHackathon.apiPayload.code.BaseErrorCode;
import neordinaryHackathon.neordinaryHackathon.apiPayload.code.ErrorReasonDTO;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    // 기본 에러
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 공통 에러
    PAGE_UNDER_ZERO(HttpStatus.BAD_REQUEST, "COMM_001", "페이지는 0이상이어야 합니다."),
    INVALID_SORT_CONDITION(HttpStatus.BAD_REQUEST, "COMM_002", "유효하지 않은 정렬 조건입니다."),

    // 멤버 에러
    NICKNAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER_001", "이미 존재하는 닉네임입니다."),
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER_002", "존재하지 않는 방장입니다."),

    //편지 에러
    LETTER_NOT_FOUND(HttpStatus.BAD_REQUEST, "LETTER_001", "존재하지 않는 편지입니다."),

    //방 에러
    ROOM_NOT_FOUND(HttpStatus.BAD_REQUEST, "ROOM_))!", "존재하지 않는 방입니다."),
    ROOM_CANNOT_BE_ACCESSED(HttpStatus.BAD_REQUEST, "ROOM_002", "아직 접근할 수 없는 방입니다."),

    // 하우스 에러
    HOUSE_NOT_FOUND(HttpStatus.NOT_FOUND, "HOUSE_001", "존재하지 않는 하우스입니다."),
    HOUSE_INVALID(HttpStatus.BAD_REQUEST, "HOUSE_002", "이상한 하우스입니다."),

    // 게스트 에러
    GUEST_ALREADY(HttpStatus.NOT_FOUND, "GUEST_001", "이미 존재하는 게스트입니다."),
    GUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "GUEST_002", "존재하지 않는 게스트입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder().message(message).code(code).isSuccess(false).build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
