package si.gril.o7skilltest;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.RandomAccess;

public class CreateEmployeeActivity extends AppCompatActivity {

    EditText inputName;
    EditText inputDob;
    EditText inputSalary;
    RadioGroup rgGender;
    RadioButton rbMale;
    RadioButton rbFemale;
    Button btnSave;
    CalendarView calendarInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_create_employee);

        inputName = findViewById(R.id.input_name);
        inputDob = findViewById(R.id.input_dob);
        inputSalary = findViewById(R.id.input_salary);
        rgGender = findViewById(R.id.rg_gender);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        btnSave = findViewById(R.id.btn_save);

        final Calendar myCalendar = Calendar.getInstance();
        LocalDate date;

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                inputDob.setText(String.format("%d. %d. %d",dayOfMonth, monthOfYear, year));
            }
        };

        inputDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(CreateEmployeeActivity.this,
                        dateSetListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 31536000000L); // Set maximum date to 1 year ago
                dialog.show();
            }
        });


        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dev")
                .allowMainThreadQueries().build();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean error = false;
                if (inputName.getText().toString().equals(""))
                {
                    inputName.setError("Name must not be empty!");
                    error = true;
                }
                if (inputDob.getText().toString().equals(""))
                {
                    inputDob.setError("Birthday must not be empty!");
                    error = true;
                }
                if (inputSalary.getText().toString().equals(""))
                {
                    inputSalary.setError("Salary must not be empty!");
                    error = true;
                }
                if (!error)
                {
                    Employee employee = new Employee(inputName.getText().toString(),
                            new LocalDate(myCalendar),
                            rgGender.getCheckedRadioButtonId() == rbMale.getId() ? 'M' : 'F',
                            Double.parseDouble(inputSalary.getText().toString()));
                    db.employeeDAO().insertAll(employee);
                    startActivity(new Intent(CreateEmployeeActivity.this, MainActivity.class));
                }
            }
        });


    }
}
