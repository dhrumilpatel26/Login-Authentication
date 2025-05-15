package dmw.auth.JwtAuth.signin.exception;

import lombok.Data;

@Data
public class SystemException extends Exception {
    private Exception appException;
    private String appFieldName;
    private SystemMessageEnum.Message appMessage;

    public SystemException(Exception appException, String appFieldName, SystemMessageEnum.Message appMessage) {
        super(appException);
        this.appException = appException;
        this.appFieldName = appFieldName;
        this.appMessage = appMessage;
    }

    @Override
    public String toString() {
        return "SystemException{" +
                "exception=" + appException +
                ", fieldName='" + appFieldName + '\'' +
                ", message=" + appMessage +
                '}';
    }
}
