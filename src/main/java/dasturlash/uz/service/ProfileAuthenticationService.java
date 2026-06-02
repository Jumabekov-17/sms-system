package dasturlash.uz.service;

import dasturlash.uz.dto.profile.RequestForLoginProfile;
import dasturlash.uz.dto.profile.RequestForRegisterProfile;
import dasturlash.uz.dto.profile.ResponseDtoForProfile;
import dasturlash.uz.entity.Profile;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileAuthenticationService {
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

    public ResponseDtoForProfile loginProfile(RequestForLoginProfile request) {
        Profile profile = profileRepository.findByLoginAndPassword(request.login(), request.password())
                .orElseThrow(() -> new IllegalArgumentException("Invalid login or password"));

        ResponseDtoForProfile response = new ResponseDtoForProfile();
        response.setName(profile.getName());
        response.setSurname(profile.getSurname());
        response.setLogin(profile.getLogin());
        response.setId(profile.getId());
        return response;
    }
}
