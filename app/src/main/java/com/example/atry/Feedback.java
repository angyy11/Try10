package com.example.atry;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private EditText txtName,txtPhone,txtComment;
    private Button button;
    DatabaseReference reff;
    Comment comment;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Intent intent = getIntent();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        txtName=findViewById(R.id.txtName);
        txtPhone=findViewById(R.id.txtPhone);
        txtComment=findViewById(R.id.txtComment);

        button=findViewById(R.id.button);
        comment= new Comment();
        reff= FirebaseDatabase.getInstance().getReference().child("Comment");
        txtName.addTextChangedListener(loginTextWatcher);
        txtPhone.addTextChangedListener(loginTextWatcher);
        txtComment.addTextChangedListener(loginTextWatcher);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comment.setPhone(txtPhone.getText().toString().trim());
                comment.setName(txtName.getText().toString().trim());
                comment.setComment(txtComment.getText().toString().trim());
                reff.child("CustComment").setValue(comment);

                EditText name,phone,comment;
                name=findViewById(R.id.txtName);
                name.setText("");
                phone=findViewById(R.id.txtPhone);
                phone.setText("");
                comment=findViewById(R.id.txtComment);
                comment.setText("");

            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch (item.getItemId())
        {
            case R.id.nav_home:
                intent = new Intent( Feedback.this,Home.class);
                startActivity(intent);
                break;
            case R.id.nav_faq:
                intent = new Intent( Feedback.this,Faq.class);
                startActivity(intent);
                break;
            case R.id.nav_policy:
                intent = new Intent(Feedback.this, Policy.class);
                startActivity(intent);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }
    private TextWatcher loginTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String feedbackInput=txtName.getText().toString().trim();
            button.setEnabled(!feedbackInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    public void back1(View view){
        // This is go new page
        /*Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);*/
        ///////////////////////////////////////////
        // This is close page
        finish();
    }
    public void displayToast(View view) {
        Toast toast=Toast.makeText(this,R.string.toast_message,Toast.LENGTH_SHORT);
        toast.show();

    }

}
