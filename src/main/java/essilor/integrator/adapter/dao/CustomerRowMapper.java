package essilor.integrator.adapter.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import essilor.integrator.adapter.domain.optosys.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {

	private long customerNumber;

	public CustomerRowMapper(long customerNumber) {
		this.customerNumber = customerNumber;
	}

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerNumber(customerNumber);
		customer.setSurname(rs.getString(1));
		customer.setFirstName(rs.getString(2));
		customer.setTitle(rs.getString(3));
		return customer;
	}

}
