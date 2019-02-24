package com.shafique.springboot.repository;

import com.shafique.springboot.model.Complaint;
import com.shafique.springboot.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mohammedshafique
 */
@Repository
public interface CCSMComplaintsRepository extends CrudRepository<Complaint, Long> {
    @Query("select e FROM Complaint e where e.assignedTo = 'MAN'")
    public List<Complaint> getAllComplaints();
}
