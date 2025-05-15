package dmw.auth.JwtAuth.signin.service;


import dmw.auth.JwtAuth.signin.dao.SignUpRequest;
import dmw.auth.JwtAuth.signin.dao.SigninRequest;
import dmw.auth.JwtAuth.signin.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
