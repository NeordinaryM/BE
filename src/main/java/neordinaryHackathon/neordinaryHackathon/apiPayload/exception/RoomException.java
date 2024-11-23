package neordinaryHackathon.neordinaryHackathon.apiPayload.exception;

import neordinaryHackathon.neordinaryHackathon.apiPayload.code.BaseErrorCode;

public class RoomException extends GeneralException {
    public RoomException(BaseErrorCode code) {
        super(code);
    }
}
