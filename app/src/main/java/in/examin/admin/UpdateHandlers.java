package in.examin.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.TextView;

import static in.examin.admin.R.id.nestedScrollView;

public class UpdateHandlers extends AppCompatActivity implements View.OnClickListener {
    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutId;
    private TextView name;
    private TextView id;
    private TextInputLayout textInputLayoutAddress;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputEditText textInputEditTextAddress;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    User user;
    InputValidation inputValidation;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_handlers);


        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView1);
        textInputLayoutId=(TextInputLayout) findViewById(R.id.textInputLayoutId1);
        id=(TextView)findViewById(R.id.id);
        name=(TextView)findViewById(R.id.name);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName1);
        textInputLayoutAddress=(TextInputLayout) findViewById(R.id.textInputLayoutAddr1);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail1);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword1);
        textInputEditTextAddress = (TextInputEditText) findViewById(R.id.textInputEditTextAddr1);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail1);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword1);
        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister1);
        appCompatButtonRegister.setOnClickListener(this);



    }
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister1:
                Intent intent = getIntent();
                Bundle extras = intent.getExtras();
                String s = extras.getString("name");
                int s1 = extras.getInt("selectedIt");
                Boolean success =  postDataToSQLite(s,s1);
                id.setText(s1);
                name.setText(s);

                if(success)
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gotoupdateactivity();
                        }
                    }, 1000);
                }
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    public void gotoupdateactivity(){
        Intent intentRegister = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intentRegister);
    }
    public boolean postDataToSQLite(String b,int a) {
        //check for id and update user
        int id = a;
        String name = b;
        user=new User();
        user.setId(id);
        user.setName(name);
        Context context=getApplicationContext();
        inputValidation=new InputValidation(context);

        if (!inputValidation.isInputEditTextFilled(textInputEditTextAddress, textInputLayoutAddress, getString(R.string.error_message_address))) {
            return false;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return false;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return false;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return false;
        }


        String n1  =textInputEditTextAddress.getText().toString().trim();
        user.setAddress(n1);
        user.setEmail(textInputEditTextEmail.getText().toString().trim());
        user.setPassword(textInputEditTextPassword.getText().toString().trim());

        databaseHelper=new DatabaseHelper(context);
        databaseHelper.updateUser(user);

        // Snack Bar to show success message that record saved successfully
        Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
         return true;
    }



}
