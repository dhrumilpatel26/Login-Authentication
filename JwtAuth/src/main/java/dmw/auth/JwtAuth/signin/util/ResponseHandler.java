package dmw.auth.JwtAuth.signin.util;

import org.springframework.http.ResponseEntity;
import dmw.auth.JwtAuth.signin.exception.SystemException;

import static dmw.auth.JwtAuth.signin.exception.SystemMessageEnum.Message.APP_FAILED;
import static dmw.auth.JwtAuth.signin.exception.SystemMessageEnum.Message.APP_SUCCESS;


public class ResponseHandler {
    public static ResponseEntity success(Object data, String message){
        return ResponseEntity.ok(new Response(data, APP_SUCCESS.error_code, APP_SUCCESS.message + message, EntityEnum.Status.success));
    }
    public static ResponseEntity error(Exception exception)
    {
        System.out.println("Exception occurred" + exception);
        if(exception != null && exception instanceof SystemException){
            SystemException systemException=(SystemException) exception;

            if (systemException.getAppException() != null &&    //May be Custom message from APIs or Services
                    systemException.getAppException().getMessage() != null &&
                    systemException.getAppException().getMessage().length() < 50) {
                return ResponseEntity.ok(new Response(null, systemException.getAppMessage().error_code,
                        systemException.getAppException().getMessage(), EntityEnum.Status.error));
            }
            else if (systemException.getAppMessage() != null) { //Some APIs or Services validation failed
                try {
                    if (systemException.getAppFieldName() != null && systemException.getAppMessage().message.indexOf("%s") > -1) {
                        return ResponseEntity.ok(new Response(null, systemException.getAppMessage().error_code,
                                String.format(systemException.getAppMessage().message, systemException.getAppFieldName()),
                                EntityEnum.Status.error));
                    } else {
                        return ResponseEntity.ok(new Response(null, systemException.getAppMessage().error_code,
                                systemException.getAppMessage().message, EntityEnum.Status.error));
                    }
                } catch (Exception e) {
                    System.out.println("Error creating Error Response" + e);
                }
            }
            else {
                System.out.println("Error System Exception Message is null" + systemException);
            }
        }
        return ResponseEntity.ok(new Response(null, APP_FAILED.error_code, APP_FAILED.message, EntityEnum.Status.error));
    }
}
