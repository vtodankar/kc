package org.vt.kc;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomUserAttrController {

    @GetMapping(path = "/users")
    public String getUserInfo(Model model) {

        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
            .getAuthentication();

        final Principal principal = (Principal) authentication.getPrincipal();

        String dob = "";
        String userIdByToken = "";
        String userIdByMapper = "";
        String userEmail = "";

        if (principal instanceof KeycloakPrincipal) {

           Optional<KeycloakPrincipal<KeycloakSecurityContext>> KeyCloakPrincipal = Optional.of((KeycloakPrincipal<KeycloakSecurityContext>) principal);
           IDToken token = KeyCloakPrincipal
        		   .map(KeycloakPrincipal::getKeycloakSecurityContext)
        		   .map(KeycloakSecurityContext::getIdToken).orElseGet(IDToken::new);
            userIdByToken = token.getSubject();
            userEmail = token.getEmail();
            dob = token.getBirthdate();
            /**userIdByMapper = token.getOtherClaims().get("user_id").toString();

            Map<String, Object> customClaims = token.getOtherClaims();

            if (customClaims.containsKey("DOB")) {
                dob = String.valueOf(customClaims.get("DOB"));
            }
            **/
        }

        model.addAttribute("username", principal.getName());
        model.addAttribute("userIDByToken", userIdByToken);
        model.addAttribute("userIDByMapper", userIdByMapper);
        model.addAttribute("dob", dob);
        model.addAttribute("userEmail", userEmail);

        return "userInfo";
    }

}
