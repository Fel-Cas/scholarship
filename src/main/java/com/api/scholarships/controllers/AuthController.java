package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.dtos.AuthDTO;
import com.api.scholarships.dtos.AuthResponseDTO;
import com.api.scholarships.entities.User;
import com.api.scholarships.entities.UserDetailsImp;
import com.api.scholarships.mappers.UserMapper;
import com.api.scholarships.security.JwtService;
import com.api.scholarships.services.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.AUTH)
@RequiredArgsConstructor
public class AuthController {
  public final UserService userService;
  public final JwtService jwtService;
  public final UserMapper userMapper;
  public final AuthenticationManager authenticationManager;
  @PostMapping(Endpoints.LOGIN)
  public ResponseEntity<AuthResponseDTO> authenticate(@Valid @RequestBody AuthDTO request){
    Authentication authentication=authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    UserDetails userDetails = (UserDetailsImp) authentication.getPrincipal();
    String jwt = jwtService.generateToken(userDetails);
    User userFound=userService.getByEmail(request.getEmail());
    return ResponseEntity.ok(new AuthResponseDTO(userMapper.userToUserDTOResponse(userFound),jwt));
  }
}
