package si.gril.o7skilltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabAddEmployee;
    Button btnAnalytics;
    RecyclerView rvEmployees;
    RecyclerView.Adapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAddEmployee = findViewById(R.id.fab_add_employee);
        fabAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateEmployeeActivity.class));
            }
        });

        btnAnalytics = findViewById(R.id.btn_analytics);
        btnAnalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AnalyticsActivity.class));
            }
        });

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "dev")
                .allowMainThreadQueries().build();
        List<Employee> employees = db.employeeDAO().getAllUsers();

        rvEmployees = findViewById(R.id.rv_employees);
        rvEmployees.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvEmployees.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new EmployeeAdapter(employees);
        rvEmployees.setAdapter(rvAdapter);

    }
}