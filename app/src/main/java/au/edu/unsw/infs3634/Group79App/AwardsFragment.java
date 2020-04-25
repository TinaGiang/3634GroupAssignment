package au.edu.unsw.infs3634.Group79App;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import au.edu.unsw.infs3634.Group79App.Room.SignupTable;

public class AwardsFragment extends Fragment {
    private int progressStatus = 0;
    LeaderboardFragment abc = new LeaderboardFragment();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getSignupTable();

        View rootview =  inflater.inflate(R.layout.awards, container, false);







        ProgressBar progressBar = (ProgressBar)rootview.findViewById(R.id.progressBar);
        ImageView rocketAward = (ImageView)rootview.findViewById(R.id.Rocket);
        ImageView bronzeAward = (ImageView)rootview.findViewById(R.id.Bronze);
        ImageView silverAward = (ImageView)rootview.findViewById(R.id.Silver);
        ImageView goldAward = (ImageView)rootview.findViewById(R.id.Gold);
        ImageView platiniumAward = (ImageView)rootview.findViewById(R.id.Platinium);

        System.out.println(abc.getList().get(0).getScoresInt());
        TextView Total = (TextView)rootview.findViewById(R.id.Total);
        Total.setText(abc.getList().get(0).getScoresInt()+"/10000");
        TextView rocketAwardTitle = (TextView)rootview.findViewById(R.id.RocketTitle);
        TextView bronzeAwardTitle = (TextView)rootview.findViewById(R.id.BronzeTitle);
        TextView silverAwardTitle = (TextView)rootview.findViewById(R.id.SilverTitle);
        TextView goldAwardTitle = (TextView)rootview.findViewById(R.id.GoldTitle);
        TextView platiniumAwardTitle = (TextView)rootview.findViewById(R.id.PlatTitle);

        //grey out textviews

        rocketAwardTitle.setTextColor(Color.parseColor("#d3d3d3"));
        bronzeAwardTitle.setTextColor(Color.parseColor("#d3d3d3"));
        silverAwardTitle.setTextColor(Color.parseColor("#d3d3d3"));
        goldAwardTitle.setTextColor(Color.parseColor("#d3d3d3"));
        platiniumAwardTitle.setTextColor(Color.parseColor("#d3d3d3"));


        if (abc.getList().get(0).getScoresInt() >= 0) {
            rocketAward.setImageResource(R.drawable.rocket);
            rocketAwardTitle.setTextColor(Color.parseColor("BLACK"));
        }
        if (abc.getList().get(0).getScoresInt() >= 1) {
            bronzeAward.setImageResource(R.drawable.awbro);
            bronzeAwardTitle.setTextColor(Color.parseColor("BLACK"));
        }
        if (abc.getList().get(0).getScoresInt() >= 2500) {
            silverAward.setImageResource(R.drawable.awsil);
            silverAwardTitle.setTextColor(Color.parseColor("BLACK"));
        }
        if (abc.getList().get(0).getScoresInt() >= 5000) {
            goldAward.setImageResource(R.drawable.awgol);
            goldAwardTitle.setTextColor(Color.parseColor("BLACK"));
        }
        if (abc.getList().get(0).getScoresInt() >= 8000) {
            platiniumAward.setImageResource(R.drawable.awpla);
            platiniumAwardTitle.setTextColor(Color.parseColor("BLACK"));
        }

        progressStatus = abc.getList().get(0).getScoresInt();
        progressBar.setProgress(progressStatus);



        return rootview;

    }




    private void getSignupTable() {

        class getSignupTable extends AsyncTask<Void, Void, Void> {



            @Override
            protected Void doInBackground(Void... voids) {

                //   SignupTable signupTable = new SignupTable();


                List<SignupTable> signupTables =  DatabaseClient.getInstance(getContext()).getAppDatabase().signupDao().getDetails();
                int a =  signupTables.get(0).getDBscore();



                System.out.println(a);
                //    LeaderboardFragment abc = new LeaderboardFragment();
                abc.getList().get(0).setScores( Integer.toString(a));

                System.out.println(abc.getList().get(0).getScoresInt());

                return null;
            }


        }

        getSignupTable st = new getSignupTable();
        st.execute();
    }
}
