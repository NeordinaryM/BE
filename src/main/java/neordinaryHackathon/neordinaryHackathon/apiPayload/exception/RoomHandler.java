package neordinaryHackathon.neordinaryHackathon.apiPayload.exception;

import neordinaryHackathon.neordinaryHackathon.apiPayload.code.BaseErrorCode;

public class RoomHandler extends GeneralException{
    public RoomHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}