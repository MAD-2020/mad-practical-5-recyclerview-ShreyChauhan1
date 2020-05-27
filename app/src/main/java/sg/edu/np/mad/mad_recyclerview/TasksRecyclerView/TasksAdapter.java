package sg.edu.np.mad.mad_recyclerview.TasksRecyclerView;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.mad_recyclerview.MainActivity;
import sg.edu.np.mad.mad_recyclerview.R;

 class TaskViewHolder extends RecyclerView.ViewHolder {
    TextView taskName;
    View view;
    public TaskViewHolder(View v) {
        super(v);
        view = v;
        taskName = v.findViewById(R.id.taskTextView);

    }
}
public class TasksAdapter extends RecyclerView.Adapter<TaskViewHolder>{
     ArrayList<Task> data;
     Context context;
     public TasksAdapter(ArrayList<Task>inputData,Context context){
         this.context = context;

         data=inputData;
     }
     @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
         View v = null;
         v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row2, parent, false);
         return new TaskViewHolder(v);

     }
     @Override
    public void onBindViewHolder(@NonNull final TaskViewHolder holder, final int position){
         Task information=data.get(position);
         holder.taskName.setText(information.taskName);
         holder.view.setOnClickListener(new View.OnClickListener() {

             @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
             @Override
             public void onClick(View v) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(context);
                 String taskName=holder.taskName.getText().toString();
                 View view=LayoutInflater.from(v.getContext()).inflate(R.layout.dialog,null);
                // builder.setView(R.layout.dialog);
              TextView tempTask=view.findViewById(R.id.textView3);
              tempTask.setText(taskName+"?");
              builder.setView(view)
                      .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              dialog.dismiss();
                              
                          }
                      })
                      .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              data.remove(position);
                          }
                      });
              AlertDialog dialog=builder.create();
              dialog.show();
             }
         });
     }
     @Override
    public int getItemCount(){return data.size();}

}