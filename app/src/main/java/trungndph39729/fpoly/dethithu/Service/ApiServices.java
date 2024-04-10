package trungndph39729.fpoly.dethithu.Service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import trungndph39729.fpoly.dethithu.model.Response;
import trungndph39729.fpoly.dethithu.model.Student;


public interface ApiServices {
    public static String BASE_URL = "http://10.0.2.2:3000/api/";

    @GET("students")
    Call<Response<ArrayList<Student>>> getListStudent();

    @GET("search-student")
    Call<Response<ArrayList<Student>>> searchStudent(@Query("key") String key);

    @POST("add-student")
    Call<Response<Student>> addStudent(@Body Student Student);

    @DELETE("delete-student-by-id/{id}")
    Call<Response<Student>> deleteStudentById(@Path("id") String id);

    @PUT("update-student-by-id/{id}")
    Call<Response<Student>> updateStudentById(@Path("id") String id, @Body Student Student);


}
