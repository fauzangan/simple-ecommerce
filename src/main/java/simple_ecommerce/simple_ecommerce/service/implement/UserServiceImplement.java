package simple_ecommerce.simple_ecommerce.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simple_ecommerce.simple_ecommerce.model.User;
import simple_ecommerce.simple_ecommerce.repository.UserRepository;
import simple_ecommerce.simple_ecommerce.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User request) {
        User newUser = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        return userRepository.save(newUser);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User update(Integer id, User request) {
        User user = findById(id);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return userRepository.update(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
