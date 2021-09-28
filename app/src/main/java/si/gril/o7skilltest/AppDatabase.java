package si.gril.o7skilltest;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EmployeeDAO employeeDAO();

}
