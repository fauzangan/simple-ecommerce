package simple_ecommerce.simple_ecommerce.repository.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import simple_ecommerce.simple_ecommerce.model.Invoice;
import simple_ecommerce.simple_ecommerce.model.Product;
import simple_ecommerce.simple_ecommerce.repository.InvoiceRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InvoiceRepositoryImplement implements InvoiceRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Invoice save(Invoice invoice) {
        String sql = "INSERT INTO invoices (user_id, quantity, amount, status, va_number) VALUES (?, ?, ?, ?, ?) RETURNING id, user_id, quantity, amount, status, va_number";
        return jdbcTemplate.queryForObject(sql, new Object[]{invoice.getUser_id(), invoice.getQuantity(), invoice.getAmount(), invoice.getStatus(), invoice.getVa_number()}, new InvoiceRowMapper());
    }

    @Override
    public Invoice update(Invoice invoice) {
        String sql = "UPDATE invoices SET user_id = ?, quantity = ?, amount = ?, status = ?, va_number = ? WHERE id = ? RETURNING id, user_id, quantity, amount, status, va_number";
        return jdbcTemplate.queryForObject(sql, new Object[]{invoice.getUser_id(), invoice.getQuantity(), invoice.getAmount(), invoice.getStatus(), invoice.getVa_number(), invoice.getId()}, new InvoiceRowMapper());
    }

    @Override
    public Optional<Invoice> findById(Integer id) {
        String sql = "SELECT * FROM invoices WHERE id = ?";
        try {
            Invoice invoice = jdbcTemplate.queryForObject(sql, new Object[]{id}, new InvoiceRowMapper());
            return Optional.of(invoice);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Invoice> findByUserId(Integer id) {
        String sql = "SELECT * FROM invoices WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new InvoiceRowMapper());
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM invoices WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Invoice> findAll() {
        String sql = "SELECT * FROM invoices";
        return jdbcTemplate.query(sql, new InvoiceRowMapper());
    }

    private static class InvoiceRowMapper implements RowMapper<Invoice> {
        @Override
        public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Invoice.builder()
                    .id(rs.getInt("id"))
                    .amount(rs.getInt("amount"))
                    .quantity(rs.getInt("quantity"))
                    .status(rs.getString("status"))
                    .user_id(rs.getInt("user_id"))
                    .va_number(rs.getString("va_number"))
                    .build();
        }
    }
}
