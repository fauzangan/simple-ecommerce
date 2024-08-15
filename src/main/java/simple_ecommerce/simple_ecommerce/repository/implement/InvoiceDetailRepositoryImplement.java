package simple_ecommerce.simple_ecommerce.repository.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import simple_ecommerce.simple_ecommerce.model.InvoiceDetail;
import simple_ecommerce.simple_ecommerce.repository.InvoiceDetailRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class InvoiceDetailRepositoryImplement implements InvoiceDetailRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public InvoiceDetail save(InvoiceDetail invoiceDetail) {
        String sql = "INSERT INTO invoice_details (product_id, invoice_id, quantity, amount) VALUES (?, ?, ?, ?) RETURNING id, product_id, invoice_id, quantity, amount";
        return jdbcTemplate.queryForObject(sql, new Object[]{invoiceDetail.getProduct_id(), invoiceDetail.getInvoice_id(), invoiceDetail.getQuantity(), invoiceDetail.getAmount()}, new InvoiceDetailRowMapper());
    }

    @Override
    public InvoiceDetail update(InvoiceDetail invoiceDetail) {
        String sql = "UPDATE invoice_details SET product_id = ?, invoice_id = ?, quantity = ?, amount = ? WHERE id = ? RETURNING id, product_id, invoice_id, quantity, amount";
        return jdbcTemplate.queryForObject(sql, new Object[]{invoiceDetail.getProduct_id(), invoiceDetail.getInvoice_id(), invoiceDetail.getQuantity(), invoiceDetail.getAmount(), invoiceDetail.getId()}, new InvoiceDetailRowMapper());
    }

    @Override
    public Optional<InvoiceDetail> findById(Integer id) {
        String sql = "SELECT * FROM invoice_details WHERE id = ?";
        try {
            InvoiceDetail invoiceDetail = jdbcTemplate.queryForObject(sql, new Object[]{id}, new InvoiceDetailRowMapper());
            return Optional.of(invoiceDetail);
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public int deleteById(Integer id) {
        String sql = "DELETE FROM invoice_details WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<InvoiceDetail> findAll() {
        String sql = "SELECT * FROM invoice_details";
        return jdbcTemplate.query(sql, new InvoiceDetailRowMapper());
    }

    @Override
    public List<InvoiceDetail> findByInvoiceId(Integer id) {
        String sql = "SELECT * FROM invoice_details WHERE invoice_id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, new InvoiceDetailRowMapper());
    }

    private static class InvoiceDetailRowMapper implements RowMapper<InvoiceDetail> {
        @Override
        public InvoiceDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            return InvoiceDetail.builder()
                    .id(rs.getInt("id"))
                    .product_id(rs.getInt("product_id"))
                    .invoice_id(rs.getInt("invoice_id"))
                    .quantity(rs.getInt("quantity"))
                    .amount(rs.getInt("amount"))
                    .build();
        }
    }
}
