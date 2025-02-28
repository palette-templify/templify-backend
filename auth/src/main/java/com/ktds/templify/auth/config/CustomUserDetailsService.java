package com.ktds.templify.auth.config;


import com.ktds.templify.auth.entity.User;
import com.ktds.templify.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final AuthRepository authRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User user = authRepository.findByEmail(email)
                  .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

      log.debug("user logging in: {}", user);

      return org.springframework.security.core.userdetails.User.builder()
              .username(String.valueOf(user.getId()))
              .password(user.getPassword())
              .roles(String.valueOf(user.getRoles().getFirst()))
              .build();
  }
}
