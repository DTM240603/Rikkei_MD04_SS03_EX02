package ra.edu.hw.md04.customerservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ra.edu.hw.md04.customerservice.dto.CustomerLoginDTO;
import ra.edu.hw.md04.customerservice.dto.CustomerRequestDTO;
import ra.edu.hw.md04.customerservice.dto.CustomerResponseDTO;
import ra.edu.hw.md04.customerservice.entity.Customer;
import ra.edu.hw.md04.customerservice.exception.CustomerNotFoundException;
import ra.edu.hw.md04.customerservice.exception.EmailAlreadyExistsException;
import ra.edu.hw.md04.customerservice.exception.InvalidCredentialsException;
import ra.edu.hw.md04.customerservice.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomerResponseDTO register(CustomerRequestDTO request) {
        if (customerRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }

        Customer customer = new Customer(
                request.fullName(),
                request.email(),
                passwordEncoder.encode(request.password())
        );
        return toResponse(customerRepository.save(customer));
    }

    public CustomerResponseDTO getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        return toResponse(customer);
    }

    public CustomerResponseDTO update(Long id, CustomerRequestDTO request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        if (customerRepository.existsByEmailAndIdNot(request.email(), id)) {
            throw new EmailAlreadyExistsException(request.email());
        }

        customer.setFullName(request.fullName());
        customer.setEmail(request.email());
        customer.setPassword(passwordEncoder.encode(request.password()));
        return toResponse(customerRepository.save(customer));
    }

    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        customerRepository.delete(customer);
    }

    public CustomerResponseDTO login(CustomerLoginDTO request) {
        Customer customer = customerRepository.findByEmail(request.email())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(request.password(), customer.getPassword())) {
            throw new InvalidCredentialsException();
        }
        return toResponse(customer);
    }

    private CustomerResponseDTO toResponse(Customer customer) {
        return new CustomerResponseDTO(customer.getId(), customer.getFullName(), customer.getEmail());
    }
}
