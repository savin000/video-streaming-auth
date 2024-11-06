package ru.savin.videostreamingauth.controller;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.savin.videostreamingauth.constant.ControllerConstant;
import ru.savin.videostreamingauth.entity.JwtAuthentication;
import ru.savin.videostreamingauth.entity.JwtRequest;
import ru.savin.videostreamingauth.entity.JwtResponse;
import ru.savin.videostreamingauth.entity.RefreshJwtRequest;
import ru.savin.videostreamingauth.service.AuthService;

@RestController
@RequestMapping(ControllerConstant.ROOT_PATH + "auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/info")
    public ResponseEntity<JwtAuthentication> getAuthInfo() {
        return ResponseEntity.ok(authService.getAuthInfo());
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) throws AuthException {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
}
