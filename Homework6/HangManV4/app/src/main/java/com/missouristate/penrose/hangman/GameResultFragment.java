package com.missouristate.penrose.hangman;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GameResultFragment extends Fragment {

    private TextView gameResultTV;

    public GameResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setUpFragmentGUI(container);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setUpFragmentGUI(ViewGroup container) {
        if (gameResultTV == null) {
            gameResultTV = new TextView(getActivity());
            gameResultTV.setGravity(Gravity.CENTER);
            container.addView(gameResultTV);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        gameResultTV.setText("Good Luck!");
    }

    public void setResult(String result) {
        gameResultTV.setText(result);
    }
}