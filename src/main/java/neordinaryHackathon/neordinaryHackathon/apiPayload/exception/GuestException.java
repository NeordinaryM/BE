package neordinaryHackathon.neordinaryHackathon.apiPayload.exception;

import neordinaryHackathon.neordinaryHackathon.apiPayload.code.BaseErrorCode;

public class GuestException extends GeneralException{
    public GuestException(BaseErrorCode code) {
        super(code);
    }
}
