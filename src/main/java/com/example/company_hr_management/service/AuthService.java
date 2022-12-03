package com.example.company_hr_management.service;

import com.example.company_hr_management.security.jwt.JwtProvider;
import com.example.company_hr_management.entity.User;
import com.example.company_hr_management.entity.enums.RoleNames;
import com.example.company_hr_management.payload.ApiResponse;
import com.example.company_hr_management.payload.LoginDto;
import com.example.company_hr_management.payload.RegesterDto;
import com.example.company_hr_management.repository.RoleRepository;
import com.example.company_hr_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
            @Lazy
    PasswordEncoder passwordEncoder;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    public ApiResponse regesterUser(RegesterDto regesterDto){
         if (userRepository.existsByEmail(regesterDto.getUsername())){
            return new ApiResponse(regesterDto.getUsername()+" pochta orqali roy'hatga olingan",false);
        }
        User user=new User();
        user.setFirstName(regesterDto.getFirstName());
        user.setLastName(regesterDto.getLastName());
        user.setEmail(regesterDto.getUsername());
        user.setPassword(passwordEncoder.encode(regesterDto.getPassword()));
        //collectionga o'rab beryabmiz
        user.setRoles(Collections.singleton(roleRepository.findByRoleName(RoleNames.DERECTOR)));
        user.setEmailCode(UUID.randomUUID().toString());
        userRepository.save(user);
        sendEmail(user.getEmail(), user.getEmailCode());

        if (!sendEmail(user.getEmail(), user.getEmailCode()))
            return new ApiResponse("xabar yuborilmadi",false);


        return new ApiResponse(
                "Pochtangizga xabar yubordik haqiqatdan siz ekanligingizni bilishimiz uchun xabarni tasdiqlang",
                true);
    }

    public   boolean sendEmail(String sendingEmail,String emailCode){
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("sandjarbeek@gmail.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.getReplyTo();
            mailMessage.setSubject("Akkountni tasdiqlash");
            mailMessage.setText("http://localhost:8080/api/auth/verifyEmail?mailCode=" + emailCode + "&email=" + sendingEmail);
            javaMailSender.send(mailMessage);

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public ApiResponse verifyEmail(String mailCode, String email) {
        Optional<User> optionalUser = userRepository.findByEmailCodeAndEmail(mailCode, email);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setEnabled(true);
            user.setEmailCode(null);
            userRepository.save(user);
            return new ApiResponse("Accountingiz tasdiqlandi tizimga kirishingiz mumkin",true);
        }
        return new ApiResponse("Akkontingiz avval tasdiqlangan!",false);
    }
    public ApiResponse login(LoginDto loginDto){
        try {

            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            ));
            User user = (User) authenticate.getPrincipal();

            String token = jwtProvider.generateToken(loginDto.getUsername(), user.getRoles());
            return new ApiResponse(token,true);
        }catch (BadCredentialsException badCredentialsException){
            return new ApiResponse("Parol yoki login xato",false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent())
            return optionalUser.get();
        throw new UsernameNotFoundException(username+" user topilmadi");
    }
}
