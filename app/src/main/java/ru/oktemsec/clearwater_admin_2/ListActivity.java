package ru.oktemsec.clearwater_admin_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private String userEmail;
    private ListView list_data;

    private List<ZakazData> list_users = new ArrayList<>();

    private ZakazData selectedZakaz; //hold selected zakaz

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Control
        list_data = (ListView) findViewById(R.id.list_data);

        initFirebase();
        //Very important Part
        addEventFirebaseListener();

    }
    //Инициализация Базы данных Firebase
    private void initFirebase() {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            userEmail = user.getEmail();
            Toast.makeText(ListActivity.this, userEmail, Toast.LENGTH_SHORT).show();
        }

        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
    }

    //метод Выгрузки данных из Firebase
    private void addEventFirebaseListener() {
        list_data.setVisibility(View.INVISIBLE);

        mDatabaseReference.child("zakazy")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (list_users.size() > 0) {
                            list_users.clear();
                        }
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            ZakazData zakaz = postSnapshot.getValue(ZakazData.class);
                            list_users.add(zakaz);
                        }
                        ListViewAdapter adapter = new ListViewAdapter(ListActivity.this, list_users);
                        list_data.setAdapter(adapter);

                        list_data.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}
