package mecalogik.com.tianquiztli_p.m_UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import mecalogik.com.tianquiztli_p.R;

/**
 * Created by Ryzen on 06/06/2018.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView nameTxt,propTxt,descTxt;
    ItemClickListener itemClickListener;

    public MyViewHolder(View itemView){
        super(itemView);

        nameTxt=(TextView) itemView.findViewById(R.id.nameTxt);
        propTxt=(TextView) itemView.findViewById(R.id.propellantTxt);
        descTxt=(TextView) itemView.findViewById(R.id.descTxt);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }
}
