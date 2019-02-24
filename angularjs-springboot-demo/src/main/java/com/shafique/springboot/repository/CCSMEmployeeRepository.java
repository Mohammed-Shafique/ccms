package com.shafique.springboot.repository;

import com.shafique.springboot.model.CustomerDetails;
import com.shafique.springboot.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CCSMEmployeeRepository extends CrudRepository<Employee, Long> {
    @Query("select u FROM Employee u where u.email= :email and u.password= :password")
    public Employee findByLoginDetails(@Param("email") String email, @Param("password") String password);

    @Query("select u FROM Employee u where u.employeeId= :employeeId")
    public Employee findEmployeById(@Param("employeeId") long employeeId);

    @Query("select u FROM Employee u where u.email= :email")
    public Employee findCustomerByEmail(@Param("email") String email);

    @Query("select employeeId FROM Employee ORDER BY employeeId desc")
    public long getId();

    @Query("select e FROM Employee e where e.employeeId != :empId")
    public List<Employee> getAllEmployees(long empId);

}
