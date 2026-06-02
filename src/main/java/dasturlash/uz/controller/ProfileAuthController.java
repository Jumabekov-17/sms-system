package dasturlash.uz.controller;

import dasturlash.uz.dto.profile.RequestForLoginProfile;
import dasturlash.uz.dto.profile.RequestForRegisterProfile;
import dasturlash.uz.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileAuthController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/register")
    public ResponseEntity<?> registerProfile(@RequestBody @Valid RequestForRegisterProfile request) {
        return ResponseEntity.ok(profileService.registerProfile(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid RequestForLoginProfile request) {
        return ResponseEntity.ok(profileService.loginProfile(request));
    }
}
