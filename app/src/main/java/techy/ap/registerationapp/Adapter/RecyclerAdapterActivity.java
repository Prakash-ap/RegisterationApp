package techy.ap.registerationapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import techy.ap.registerationapp.Modal.User;
import techy.ap.registerationapp.R;

public class RecyclerAdapterActivity extends RecyclerView.Adapter<RecyclerAdapterActivity.MyViewHolder> {
    private ArrayList<User>userArrayList;
    private View.OnClickListener onClickListener;

    public RecyclerAdapterActivity(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public RecyclerAdapterActivity.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_layout,viewGroup,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterActivity.MyViewHolder myViewHolder, int position) {
        User user=userArrayList.get(position);
        myViewHolder.fname.setText(user.getFname());
        myViewHolder.lname.setText(user.getLname());
        myViewHolder.location.setText(user.getLocation());
        myViewHolder.dob.setText(user.getDob());
        myViewHolder.gender.setText(user.getGender());

    }

public void setItemclickListener(View.OnClickListener  onItemclickListener){
onClickListener=onItemclickListener;
}

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView fname,lname,dob,gender,location;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fname=itemView.findViewById(R.id.c_fname);
            lname=itemView.findViewById(R.id.c_lname);
            dob=itemView.findViewById(R.id.c_dob);
            gender=itemView.findViewById(R.id.c_gender);
            location=itemView.findViewById(R.id.c_location);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }
    }
}
