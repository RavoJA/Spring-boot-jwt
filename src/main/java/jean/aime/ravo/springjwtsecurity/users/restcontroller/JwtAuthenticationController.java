package jean.aime.ravo.springjwtsecurity.users.restcontroller;

import jean.aime.ravo.springjwtsecurity.users.exceptions.erreur.ResourceNotFoundException;
import jean.aime.ravo.springjwtsecurity.users.security.JwtLoginData;
import jean.aime.ravo.springjwtsecurity.users.security.JwtTokenUtil;
import jean.aime.ravo.springjwtsecurity.users.security.JwtUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
public class JwtAuthenticationController {


    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<Map<String, String>> createAuthenticationToken(@RequestBody JwtLoginData request) throws ResourceNotFoundException {
        authenticate(request.getUsername(), request.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getUsername());

        return ResponseEntity.ok(Collections.singletonMap("Token", jwtTokenUtil.generateToken(userDetails)));
    }

    private void authenticate(String username, String password) throws ResourceNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new ResourceNotFoundException("INVALID_CREDENTIALS", e);
        }
    }
}
