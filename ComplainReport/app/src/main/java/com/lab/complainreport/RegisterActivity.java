package com.lab.complainreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
private final RegisterActivity activity=RegisterActivity.this;
private NestedScrollView nestedScrollView;
private TextInputLayout layName;
private TextInputLayout layEmail;
private TextInputLayout layPass;
private TextInputLayout layCPass;
private EditText eName;
private EditText eEmail;
private EditText ePass;
private EditText eCPass;
private AppCompatButton reg;
private AppCompatTextView al;
private InputValidation inputValidation;
private Database jDatabase;
private User jUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg=(AppCompatButton)findViewById(R.id.regi);
        al=(AppCompatTextView) findViewById(R.id.log);

        al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        initView();
        initListener();
        initObject();

    }
    private void initView(){
        nestedScrollView=findViewById(R.id.nest);
        layName=findViewById(R.id.Name);
        layEmail=findViewById(R.id.Email);
        layPass=findViewById(R.id.pass);
        layCPass=findViewById(R.id.cpass);
        eName=findViewById(R.id.ename);
        eEmail=findViewById(R.id.etemail);
        ePass=findViewById(R.id.epass);
        eCPass=findViewById(R.id.ecp);

    }
    private void initListener(){
        reg.setOnClickListener(this);

    }
    private void initObject(){
        inputValidation=new InputValidation(activity);
        jDatabase=new Database(activity);
         jUser=new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.regi:
                postDateSq();
                break;

        }
    }
    private void postDateSq(){
        if(!inputValidation.isInputEditText(eName,layName,getString(R.string.errormessagenaam))){
            return;

        }
        if(!inputValidation.isInputEditText(eEmail,layEmail,getString(R.string.errormessageemail))){
         return;
        }
        if(!inputValidation.isInputEditTextEmail(eEmail,layEmail,getString(R.string.errormessageemail))){
          return;
        }
        if(!inputValidation.isInputEditText(ePass,layPass,getString(R.string.errormessagepass))){
       return;
        }
        if(!inputValidation.isInputEditTextMatch(ePass,eCPass,layCPass,getString(R.string.errormessagematch))){
return;
        }
        if(!jDatabase.checkUser(eEmail.getText().toString().trim())){
            jUser.setName(eName.getText().toString().trim());
            jUser.setEmail(eEmail.getText().toString().trim());
            jUser.setPassword(ePass.getText().toString().trim());
            jDatabase.addUser(jUser);

            Snackbar.make(nestedScrollView,getString(R.string.success), Snackbar.LENGTH_LONG).show();
            Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(i);

            emptyInputEditText();
        }
        else{
            Snackbar.make(nestedScrollView,getString(R.string.emailhaipehle), Snackbar.LENGTH_LONG).show();
        }
    }
    private void emptyInputEditText(){
        eName.setText(null);
        eEmail.setText(null);
        ePass.setText(null);
        eCPass.setText(null);

    }
}