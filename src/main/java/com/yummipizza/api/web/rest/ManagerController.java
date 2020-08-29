package com.yummipizza.api.web.rest;

import com.sun.mail.iap.Response;
import com.yummipizza.api.domain.*;
import com.yummipizza.api.repository.*;
import com.yummipizza.api.security.SecurityUtils;
import com.yummipizza.api.service.*;
import com.yummipizza.api.service.dto.*;
import com.yummipizza.api.service.mapper.*;
import com.yummipizza.api.web.rest.errors.EmailAlreadyUsedException;
import com.yummipizza.api.web.rest.errors.InvalidPasswordException;
import com.yummipizza.api.web.rest.errors.LoginAlreadyUsedException;
import com.yummipizza.api.web.rest.vm.LoginVM;
import com.yummipizza.api.web.rest.vm.ManagedUserVM;
import io.github.jhipster.web.util.ResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manager/web")
public class ManagerController {

    private static class AccountResourceException extends RuntimeException {
        private AccountResourceException(String message) {
            super(message);
        }
    }

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final ManagerService managerService;
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    private final OrderItemMapper orderItemMapper;

    private final MenuItemService menuItemService;

    private final AddressMapper addressMapper;


    public ManagerController(ManagerService managerService, ManagerMapper managerMapper, ManagerRepository managerRepository,
                             OrderService orderService, OrderRepository orderRepository, OrderMapper orderMapper,
                             OrderItemMapper orderItemMapper, MenuItemService menuItemService, AddressMapper addressMapper) {
        this.managerService = managerService;
        this.managerMapper = managerMapper;
        this.managerRepository = managerRepository;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.menuItemService = menuItemService;
        this.addressMapper = addressMapper;
    }

    /**
     * get managar model by providing jwt
     * @return
     */
    @GetMapping("/managers")
    public ResponseEntity<DummyManagerDTO> getManager() {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        Manager manager = managerRepository.findByUsername(userLogin).get();
        return ResponseEntity.ok(new DummyManagerDTO(managerMapper.toDto(manager)));
    }

    /**
     * update a manager
     * @param dummyManagerDTO
     * @return
     */
    @PutMapping("/managers")
    public ResponseEntity<DummyManagerDTO> updateManager(@RequestBody DummyManagerDTO dummyManagerDTO) {
        String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
        dummyManagerDTO.setUsername(userLogin);
        dummyManagerDTO.setEmail(userLogin);
        return ResponseEntity.ok(new DummyManagerDTO(managerService.save(dummyManagerDTO)));
    }

    /**
     * get all pizzaria orders by providing manager jwt
     * @return
     */
    @GetMapping("/orders")
    public ResponseEntity<List<DummyOrderDTO>> getOrders() {
        List<DummyOrderDTO> dummyOrderDTOList = orderRepository.findAll().stream().map((order) -> {
            List<DummyOrderItemDTO> dummyOrderItemDTOList = order.getItems().stream().filter((orderItem ->
                menuItemService.findOne(orderItem.getMenuItemId()).isPresent()
            )).map((orderItem) -> {
                DummyOrderItemDTO dummyOrderItemDTO = new DummyOrderItemDTO(orderItemMapper.toDto(orderItem));
                MenuItemDTO menuItemDTO = menuItemService.findOne(orderItem.getMenuItemId()).get();
                dummyOrderItemDTO.setMenuItem(new DummyMenuItemDTO(menuItemDTO));
                return dummyOrderItemDTO;
            }).collect(Collectors.toList());
            DummyAddressDTO dummyAddressDTO = order.getAddress() != null ? new DummyAddressDTO(addressMapper.toDto(order.getAddress())) : null;
            DummyOrderDTO dummyOrderDTO = new DummyOrderDTO(orderMapper.toDto(order));
            dummyOrderDTO.setAddress(dummyAddressDTO);
            dummyOrderDTO.setItems(dummyOrderItemDTOList);
            return dummyOrderDTO;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dummyOrderDTOList);
    }

    /**
     * set order delivered attribute in order model by manager
     * @param orderDTO
     */
    @PostMapping("/order-delivered")
    public void setOrderDelivered(@RequestBody OrderDTO orderDTO) {
        orderService.save(orderDTO);
    }

    /**
     * add a menu item by manager
     * @param menuItemDTO
     */
    @PostMapping("/add-menu-item")
    public void addMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        menuItemService.save(menuItemDTO);
    }

    /**
     * remove a menu item by manager
     * @param menuItemId
     */
    @PostMapping("/remove-menu-item")
    public void removeMenuItem(@RequestBody Long menuItemId) {
        menuItemService.delete(menuItemId);
    }
}
