package au.edu.unsw.infs3634.Group79App;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import au.edu.unsw.infs3634.Group79App.Room.SignupTable;

public class LeaderboardFragment extends Fragment {
    private ArrayList<Items> mList;
    private Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.leaderboard, container, false);

        RecyclerView mRecyclerView = (RecyclerView) rootview.findViewById(R.id.rView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //create arralist and get score from db
        new LeaderboardFragment();

        Adapter mAdapter = new Adapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        return rootview;
    }

    public LeaderboardFragment() {

        mList = new ArrayList<>();
        mList.add(new Items(R.drawable.silver, "You", "0", " ", 1));
        mList.add(new Items(R.drawable.bronze, "Kermit", "540", "", 10));
        mList.add(new Items(R.drawable.bronze, "Naruto", "1090", "", 9));
        mList.add(new Items(R.drawable.silver, "Abc", "2720", "", 8));
        mList.add(new Items(R.drawable.silver, "Ya boi", "3020", "", 7));
        mList.add(new Items(R.drawable.silver, "Nani", "3030", "", 6));
        mList.add(new Items(R.drawable.silver, "Boris", "4120", "", 5));
        mList.add(new Items(R.drawable.gold, "Goku", "5470", "", 4));
        mList.add(new Items(R.drawable.gold, "Lelouch", "6020", "", 3));
        mList.add(new Items(R.drawable.plat, "My Cat", "8020", "", 2));

        // call database and pass in the current score value and sort list
        sortList();



    }

    public ArrayList<Items> getList() {
        return mList;

    }


    private void sortList() {

        class getSignupTable extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                SignupTable signupTable = new SignupTable();

                // add entry into db

                List<SignupTable> signupTables = DatabaseClient.getInstance(getContext()).getAppDatabase().signupDao().getDetails();
                int b = signupTables.get(0).getDBscore();
                mList.get(0).setScores(Integer.toString(b));

                // sort the arraylist
                //assign different star dependent on score value in UI
                         for (int i = 0; i < mList.size(); i++) {
                    int num = (mList.get(i).getScoresInt());

                    if ( num > 100 && num < 2500 ) {
                        mList.get(i).setDrawableId(R.drawable.bronze);
                    }

                    if (num > 2500 && num < 5000 ) {
                        mList.get(i).setDrawableId(R.drawable.silver);
                    }

                    if (num > 5000 && num < 8000) {
                        mList.get(i).setDrawableId(R.drawable.gold);
                    }

                    if (num > 8000) {
                        mList.get(i).setDrawableId(R.drawable.plat);
                    }
                }



                // rank from 1 - 10 based on scores
                int lastScore = -1;
                int ranki = mList.size() + 1;
                int displayRank = ranki;


                //set ranking from 1 to 10
                for (int i = 0; i < mList.size(); i++) {
                    Items sg = mList.get(i);
                    ranki--;

                    if (sg.getScoresInt() > lastScore) {
                        displayRank = ranki;
                    }

                    mList.get(i).setRank(String.valueOf(ranki));
                    lastScore = sg.getScoresInt();

                }

                // if statements to make sure ranking of user's score is accurate
                if (mList.get(0).getScoresInt() > mList.get(1).getScoresInt()) {
                    mList.get(0).setRank(String.valueOf(9));
                    mList.get(1).setRank(String.valueOf(10));
                }

                if (mList.get(0).getScoresInt() > mList.get(2).getScoresInt()) {
                    mList.get(0).setRank(String.valueOf(8));
                    mList.get(2).setRank(String.valueOf(9));
                }

                if (mList.get(0).getScoresInt() > mList.get(3).getScoresInt()) {
                    mList.get(0).setRank(String.valueOf(7));
                    mList.get(3).setRank(String.valueOf(8));
                }

                if (mList.get(0).getScoresInt() > mList.get(4).getScoresInt()) {
                    mList.get(0).setRank(String.valueOf(6));
                    mList.get(4).setRank(String.valueOf(7));
                }

                if (mList.get(0).getScoresInt() > mList.get(5).getScoresInt()) {
                    mList.get(0).setRank(String.valueOf(5));
                    mList.get(5).setRank(String.valueOf(6));
                }

                if (mList.get(0).getScoresInt() > mList.get(6).getScoresInt()) {
                    mList.get(0).setRank(String.valueOf(4));
                    mList.get(6).setRank(String.valueOf(5));
                }

                if (mList.get(0).getScoresInt() > mList.get(7).getScoresInt()) {
                    mList.get(0).setRank(String.valueOf(3));
                    mList.get(7).setRank(String.valueOf(4));
                }

                if (mList.get(0).getScoresInt() > mList.get(8).getScoresInt()) {
                    mList.get(0).setRank(String.valueOf(2));
                    mList.get(8).setRank(String.valueOf(3));
                }

                if (mList.get(0).getScoresInt() > mList.get(9).getScoresInt()) {
                    mList.get(0).setRank(String.valueOf(1));
                    mList.get(9).setRank(String.valueOf(2));
                }

                // sort according to sort order, user at the top then from 1 to 10
                Collections.sort(mList, new Comparator<Items>() {
                    public int compare(Items obj1, Items obj2) {

                        return obj1.getSort() - (obj2.getSort());

                    }

                });

                return null;

           }

        }

        getSignupTable st = new getSignupTable();
        st.execute();

    }
}

