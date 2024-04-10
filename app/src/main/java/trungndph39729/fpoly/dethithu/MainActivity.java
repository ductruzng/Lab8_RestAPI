package trungndph39729.fpoly.dethithu;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import trungndph39729.fpoly.dethithu.Service.HttpRequest;
import trungndph39729.fpoly.dethithu.databinding.ActivityMainBinding;
import trungndph39729.fpoly.dethithu.handle.Handle;
import trungndph39729.fpoly.dethithu.model.Response;
import trungndph39729.fpoly.dethithu.model.Student;

public class MainActivity extends AppCompatActivity implements Handle {
    private ActivityMainBinding binding;
    private HttpRequest httpRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       binding = ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

       binding.addFab.setOnClickListener(view -> onAdd());

       httpRequest = new HttpRequest();

       httpRequest.callAPI().getListStudent().enqueue(getListStudentResponse);
    }

    private void getData(ArrayList<Student> ds) {
        binding.recycleStudent.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recycleStudent.setAdapter(new StudentAdapter(ds,getApplicationContext(),this));
    }
    private void onAdd(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_add);
        EditText edName = (EditText) dialog.findViewById(R.id.edName);
        EditText edAddress = (EditText) dialog.findViewById(R.id.edAddress);
        EditText edPoint = (EditText) dialog.findViewById(R.id.edPoint);
        EditText edUrl = (EditText) dialog.findViewById(R.id.edUrlPic);

        Button button = (Button) dialog.findViewById(R.id.addBtn);
        button.setOnClickListener(view -> {
            String name = edName.getText().toString();
            String address = edAddress.getText().toString();
            String point = edPoint.getText().toString();
            String url = edUrl.getText().toString();

            Student student =  new Student();
            student.setHoten_ph39729(name);
            student.setQuequan_ph39729(address);
            student.setDiem_ph39729(point);
            student.setHinh_anh_ph39729(url);

            httpRequest.callAPI().addStudent(student).enqueue(responseStudentAPI);
            dialog.dismiss();

        });


        dialog.show();

    }

    @Override
    public void onDelete(String dd) {
        httpRequest.callAPI()
                .deleteStudentById(dd)
                .enqueue(responseStudentAPI);
    }

    @Override
    public void onUpdate(String Id, Student student) {
            httpRequest.callAPI().updateStudentById(Id,student).enqueue(responseStudentAPI);
    }

    retrofit2.Callback<Response<ArrayList<Student>>> getListStudentResponse = new Callback<Response<ArrayList<Student>>>() {
        @Override
        public void onResponse(Call<Response<ArrayList<Student>>> call, retrofit2.Response<Response<ArrayList<Student>>> response) {
            if (response.isSuccessful()) {
                if (response.body().getStatus() == 200) {
                    ArrayList<Student> ds = response.body().getData();

                    getData(ds);

                    Toast.makeText(MainActivity.this, response.body().getMessenger(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<ArrayList<Student>>> call, Throwable t) {
            Log.d(">>>> getListStudent", "onFailure: " + t.getMessage());
        }
    };

    Callback<Response<Student>>  responseStudentAPI =  new Callback<Response<Student>>() {
        @Override
        public void onResponse(Call<Response<Student>> call, retrofit2.Response<Response<Student>> response) {
            if (response.isSuccessful()){
                if (response.body().getStatus() == 200){
                    httpRequest.callAPI()
                            .getListStudent()
                            .enqueue(getListStudentResponse);
                    Toast.makeText(MainActivity.this, response.body().getMessenger(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<Student>> call, Throwable t) {
            Log.d("ZZZZZZZZZZ", "onFailure: "+t.getMessage());

        }
    };
}