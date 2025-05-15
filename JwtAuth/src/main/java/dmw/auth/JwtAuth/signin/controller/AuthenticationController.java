package dmw.auth.JwtAuth.signin.controller;



import dmw.auth.JwtAuth.signin.dao.SignUpRequest;
import dmw.auth.JwtAuth.signin.dao.SigninRequest;
import dmw.auth.JwtAuth.signin.exception.SystemException;
import dmw.auth.JwtAuth.signin.exception.SystemMessageEnum;
import dmw.auth.JwtAuth.signin.service.AuthenticationService;
import dmw.auth.JwtAuth.signin.util.Response;
import dmw.auth.JwtAuth.signin.util.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Operation(summary = "Sign-up")
    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@RequestBody SignUpRequest request) {
        try {
            return ResponseHandler.success(authenticationService.signup(request), "Sign-up");
        }catch (Exception exception){
            return ResponseHandler.error(new SystemException(exception, null, SystemMessageEnum.Message.INVALID_SIGN_UP));
        }
    }

    @Operation(summary = "Sign-in")
    @PostMapping("/signin")
    public ResponseEntity<Response> signin(@RequestBody SigninRequest request) {
        try {
            return ResponseHandler.success(authenticationService.signin(request), "Sign-in");
        }catch (Exception exception){
            return ResponseHandler.error(new SystemException(exception, null, SystemMessageEnum.Message.INVALID_SIGN_IN));
        }
    }
}
