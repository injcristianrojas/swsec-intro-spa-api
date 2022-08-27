package cl.injcristianrojas.swsecintro.controller;

import cl.injcristianrojas.swsecintro.JwtTokenUtil;
import cl.injcristianrojas.swsecintro.model.AuthRequest;
import cl.injcristianrojas.swsecintro.model.AuthResponse;
import cl.injcristianrojas.swsecintro.model.User;
import cl.injcristianrojas.swsecintro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthController {

    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    @PostMapping("/api/v1/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            User user = userService.authenticateUser(request.getUsername(), request.getPassword());
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(accessToken);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
