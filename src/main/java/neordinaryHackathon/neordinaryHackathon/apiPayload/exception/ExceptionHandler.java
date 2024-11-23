package neordinaryHackathon.neordinaryHackathon.apiPayload.exception;

import neordinaryHackathon.neordinaryHackathon.apiPayload.code.BaseErrorCode;

public class ExceptionHandler extends GeneralException {
    public ExceptionHandler(BaseErrorCode code) {
        super(code);
    }
}