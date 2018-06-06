package mecalogik.com.tianquiztli_p.m_UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mecalogik.com.tianquiztli_p.DetailActivity;
import mecalogik.com.tianquiztli_p.R;
import mecalogik.com.tianquiztli_p.m_Model.Spacecraft;

/**
 * Created by Ryzen on 06/06/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    Context c;
    ArrayList<Spacecraft> spacecrafts;

    public MyAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final  Spacecraft s=spacecrafts.get(position);

        holder.nameTxt.setText(s.getName());
        holder.propTxt.setText(s.getPropellant());
        holder.descTxt.setText(s.getDescription());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                //OPEN DETAIL ACTIVITY

                openDetailActivity(s.getName(),s.getDescription(),s.getPropellant());
            }
        });
    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }

    //OPEN DETAIL ACTIVITY

    private void openDetailActivity(String...details){
        Intent i=new Intent(c, DetailActivity.class);

        i.putExtra("NAME_KEY",details[0]);
        i.putExtra("DESC_KEY",details[1]);
        i.putExtra("PROP_KEY",details[2]);

        c.startActivity(i);
    }
}
