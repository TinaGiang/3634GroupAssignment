package au.edu.unsw.infs3634.Group79App;

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

public class LeaderboardFragment extends Fragment {
   private ArrayList<items> mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
View rootview =  inflater.inflate(R.layout.leaderboard, container, false);

       RecyclerView mRecyclerView =(RecyclerView) rootview.findViewById(R.id.rView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        new LeaderboardFragment();
        getList();
        System.out.println(getList().get(0).getScoresInt());

        Adapter mAdapter = new Adapter(mList);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


return  rootview;
    }

    public LeaderboardFragment() {
        mList = new ArrayList<>();
        mList.add(new items(R.drawable.silver, "You", "3041", " "));
        mList.add(new items(R.drawable.bronze, "Kermit", "1041", ""));
        mList.add(new items(R.drawable.bronze, "Naruto", "1087", ""));
        mList.add(new items(R.drawable.silver, "Abc", "2721", ""));
        mList.add(new items(R.drawable.silver, "Ya boi", "3021", ""));
        mList.add(new items(R.drawable.silver, "Nani", "3034", ""));
        mList.add(new items(R.drawable.silver, "Boris", "4121", ""));
        mList.add(new items(R.drawable.gold, "Goku", "5471", ""));

        mList.add(new items(R.drawable.gold, "Lelouch", "6021", ""));
        mList.add(new items(R.drawable.plat, "My Cat", "8021", ""));

        for (int i = 0; i < mList.size(); i++) {
            int num = (mList.get(i).getScoresInt());

            if (num < 2500) {
                mList.get(i).setDrawableId(R.drawable.bronze);
            }

            if (num < 5000 && num >= 2500) {
                mList.get(i).setDrawableId(R.drawable.silver);
            }

            if (num >= 5000 && num < 8000) {
                mList.get(i).setDrawableId(R.drawable.gold);
            }

            if (num >= 8000) {
                mList.get(i).setDrawableId(R.drawable.plat);
            }
        }

        int lastScore = -1;
        int ranki = mList.size() + 1;
        int displayRank = ranki;


        //set ranking from 1 to 10
        for (int i = 0; i < mList.size(); i++) {
            items sg = mList.get(i);
            ranki--;


            if (sg.getScoresInt() > lastScore) {
                displayRank = ranki;
            }

            mList.get(i).setRank(String.valueOf(ranki));
            lastScore = sg.getScoresInt();


        }

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


        // sort from highest score to lowest
        Collections.sort(mList, new Comparator<items>() {
                    public int compare(items obj1, items obj2) {

                        return obj2.getScoresInt() - (obj1.getScoresInt());

                    }

                }
        );

    }


    public ArrayList<items> getList() {
        return mList;
    }
}
