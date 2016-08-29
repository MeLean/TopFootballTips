package com.topfootballtips.topfootballtip.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.style.TabStopSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.topfootballtips.topfootballtip.R;

public class MatchesListFragment extends ListFragment  {
    public MatchesListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false);
    }
}

