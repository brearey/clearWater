package ru.oktemsec.clearwater_admin_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ListActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference myRef;
    private List<String> DiscrTask;
    private String userName;

    ListView ListUserTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null) {
            userName = user.getEmail();
        }

        Toast.makeText(ListActivity.this, userName, Toast.LENGTH_SHORT).show();

        ListUserTask = (ListView) findViewById(R.id.discr_for_task);
        myRef = FirebaseDatabase.getInstance().getReference();
        user = mAuth.getCurrentUser();
        myRef.child("root").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                DiscrTask = dataSnapshot.child("tasks").getValue(t);
                updateUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateUI(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, DiscrTask);
        ListUserTask.setAdapter(adapter);
    }

}
