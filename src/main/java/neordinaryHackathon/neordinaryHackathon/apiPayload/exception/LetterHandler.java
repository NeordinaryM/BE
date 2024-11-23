package neordinaryHackathon.neordinaryHackathon.apiPayload.exception;

import neordinaryHackathon.neordinaryHackathon.apiPayload.code.BaseErrorCode;

public class LetterHandler extends GeneralException{
    public LetterHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
