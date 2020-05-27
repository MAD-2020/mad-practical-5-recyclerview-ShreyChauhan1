package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.np.mad.mad_recyclerview.TasksRecyclerView.Task;
import sg.edu.np.mad.mad_recyclerview.TasksRecyclerView.TasksAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = MainActivity.this;

        final ArrayList<Task> data=new ArrayList<>();
        Button add=findViewById(R.id.button);
        final RecyclerView rv=findViewById(R.id.recyclerView);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText editText=findViewById(R.id.editText2);
                String taskText=editText.getText().toString();
                if (taskText.length()!=0){
                    Task task=new Task();
                    task.taskName=taskText;
                    data.add(task);
                    showNewEntry(rv,data);

                }

            }
        });

        TasksAdapter adapter=new TasksAdapter(data,context);
        rv.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        rv.setLayoutManager(layout);

    }

    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param data ArrayList that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        EditText editText=findViewById(R.id.editText2);
        editText.setText("");

        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);

    }

}
