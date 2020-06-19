package ca.debateon.records.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

@CrossOrigin
@RestController
public class AuthenticationController {

    @Resource
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Credentials createAuthentication(@Valid @RequestBody Credentials credentials) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new Credentials(credentials.getUsername(), new Date());
    }
}
