package com.yummipizza.api.web.rest;

import com.yummipizza.api.domain.User;
import com.yummipizza.api.repository.UserRepository;
import com.yummipizza.api.service.MailService;
import com.yummipizza.api.service.PizzariaService;
import com.yummipizza.api.service.UserService;
import com.yummipizza.api.service.dto.PizzariaDTO;
import com.yummipizza.api.service.dto.UserDTO;
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
import java.util.List;

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

    private final PizzariaService pizzariaService;

    @Autowired
    UserJWTController userJWTController;

    public CustomerController(UserRepository userRepository, UserService userService, MailService mailService,
                              PizzariaService pizzariaService) {

        this.userRepository = userRepository;
        this.userService = userService;
        this.mailService = mailService;
        this.pizzariaService = pizzariaService;
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

    /**
     * {@code GET  /account} : get the current user.
     *
     * @return the current user.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
     */
    @GetMapping("/account")
    public UserDTO getAccount() {
        return userService.getUserWithAuthorities()
            .map(UserDTO::new)
            .orElseThrow(() -> new AccountResourceException("User could not be found"));
    }

    /**
     * {@code GET  /pizzarias} : get all the pizzarias.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pizzarias in body.
     */
    @GetMapping("/pizzarias")
    public List<PizzariaDTO> getAllPizzarias() {
        log.debug("REST request to get all Pizzarias");
        return pizzariaService.findAll();
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }
}
