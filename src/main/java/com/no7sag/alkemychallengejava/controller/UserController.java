package com.no7sag.alkemychallengejava.controller;

import com.no7sag.alkemychallengejava.entity.User;
import com.no7sag.alkemychallengejava.repository.UserRepository;
import com.no7sag.alkemychallengejava.security.jwt.JwtTokenUtil;
import com.no7sag.alkemychallengejava.security.payload.JwtResponse;
import com.no7sag.alkemychallengejava.security.payload.LoginRequest;
import com.no7sag.alkemychallengejava.security.payload.MessageResponse;
import com.no7sag.alkemychallengejava.security.payload.RegisterRequest;
import com.no7sag.alkemychallengejava.service.MailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    MailService mailService;

    public UserController(AuthenticationManager authManager,
                          UserRepository userRepository,
                          PasswordEncoder encoder,
                          JwtTokenUtil jwtTokenUtil) {
        this.authManager = authManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    @ApiOperation("Iniciar sesión")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/register")
    @ApiOperation("Registrar usuario")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El usuario ya está en uso"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El email ya está en uso"));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);
        mailService.sendTextEmail(signUpRequest.getEmail());

        return ResponseEntity.ok(new MessageResponse("Se ha registrado con éxito"));
    }

}