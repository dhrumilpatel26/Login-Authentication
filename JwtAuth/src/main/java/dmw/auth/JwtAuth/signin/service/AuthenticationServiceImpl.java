package dmw.auth.JwtAuth.signin.service;


import dmw.auth.JwtAuth.signin.util.EntityEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import dmw.auth.JwtAuth.signin.dao.SignUpRequest;
import dmw.auth.JwtAuth.signin.dao.SigninRequest;
import dmw.auth.JwtAuth.signin.entities.UserEntity;
import dmw.auth.JwtAuth.signin.entities.UserRepository;
import dmw.auth.JwtAuth.signin.response.JwtAuthenticationResponse;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private  final UserRepository userRepository;
    private final JwtService jwtService;
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .role(EntityEnum.Role.USER).build();
        UserEntity saveUser=userRepository.save(user);

        var jwt = jwtService.generateToken(user,new HashMap<>(){{
            put(EntityEnum.USER_ID, saveUser.getId());
        }});
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user,new HashMap<>(){{
            put(EntityEnum.USER_ID, user.getId());
        }});
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}
