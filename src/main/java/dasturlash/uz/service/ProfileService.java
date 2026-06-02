package dasturlash.uz.service;

import dasturlash.uz.dto.admin.RequestForRegisterProfile;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public String registerProfile(RequestForRegisterProfile request) {
        boolean found = profileRepository.existsByLogin(request.login());
        if (found) {
            throw new IllegalArgumentException("Profile already exists");
        }
        if (request.login().length() < 5) {
            throw new IllegalArgumentException("Login length must be greater than 5");
        }
        if (request.password().length() < 5) {
            throw new IllegalArgumentException("Password length must be greater than 5");
        }
        Profile profile = new Profile();
        profile.setLogin(request.login());
        profile.setPassword(request.password());
        profile.setSurname(request.surname());
        profile.setName(request.name());
        profileRepository.save(profile);
        return "Profile registered successfully";
    }
}
