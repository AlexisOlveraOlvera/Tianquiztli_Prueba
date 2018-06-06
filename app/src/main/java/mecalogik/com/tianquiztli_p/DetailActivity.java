package mecalogik.com.tianquiztli_p;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView nameTxt, descTxt, propTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        nameTxt = (TextView) findViewById(R.id.nameDetailTxt);
        descTxt = (TextView) findViewById(R.id.descDetailTxt);
        propTxt = (TextView) findViewById(R.id.propellantDetailTxt);

        // GET INTENT
        Intent i = this.getIntent();

        // RECEIVE DATA
        String name =i.getExtras().getString("NAME_KEY");
        String desc =i.getExtras().getString("DESC_KEY");
        String propellant =i.getExtras().getString("PROP_KEY");

        //BIND DATA
        nameTxt.setText(name);
        descTxt.setText(desc);
        propTxt.setText(propellant);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
