package com.shafique.springboot.repository;

import com.shafique.springboot.model.CustomerDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author mohammedshafique
 */
@Repository
public interface CCSMUserRepository extends CrudRepository<CustomerDetails, Long> {

    @Query("select u FROM CustomerDetails u where u.email= :email and u.password= :password")
    public CustomerDetails findByLoginDetails(@Param("email") String email, @Param("password") String password);

    @Query("select u FROM CustomerDetails u where u.customerId= :customerId")
    public CustomerDetails findCustomerById(@Param("customerId") long customerId);

    @Query("select u FROM CustomerDetails u where u.email= :email")
    public CustomerDetails findCustomerByEmail(@Param("email") String email);
}
