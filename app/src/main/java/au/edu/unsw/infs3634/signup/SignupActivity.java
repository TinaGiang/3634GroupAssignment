package au.edu.unsw.infs3634.signup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import au.edu.unsw.infs3634.signup.Room.SignupTable;

public class SignupActivity extends AppCompatActivity {

    EditText sfirstname;
    EditText slastname;
    EditText semail;
    EditText spassword;
    Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        sfirstname = findViewById(R.id.sfirstname);
        slastname = findViewById(R.id.slastname);
        semail = findViewById(R.id.semail);
        spassword = findViewById(R.id.spassword);
        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveSignupTable();
            }
        });
    }

    private void saveSignupTable() {

        final String firstname = sfirstname.getText().toString().trim();
        final String lastname = slastname.getText().toString().trim();
        final String email = semail.getText().toString().trim();
        final String password = spassword.getText().toString().trim();


        if (firstname.isEmpty()) {
            sfirstname.setError("First name required");
            sfirstname.requestFocus();
            return;
        }

        if (lastname.isEmpty()) {
            slastname.setError("Last name required");
            slastname.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            semail.setError("Email required");
            semail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            spassword.setError("Password required");
            spassword.requestFocus();
            return;
        }

        class SaveSignupTable extends AsyncTask<Void, Void, Void> {

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
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveSignupTable st = new SaveSignupTable();
        st.execute();
    }
}

