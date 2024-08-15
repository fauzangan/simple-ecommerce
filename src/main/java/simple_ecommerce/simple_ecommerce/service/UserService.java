package simple_ecommerce.simple_ecommerce.service;

import simple_ecommerce.simple_ecommerce.model.User;

import java.util.List;

public interface UserService {
    User create(User request);
    List<User> getAll();
    User findById(Integer id);
    User update(Integer id, User Request);
    void delete(Integer id);
}
