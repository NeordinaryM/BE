package neordinaryHackathon.neordinaryHackathon.apiPayload.exception;

import neordinaryHackathon.neordinaryHackathon.apiPayload.code.BaseErrorCode;

public class HouseHandler extends GeneralException {
    public HouseHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
