package homework.lesson55.service;

import homework.lesson55.dao.UserDao;
import homework.lesson55.dto.RegisterRequestDto;
import homework.lesson55.exception.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequestDto dto) {
        if (userDao.existsByUsername(dto.getUsername())) {
            throw new BadRequestException("username already exists");
        }

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        userDao.createUser(dto.getUsername(), encodedPassword, true);
        userDao.addAuthority(dto.getUsername(), "ROLE_USER");
    }
}