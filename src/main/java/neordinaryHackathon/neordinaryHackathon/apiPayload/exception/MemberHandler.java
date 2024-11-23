package neordinaryHackathon.neordinaryHackathon.apiPayload.exception;

import neordinaryHackathon.neordinaryHackathon.apiPayload.code.BaseErrorCode;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}