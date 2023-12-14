package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .map(user -> new User(user.getEmail(),
                        user.getPassword(),
                        getAuthorities(user.getRole()))
                ).orElseThrow(() -> new UsernameNotFoundException(String.format("%s - not found", username)));
    }

    public List<GrantedAuthority> getAuthorities(Role role) {
        List<GrantedAuthority> authList = new ArrayList<>();
        if (role.toString().equals("USER")) {
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        } else if (role.toString().equals("ADMIN")) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        System.out.println(authList);
        return authList;
    }

}
