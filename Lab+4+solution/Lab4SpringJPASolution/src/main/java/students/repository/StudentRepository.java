package students.repository;

import customers.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import students.domain.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByName(String name);
    Student findByPhone(String phone);

    @Query("select s from Student s where s.address.city= :city")
    List<Student> findByCity(String city);

}

