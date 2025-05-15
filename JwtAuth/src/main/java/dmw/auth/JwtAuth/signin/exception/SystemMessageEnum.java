package dmw.auth.JwtAuth.signin.exception;

public class SystemMessageEnum {
    public enum Message {

        UNAUTHORIZATION_ERROR_MESSAGE ("UNAUTH01","You do not have the necessary authorities to access this endpoint"),
        APP_SUCCESS ("APP00", "Success - "),
        APP_FAILED ("APP01", "Unknown Error"),
        INVALID_SIGN_UP ("FLD03", "Error in sign-up,please try again"),
        INVALID_SIGN_IN ("FLD04", "Error in sign-in,please try again");

        public final String error_code;
        public final String message;

        Message(String error_code, String message) {
            this.error_code = error_code;
            this.message = message;
        }
    }
}
