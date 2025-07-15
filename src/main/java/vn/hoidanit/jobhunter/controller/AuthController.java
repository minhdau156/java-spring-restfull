package vn.hoidanit.jobhunter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.dto.LoginDTO;
import vn.hoidanit.jobhunter.domain.dto.ResLoginDTO;
import vn.hoidanit.jobhunter.util.SecurityUtil;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;
    public AuthController (AuthenticationManagerBuilder authenticationManagerBuilder, SecurityUtil securityUtil) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
    }
    @PostMapping("/login")
    public ResponseEntity<ResLoginDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        
        //Nap input username va password vao Security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        
        //Xac thuc nguoi dung => can viet ham LoadByUsername
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        String access_token = this.securityUtil.createToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ResLoginDTO res = new ResLoginDTO();
        res.setAccessToken(access_token);
        return ResponseEntity.ok().body(res);
    }
}
