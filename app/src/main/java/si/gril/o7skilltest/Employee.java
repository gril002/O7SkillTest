package si.gril.o7skilltest;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.joda.time.LocalDate;
import org.joda.time.Years;

@Entity
public class Employee {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "dob")
    private String dob;

    @ColumnInfo(name = "gender")
    private char gender;

    @ColumnInfo(name = "salary")
    private double salary;

    public Employee(String name, String dob, char gender, double salary) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.salary = salary;
    }

    public Employee(String name, LocalDate dob, char gender, double salary) {
        this.name = name;
        this.dob = dob.toString("yyyy-MM-dd");
        this.gender = gender;
        this.salary = salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }

    public LocalDate getDobDate() {
        return new LocalDate(dob);
    }

    public void setDobDate(LocalDate dob) {
        this.dob = dob.toString("yyyy-MM-dd");
    }



    public int getAge() {
        LocalDate now = new LocalDate();
        LocalDate dateOfBirth = new LocalDate(dob);
        Years between = Years.yearsBetween(new LocalDate(dob), new LocalDate());
        return between.getYears();
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
