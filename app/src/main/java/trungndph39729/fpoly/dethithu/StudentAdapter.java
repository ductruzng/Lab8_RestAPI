package trungndph39729.fpoly.dethithu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import trungndph39729.fpoly.dethithu.databinding.ViewholderStudentBinding;
import trungndph39729.fpoly.dethithu.handle.Handle;
import trungndph39729.fpoly.dethithu.model.Student;


public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private ArrayList<Student> items;
    private Context context;
    private Handle handle;

    public StudentAdapter(ArrayList<Student> items, Context context, Handle handle) {
        this.items = items;
        this.context = context;
        this.handle = handle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderStudentBinding binding = ViewholderStudentBinding.inflate(LayoutInflater.from(context), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.nameTxt.setText("Ho ten: "+items.get(position).getHoten_ph39729());
        holder.binding.addressTxt.setText("Que quan: "+items.get(position).getQuequan_ph39729());
        holder.binding.pointTxt.setText("Diem: "+items.get(position).getDiem_ph39729());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop());
        Glide.with(context)
                .load(items.get(position).getHinh_anh_ph39729())
                .apply(requestOptions)
                .into(holder.binding.avatar);

        holder.binding.itemview.setOnClickListener(view -> {

        });

        holder.binding.deleteBtn.setOnClickListener(view -> {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Ban co chac la muon xoa khong?.");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (handle != null) {
                                handle.onDelete(items.get(position).get_id());
                            }
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        });

        holder.binding.editBtn.setOnClickListener(view -> {
            final Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialog_edit);
            EditText edName = (EditText) dialog.findViewById(R.id.edName);
            EditText edAddress = (EditText) dialog.findViewById(R.id.edAddress);
            EditText edPoint = (EditText) dialog.findViewById(R.id.edPoint);
            EditText edUrl = (EditText) dialog.findViewById(R.id.edUrlPic);

            edName.setText(items.get(position).getHoten_ph39729());
            edAddress.setText(items.get(position).getQuequan_ph39729());
            edPoint.setText(items.get(position).getDiem_ph39729());
            edUrl.setText(items.get(position).getHinh_anh_ph39729());

            Button button = (Button) dialog.findViewById(R.id.addBtn);
            button.setOnClickListener(view1 -> {
                String name = edName.getText().toString();
                String address = edAddress.getText().toString();
                String point = edPoint.getText().toString();
                String url = edUrl.getText().toString();

                Student student =  new Student();
                student.setHoten_ph39729(name);
                student.setQuequan_ph39729(address);
                student.setDiem_ph39729(point);
                student.setHinh_anh_ph39729(url);

                if (handle != null) {
                    handle.onUpdate(items.get(position).get_id(),student);
                }
                dialog.dismiss();

            });


            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderStudentBinding binding;

        public ViewHolder(ViewholderStudentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
