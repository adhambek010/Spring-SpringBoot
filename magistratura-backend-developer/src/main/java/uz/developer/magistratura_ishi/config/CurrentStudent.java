package uz.developer.magistratura_ishi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.entity.StudentEntity;
import uz.developer.magistratura_ishi.entity.TeacherEntity;
import uz.developer.magistratura_ishi.service.ApplicationUserDetailsService;

@Service
public class CurrentStudent {

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;


    public StudentEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            String username = (String) authentication.getPrincipal();
            UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(username);
            return (StudentEntity) userDetails;
        }
        return null;
    }
}
