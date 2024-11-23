package neordinaryHackathon.neordinaryHackathon.apiPayload.exception.handler;


import neordinaryHackathon.neordinaryHackathon.apiPayload.code.BaseErrorCode;
import neordinaryHackathon.neordinaryHackathon.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
