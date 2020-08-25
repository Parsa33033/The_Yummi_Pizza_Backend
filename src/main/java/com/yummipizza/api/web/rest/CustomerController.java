package com.yummipizza.api.web.rest;

import com.yummipizza.api.domain.User;
import com.yummipizza.api.repository.UserRepository;
import com.yummipizza.api.service.MailService;
import com.yummipizza.api.service.UserService;
import com.yummipizza.api.web.rest.errors.EmailAlreadyUsedException;
import com.yummipizza.api.web.rest.errors.InvalidPasswordException;
import com.yummipizza.api.web.rest.errors.LoginAlreadyUsedException;
import com.yummipizza.api.web.rest.vm.LoginVM;
import com.yummipizza.api.web.rest.vm.ManagedUserVM;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer/web")
public class CustomerController {

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final UserRepository userRepository;

    private final UserService userService;

    private final MailService mailService;

    @Autowired
    UserJWTController userJWTController;

    public CustomerController(UserRepository userRepository, UserService userService, MailService mailService) {

        this.userRepository = userRepository;
        this.userService = userService;
        this.mailService = mailService;
    }

    /**
     * {@code POST  /register} : register the user.
     *
     * @param managedUserVM the managed user View Model.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is incorrect.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        if (!checkPasswordLength(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }
        User user = userService.registerUser(managedUserVM, managedUserVM.getPassword(), true);
        mailService.sendActivationEmail(user);

//        LoginVM loginVM = new LoginVM();
//        loginVM.setUsername(user.getLogin());
//        loginVM.setPassword(managedUserVM.getPassword());
//        return userJWTController.authorize(loginVM);
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }
}
