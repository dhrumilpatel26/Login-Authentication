package dmw.auth.JwtAuth.signin.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private Object data;
    private String errorCode;
    private String message;
    private EntityEnum.Status status;

}

