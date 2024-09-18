package uz.developer.magistratura_ishi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.developer.magistratura_ishi.entity.StudentEntity;
import uz.developer.magistratura_ishi.entity.TeacherEntity;
import uz.developer.magistratura_ishi.repository.StudentRepository;
import uz.developer.magistratura_ishi.repository.TeacherRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.contains("student")) {
            Optional<StudentEntity> optionalStudentEntity = studentRepository.findByUsername(username);
            if (optionalStudentEntity.isPresent()) {
                return optionalStudentEntity.get();
            }
        } else {
            Optional<TeacherEntity> optionalTeacherEntity = teacherRepository.findByUsername(username);
            if (optionalTeacherEntity.isPresent()) {
                return optionalTeacherEntity.get();
            }
        }
        throw new UsernameNotFoundException(username + " Username not found");
    }
}
