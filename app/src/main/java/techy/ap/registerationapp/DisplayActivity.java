package techy.ap.registerationapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import techy.ap.registerationapp.Adapter.RecyclerAdapterActivity;
import techy.ap.registerationapp.Database.DatabaseHandler;
import techy.ap.registerationapp.Modal.User;

public class DisplayActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapterActivity recyclerAdapterActivity;
    private DatabaseHandler db;
    private ArrayList<User>userArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        db=new DatabaseHandler(DisplayActivity.this);
        userArrayList=db.getAllDocsDetail();


        recyclerView=findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapterActivity=new RecyclerAdapterActivity(userArrayList);
        recyclerView.setAdapter(recyclerAdapterActivity);
        recyclerAdapterActivity.setItemclickListener(itemselected);

    }
    private View.OnClickListener itemselected=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder=(RecyclerView.ViewHolder)v.getTag();
            int pos=viewHolder.getAdapterPosition();
            User user=userArrayList.get(pos);
            AlertDialog.Builder alertdialoialog=new AlertDialog.Builder(DisplayActivity.this);
            LayoutInflater inflater=DisplayActivity.this.getLayoutInflater();
            View dialogvoiew= inflater.inflate(R.layout.alert_label_editor,null);
            alertdialoialog.setView(dialogvoiew);
            TextView name=dialogvoiew.findViewById(R.id.fname);
            TextView lname=dialogvoiew.findViewById(R.id.lname);
            TextView dob=dialogvoiew.findViewById(R.id.dob);
            TextView gender=dialogvoiew.findViewById(R.id.gender);
            TextView location=dialogvoiew.findViewById(R.id.location);

            name.setText(user.getFname());
            lname.setText(user.getLname());
            dob.setText(user.getDob());
            gender.setText(user.getGender());
            location.setText(user.getLocation());

            AlertDialog alertDialog=alertdialoialog.create();
            alertDialog.show();
           // Toast.makeText(DisplayActivity.this, "You Clicked: " + users.getFname(), Toast.LENGTH_SHORT).show();
        }
    };
}
