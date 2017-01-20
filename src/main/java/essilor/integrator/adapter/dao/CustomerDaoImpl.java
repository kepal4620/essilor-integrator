package essilor.integrator.adapter.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import essilor.integrator.adapter.domain.optosys.Customer;

public class CustomerDaoImpl implements CustomerDao {

	private JdbcTemplate jdbcTemplate;

	public CustomerDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public Customer getCustomer(long customerNumber) {
		String sql = "select prijmeni, jmeno, titul from odber where ci_reg=?";
		List<Customer> list = jdbcTemplate.query(sql,  new Object[]{customerNumber}, new CustomerRowMapper(customerNumber));
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
