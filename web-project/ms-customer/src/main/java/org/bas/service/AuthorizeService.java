package org.bas.service;

import org.bas.entity.Account;
import org.bas.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null)
            throw new UsernameNotFoundException("用户名不能为空");
        Account account = accountRepository.getAccountByUsernameOrEmail(username);

        if (account == null)
            throw new UsernameNotFoundException("用户名或密码错误");

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles("test")
                .build();
    }
}
