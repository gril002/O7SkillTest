package si.gril.o7skilltest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Comparator;
import java.util.List;

public class AnalyticsActivity extends AppCompatActivity {

    TextView txtAvgAge;
    TextView txtMedAge;
    TextView txtMaxSalary;
    TextView txtMFRatio;
    ProgressBar pgMFRatio;
    Button btnHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        txtAvgAge = findViewById(R.id.txt_avg_age);
        txtMedAge = findViewById(R.id.txt_med_age);
        txtMaxSalary = findViewById(R.id.txt_max_salary);
        txtMFRatio = findViewById(R.id.txt_mf_ratio);
        pgMFRatio = findViewById(R.id.pb_mf_ratio);
        btnHome = findViewById(R.id.btn_home);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dev")
                .allowMainThreadQueries().build();
        List<Employee> employees = db.employeeDAO().getAllUsers();
        if (employees.isEmpty()) {
            txtAvgAge.setText("N/A");
            txtMedAge.setText("N/A");
            txtMaxSalary.setText("N/A");
            txtMFRatio.setText("N/A");
            pgMFRatio.setProgress(50);
        }
        else {
            float avgYears = 0;
            double maxSalary = 0;
            int maleCount = 0;
            int mfRatio = 0;
            for (Employee employee : employees) {
                avgYears += employee.getAge();
                if (employee.getSalary() > maxSalary)
                    maxSalary = employee.getSalary();
                if (employee.getGender() == 'M')
                    maleCount++;
            }
            mfRatio = Math.round((float)maleCount/(float)employees.size()*100);
            txtAvgAge.setText(String.format("%.2f years", avgYears /= employees.size()));

            if (employees.size() > 1)
            {
                employees.sort(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee e0, Employee e1) {
                        return e0.getAge() - e1.getAge();
                    }
                });
            }
            float medianAge = 0;
            if ((employees.size() & 1) == 0)
                medianAge = (employees.get((employees.size()/2-1)).getAge() + employees.get((employees.size()/2)).getAge()) /2;
            else
                medianAge = employees.get((employees.size()/2)).getAge();
            txtMedAge.setText(String.format("%.1f years", medianAge));

            txtMaxSalary.setText(String.format("%.2f €", maxSalary));

            txtMFRatio.setText(String.format("%d / %d", mfRatio, 100-mfRatio));
            pgMFRatio.setProgress(mfRatio);
        }

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AnalyticsActivity.this, MainActivity.class));
            }
        });
    }
}
