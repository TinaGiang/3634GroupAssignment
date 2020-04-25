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
   private ArrayList<Items> mList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
View rootview =  inflater.inflate(R.layout.leaderboard, container, false);

       RecyclerView mRecyclerView =(RecyclerView) rootview.findViewById(R.id.rView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        new LeaderboardFragment();
        getList();

        Adapter mAdapter = new Adapter(mList);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


return  rootview;
    }

    public LeaderboardFragment() {
        mList = new ArrayList<>();
        mList.add(new Items(R.drawable.silver, "You", "0", " ", 1));
        mList.add(new Items(R.drawable.bronze, "Kermit", "1040", "",10));
        mList.add(new Items(R.drawable.bronze, "Naruto", "1090", "",9));
        mList.add(new Items(R.drawable.silver, "Abc", "2720", "",8));
        mList.add(new Items(R.drawable.silver, "Ya boi", "3020", "",7));
        mList.add(new Items(R.drawable.silver, "Nani", "3030", "",6));
        mList.add(new Items(R.drawable.silver, "Boris", "4120", "",5));
        mList.add(new Items(R.drawable.gold, "Goku", "5470", "",4));

        mList.add(new Items(R.drawable.gold, "Lelouch", "6020", "",3));
        mList.add(new Items(R.drawable.plat, "My Cat", "8020", "",2));

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
            Items sg = mList.get(i);
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
        Collections.sort(mList, new Comparator<Items>() {
                    public int compare(Items obj1, Items obj2) {

                        return obj1.getSort() - (obj2.getSort());

                    }

                }
        );

    }


    public ArrayList<Items> getList() {
        return mList;
    }
}
