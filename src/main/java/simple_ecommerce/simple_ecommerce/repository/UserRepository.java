package simple_ecommerce.simple_ecommerce.repository;

import simple_ecommerce.simple_ecommerce.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User update(User user);
    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);
    int deleteById(Integer id);
    List<User> findAll();
}
