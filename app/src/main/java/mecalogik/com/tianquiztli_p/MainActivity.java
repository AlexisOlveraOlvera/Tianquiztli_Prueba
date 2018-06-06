package mecalogik.com.tianquiztli_p;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mecalogik.com.tianquiztli_p.m_FireBase.FirebaseHelper;
import mecalogik.com.tianquiztli_p.m_Model.Spacecraft;
import mecalogik.com.tianquiztli_p.m_UI.MyAdapter;

public class MainActivity extends AppCompatActivity  {


    DatabaseReference db;
    FirebaseHelper helper;
    MyAdapter adapter;
    RecyclerView rv;
    EditText nameEditTxt,propTxt,descTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        //INITIALIZE FIREBASE DB
        db = FirebaseDatabase.getInstance().getReference();
        helper=new FirebaseHelper(db);

        //ADAPTER
        adapter= new MyAdapter(this,helper.retrieve());
        rv.setAdapter(adapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInputDialog();
            }
        });

    }

    private void displayInputDialog(){
        Dialog d= new Dialog(this);
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.input_dialog);

        nameEditTxt= (EditText) d.findViewById(R.id.nameEditText);
        propTxt = (EditText) d.findViewById(R.id.propellantEditText);
        descTxt= (EditText) d.findViewById(R.id.descEditText);
        Button saveBtn = (Button) d.findViewById(R.id.saveBtn);

        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String name= nameEditTxt.getText().toString();
                String propellant= propTxt.getText().toString();
                String desc = descTxt.getText().toString();

                //SET DATA
                Spacecraft s=new Spacecraft();
                s.setName(name);
                s.setPropellant(propellant);
                s.setDescription(desc);

                // SIMPLE VALIDATION
                if (name != null && name.length()>0)
                {
                    // THEN SAVE
                    if (helper.save(s)){

                        //IF SAVED CLEAR EDITXT
                        nameEditTxt.setText("");
                        propTxt.setText("");
                        descTxt.setText("");

                        adapter=new MyAdapter(MainActivity.this,helper.retrieve());
                        rv.setAdapter(adapter);

                    }

                }else {
                    Toast.makeText(MainActivity.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        d.show();

    }
}
