package au.edu.unsw.infs3634.Group79App;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

    int a = 0;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.quiz, container, false);


        TextView textViewResult = rootview.findViewById(R.id.Question);
        TextView Results = rootview.findViewById(R.id.Results);
        TextView Score = rootview.findViewById(R.id.score);

        LeaderboardFragment abc = new LeaderboardFragment();


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


                textViewResult.setText(" ");

                String question = "Question: " + posts.getResults().get(0).getQuestion() + "\n";



                question = question.replace("&quot;", "").replace("039#;","") + "\n";
                // content += "Correct Answers: " + posts.getResults().get(0).getCorrectAnswer() + "\n";
                //  content += "Incorrect Answers1: " + posts.getResults().get(0).getIncorrectAnswers().get(0) + "\n";
                textViewResult.append(question);

                trueAnswer = posts.getResults().get(0).getCorrectAnswer();
                falseAnswer = posts.getResults().get(0).getIncorrectAnswers().get(0);


            }


            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });

        //button click true/false

        falseButton = rootview.findViewById(R.id.False);

        falseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (trueAnswer.compareTo("False") == 0) {
                    Results.setText("Correct! 10pts");


                    a += 10;

                    abc.getList().get(0).setScores(Integer.toString(a));

                    Score.setText("Your Score is " + abc.getList().get(0).getScores());


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



                        question = question.replace("&quot;", "").replace("039#;","") + "\n";
                        //         content += "Correct Answers: " + posts.getResults().get(0).getCorrectAnswer() + "\n";
                        //     content += "Incorrect Answers1: " + posts.getResults().get(0).getIncorrectAnswers().get(0) + "\n";
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


        trueButton = rootview.findViewById(R.id.True);

        trueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (trueAnswer.compareTo("True") == 0) {
                    Results.setText("Correct! 10pts");


                    a += 10;

                    abc.getList().get(0).setScores(Integer.toString(a));

                    Score.setText("Your Score is " +abc.getList().get(0).getScores());


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



                        question = question.replace("&quot;", "TEST").replace("&039#;","") + "\n";
                        //      content += "Correct Answers: " + posts.getResults().get(0).getCorrectAnswer() + "\n";
                        //       content += "Incorrect Answers1: " + posts.getResults().get(0).getIncorrectAnswers().get(0) + "\n";
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

//next question request
        return rootview;
    }

}

