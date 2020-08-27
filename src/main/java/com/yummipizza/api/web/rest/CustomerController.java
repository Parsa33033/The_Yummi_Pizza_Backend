package com.yummipizza.api.web.rest;

import com.yummipizza.api.domain.CustomerMessage;
import com.yummipizza.api.domain.Pizzaria;
import com.yummipizza.api.domain.User;
import com.yummipizza.api.repository.AddressRepository;
import com.yummipizza.api.repository.PizzariaRepository;
import com.yummipizza.api.repository.UserRepository;
import com.yummipizza.api.service.*;
import com.yummipizza.api.service.dto.*;
import com.yummipizza.api.service.mapper.AddressMapper;
import com.yummipizza.api.service.mapper.CustomerMessageMapper;
import com.yummipizza.api.service.mapper.PizzariaMapper;
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

    private final PizzariaRepository pizzariaRepository;
    private final PizzariaMapper pizzariaMapper;

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    private final CustomerMessageService customerMessageService;

    private final MenuItemService menuItemService;

    @Autowired
    UserJWTController userJWTController;

    public CustomerController(UserRepository userRepository, UserService userService, MailService mailService,
                              PizzariaRepository pizzariaRepository, PizzariaMapper pizzariaMapper,
                              AddressRepository addressRepository, AddressMapper addressMapper,
                              CustomerMessageService customerMessageService, MenuItemService menuItemService) {

        this.userRepository = userRepository;
        this.userService = userService;
        this.mailService = mailService;
        this.pizzariaRepository = pizzariaRepository;
        this.pizzariaMapper = pizzariaMapper;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.customerMessageService = customerMessageService;
        this.menuItemService = menuItemService;
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
    public ResponseEntity<DummyPizzariaDTO> getAllPizzarias() {
        log.debug("REST request to get all Pizzarias");
        Pizzaria pizzaria = pizzariaRepository.findAll().get(0);
        DummyPizzariaDTO dummyPizzariaDTO = new DummyPizzariaDTO(pizzariaMapper.toDto(pizzaria));
        DummyAddressDTO dummyAddressDTO = new DummyAddressDTO(addressMapper.toDto(pizzaria.getAddress()));
        dummyPizzariaDTO.setAddress(dummyAddressDTO);
        return ResponseEntity.ok(dummyPizzariaDTO);
    }

    @PostMapping("/customer-message")
    public void sendCustomerMessage(@RequestBody CustomerMessageDTO customerMessageDTO) {
        customerMessageService.save(customerMessageDTO);
        CustomerMessage message = new CustomerMessage();
        message.setEmail(customerMessageDTO.getEmail());
        message.setMessage(customerMessageDTO.getMessage());
        message.setName(customerMessageDTO.getName());
        message.setSubject(customerMessageDTO.getSubject());
        mailService.sendCustomerMessageEmailFromTemplate(message,  "mail/customerMessage", "email.customer.message.subject");
    }


    /**
     * {@code GET  /menu-items} : get all the menuItems.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of menuItems in body.
     */
    @GetMapping("/menu-items")
    public List<MenuItemDTO> getAllMenuItems(@RequestParam(required = false) String filter) {
        if ("orderitem-is-null".equals(filter)) {
            log.debug("REST request to get all MenuItems where orderItem is null");
            return menuItemService.findAllWhereOrderItemIsNull();
        }
        log.debug("REST request to get all MenuItems");
        return menuItemService.findAll();
    }

    private static boolean checkPasswordLength(String password) {
        return !StringUtils.isEmpty(password) &&
            password.length() >= ManagedUserVM.PASSWORD_MIN_LENGTH &&
            password.length() <= ManagedUserVM.PASSWORD_MAX_LENGTH;
    }
}
