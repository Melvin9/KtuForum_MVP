package com.ktu.dev.melvin.ktu_forum.MainScreen.View_Answer.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ktu.dev.melvin.ktu_forum.R;

public class ViewAnswerFragment extends Fragment {

    public ViewAnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_answer, container, false);
    }
}

