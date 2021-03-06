package com.walletERP.controller.service.delete;

import com.walletERP.model.dao.CustomerDAO;
import com.walletERP.model.entity.Customer;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class DeleteCustomersBean implements Serializable {
    private static final long serialVersionUID = 3528969511017885937L;
    private Customer customer;
    private final CustomerDAO customerDAO;

    @Inject
    public DeleteCustomersBean(Customer customer, CustomerDAO customerDAO) {
        this.customer = customer;
        this.customerDAO = customerDAO;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String deleteCustomer() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String customerID = externalContext.getRequestParameterMap().get("customerIdDelete");
        if (customerID != null) {
            this.customer.setCustomerID(Long.valueOf(customerID));
            this.customerDAO.deleteCustomerByID(customer);
        }

        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage("Successful", "User delete successfully.");
        context.addMessage(null, msg);
        context.getExternalContext().getFlash().setKeepMessages(true);

        return "refresh";
    }
}
