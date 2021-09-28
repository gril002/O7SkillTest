package si.gril.o7skilltest;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeDAO {
    @Query("SELECT * FROM employee")
    List<Employee> getAllUsers();

    @Insert
    void insertAll(Employee... employees);
}
