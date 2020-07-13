package com.walletERP.model.mapper;

import com.walletERP.model.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Scope("prototype")
public class CustomerMapper implements RowMapper<Customer> {
    private final Customer customer;

    @Autowired
    public CustomerMapper(@Qualifier("customer") Customer customer) {
        this.customer = customer;
    }

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        this.customer.setCustomerID(rs.getLong("customer_id"));
        this.customer.setCustomerName(rs.getString("customer_name"));
        this.customer.setCountry(rs.getString("country"));
        this.customer.setState(rs.getString("state"));
        this.customer.setAddress(rs.getString("address"));
        this.customer.setCreateDate(rs.getTimestamp("create_date"));
        return this.customer;
    }
}
