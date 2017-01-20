package essilor.integrator.adapter.dao;

import essilor.integrator.adapter.domain.optosys.Customer;

public interface CustomerDao {

	Customer getCustomer(long customerNumber);
}
