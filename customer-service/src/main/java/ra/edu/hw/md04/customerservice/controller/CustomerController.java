package ra.edu.hw.md04.customerservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ra.edu.hw.md04.customerservice.dto.CustomerLoginDTO;
import ra.edu.hw.md04.customerservice.dto.CustomerRequestDTO;
import ra.edu.hw.md04.customerservice.dto.CustomerResponseDTO;
import ra.edu.hw.md04.customerservice.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDTO register(@Valid @RequestBody CustomerRequestDTO request) {
        return customerService.register(request);
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @PutMapping("/{id}")
    public CustomerResponseDTO update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDTO request
    ) {
        return customerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

    @PutMapping("/login")
    public CustomerResponseDTO login(@Valid @RequestBody CustomerLoginDTO request) {
        return customerService.login(request);
    }
}
