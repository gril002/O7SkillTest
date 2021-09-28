package si.gril.o7skilltest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    List<Employee> employees;

    public EmployeeAdapter(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_employee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EmployeeAdapter.ViewHolder holder, int position) {
        holder.name.setText(employees.get(position).getName());
        holder.gender.setText(employees.get(position).getGender() == 'M' ? "Male,": "Female,");
        holder.dob.setText(String.format("%d y/o", employees.get(position).getAge()));
        holder.salary.setText(String.format("%.0f €",employees.get(position).getSalary()));
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView gender;
        public TextView dob;
        public TextView salary;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.row_employee_name);
            gender = itemView.findViewById(R.id.row_employee_gender);
            dob = itemView.findViewById(R.id.row_employee_dob);
            salary = itemView.findViewById(R.id.row_employee_salary);
        }
    }
}
