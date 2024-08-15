package simple_ecommerce.simple_ecommerce.repository.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import simple_ecommerce.simple_ecommerce.model.Product;
import simple_ecommerce.simple_ecommerce.repository.ProductRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImplement implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO products (name, stock, price) VALUES (?, ?, ?) RETURNING id, name, stock, price";
        return jdbcTemplate.queryForObject(sql, new Object[]{product.getName(), product.getStock(), product.getPrice()}, new ProductRowMapper());
    }

    @Override
    public Product update(Product product) {
        String sql = "UPDATE products SET name = ?, stock = ?, price = ? WHERE id = ? RETURNING id, name, stock, price";
        return jdbcTemplate.queryForObject(sql, new Object[]{product.getName(), product.getStock(), product.getPrice(), product.getId()}, new ProductRowMapper());
    }

    @Override
    public Optional<Product> findById(Integer id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper());
            return Optional.of(product);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM products WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    private static class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Product.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .stock(rs.getInt("stock"))
                    .price(rs.getInt("price"))
                    .build();
        }
    }
}
