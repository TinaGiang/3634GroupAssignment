package au.edu.unsw.infs3634.Group79App;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import au.edu.unsw.infs3634.Group79App.Room.SignupTable;

public class SignupActivity extends AppCompatActivity {

    EditText sfirstname;
    EditText slastname;
    EditText semail;
    EditText spassword;
    EditText sconfirmPassword;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        sfirstname = findViewById(R.id.sfirstname);
        slastname = findViewById(R.id.slastname);
        semail = findViewById(R.id.semail);
        spassword = findViewById(R.id.spassword);
        sconfirmPassword = findViewById(R.id.sconfirmPassword);
        signupButton = findViewById(R.id.signupButton);

        //Method collects user's details and displays an alert if successful or unsuccessful
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String firstnameString = sfirstname.getText().toString().trim();
                final String lastnameString = slastname.getText().toString().trim();
                final String emailString = semail.getText().toString().trim();
                final String passwordString = spassword.getText().toString().trim();
                final String confirmPassword = sconfirmPassword.getText().toString().trim();

                if (TextUtils.isEmpty(firstnameString) || TextUtils.isEmpty(lastnameString)
                        || TextUtils.isEmpty(emailString) || TextUtils.isEmpty(passwordString) ||
                        !confirmPassword.equals(passwordString)) {

                    openInvalidDialog();

                    if (firstnameString.isEmpty()) {
                        sfirstname.setError("First name required");
                        sfirstname.requestFocus();
                        return;
                    }

                    if (lastnameString.isEmpty()) {
                        slastname.setError("Last name required");
                        slastname.requestFocus();
                        return;
                    }

                    if (emailString.isEmpty()) {
                        semail.setError("Email required");
                        semail.requestFocus();
                        return;
                    }

                    if (passwordString.isEmpty()) {
                        spassword.setError("Password required");
                        spassword.requestFocus();
                        return;
                    }

                    if (!confirmPassword.equals(passwordString)) {
                        sconfirmPassword.setError("Passwords must match");
                        sconfirmPassword.requestFocus();
                        return;
                    }
                } else {
                    saveSignupTable();
                    openEmailDialog();
                }
            }
        });
    }

    public void openInvalidDialog() {
        SignupInvalidDialog signupInvalidDialog = new SignupInvalidDialog();
        signupInvalidDialog.show(getSupportFragmentManager(), "Signup Invalid Dialog");
    }

    public void openEmailDialog() {
        SignupEmailDialog signupEmailDialog = new SignupEmailDialog();
        signupEmailDialog.show(getSupportFragmentManager(), "Signup Email Dialog");
    }

    //Method adds the user's details to the database from the sign up screen
    private void saveSignupTable() {

        class SaveSignupTable extends AsyncTask<Void, Void, Void> {

            final String firstname = sfirstname.getText().toString().trim();
            final String lastname = slastname.getText().toString().trim();
            final String email = semail.getText().toString().trim();
            final String password = spassword.getText().toString().trim();

            @Override
            protected Void doInBackground(Void... voids) {
                SignupTable signupTable = new SignupTable();
                signupTable.setFirstName(firstname);
                signupTable.setFLastName(lastname);
                signupTable.setEmail(email);
                signupTable.setPassword(password);

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().signupDao().insertDetails(signupTable);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Details Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveSignupTable st = new SaveSignupTable();
        st.execute();
    }
}

