package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.JWTTokenData;
import med.voll.api.dto.UserAuthenticationData;
import med.voll.api.models.Users;
import med.voll.api.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid UserAuthenticationData data){
        System.out.println(data);
        Authentication authToken=new UsernamePasswordAuthenticationToken(data.userName(),data.password());
        var authenticatedUser=authenticationManager.authenticate(authToken);
        var JWTToken=tokenService.generateToken((Users) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(JWTToken));
    }
}
