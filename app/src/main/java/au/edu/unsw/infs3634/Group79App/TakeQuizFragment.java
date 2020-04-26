package au.edu.unsw.infs3634.Group79App;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import au.edu.unsw.infs3634.Group79App.Room.SignupTable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TakeQuizFragment extends Fragment {

    private Button trueButton;
    private Button falseButton;
    private String falseAnswer;
    private String trueAnswer;
    LeaderboardFragment abc = new LeaderboardFragment();

    private int activityTally;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.quiz, container, false);

        TextView textViewResult = rootview.findViewById(R.id.Question);
        TextView Results = rootview.findViewById(R.id.Results);
        TextView Score = rootview.findViewById(R.id.score);

        //call api for a response
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HolderApi jsonPlaceHolderApi = retrofit.create(HolderApi.class);
        Call<Quiz> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                Quiz posts = response.body();
                // set question to textview
                textViewResult.setText(" ");
                String question = "Question: " + posts.getResults().get(0).getQuestion() + "\n";
                question = question.replace("&quot;", "") + "\n";
                question = question.replace("&039#;", "1") + "\n";
                textViewResult.append(question);
            //get the answers from response
                trueAnswer = posts.getResults().get(0).getCorrectAnswer();
                falseAnswer = posts.getResults().get(0).getIncorrectAnswers().get(0);
            }

            //printout failure message if call fails
            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

        //method to check correct answer and call next question when user clicks on false button
        falseButton = rootview.findViewById(R.id.False);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trueAnswer.compareTo("False") == 0) {
                    Results.setText("Correct! 10pts");
                    activityTally += 10;
                    saveSignupTable();
                    Score.setText("Your score has increassed by " + activityTally + "pts");
                } else {
                    Results.setText("Incorrect");
                }
                HolderApi jsonPlaceHolderApi = retrofit.create(HolderApi.class);
                Call<Quiz> call = jsonPlaceHolderApi.getPosts();

                call.enqueue(new Callback<Quiz>() {
                    @Override
                    public void onResponse(Call<Quiz> call, Response<Quiz> response) {

                        if (!response.isSuccessful()) {
                            textViewResult.setText("Code: " + response.code());
                            return;
                        }
                        Quiz posts = response.body();

                        textViewResult.setText(" ");
                        String question = "Question: " + posts.getResults().get(0).getQuestion() + "\n";
                        question = question.replace("&quot;", "") + "\n";
                        question = question.replace("&039#;", "1") + "\n";
                        textViewResult.append(question);

                        trueAnswer = posts.getResults().get(0).getCorrectAnswer();
                        falseAnswer = posts.getResults().get(0).getIncorrectAnswers().get(0);
                    }

                    @Override
                    public void onFailure(Call<Quiz> call, Throwable t) {
                        textViewResult.setText(t.getMessage());
                    }
                });
            }
        });

        //method to check correct answer and call next question when user clicks on true button
        trueButton = rootview.findViewById(R.id.True);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trueAnswer.compareTo("True") == 0) {
                    Results.setText("Correct! 10pts");
                    activityTally += 10;
                     saveSignupTable();
                    Score.setText("Your score has increassed by " + activityTally + "pts");
                } else {
                    Results.setText("Incorrect");
                }
                HolderApi jsonPlaceHolderApi = retrofit.create(HolderApi.class);
                Call<Quiz> call = jsonPlaceHolderApi.getPosts();

                call.enqueue(new Callback<Quiz>() {
                    @Override
                    public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                        if (!response.isSuccessful()) {
                            textViewResult.setText("Code: " + response.code());
                            return;
                        }
                        Quiz posts = response.body();

                        textViewResult.setText(" ");
                        String question = "Question: " + posts.getResults().get(0).getQuestion() + "\n";
                        question = question.replace("&quot;", "") + "\n";
                        question = question.replace("&039#;", "1") + "\n";
                        textViewResult.append(question);

                        trueAnswer = posts.getResults().get(0).getCorrectAnswer();
                        falseAnswer = posts.getResults().get(0).getIncorrectAnswers().get(0);
                    }

                    @Override
                    public void onFailure(Call<Quiz> call, Throwable t) {
                        textViewResult.setText(t.getMessage());
                    }
                });
            }

        });

        return rootview;
    }


        //method to assign points and store score in database

    private void saveSignupTable() {
        class SaveSignupTable extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                SignupTable signupTable = new SignupTable();
                int points = 10;

                // query database for details
                List<SignupTable> signupTables = DatabaseClient.getInstance(getContext()).getAppDatabase().signupDao().getDetails();
                int currentscores = signupTables.get(0).getDBscore();

                // add points earned in session to currentscores
                int total = currentscores + points;

                //insert into database
                signupTable.setDBscore(total);
                DatabaseClient.getInstance(getContext()).getAppDatabase().signupDao().insertDetails(signupTable);

                //insert to arraylist for leadarboard/awards
                abc.getList().get(0).setScores(Integer.toString(total));

                return null;
            }
        }
        SaveSignupTable st = new SaveSignupTable();
        st.execute();
    }
}

