package com.example.em_project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    //custom method
    // List<EmployeeEntity> findByName(String name);
    //save, delete , findby , findall
}
