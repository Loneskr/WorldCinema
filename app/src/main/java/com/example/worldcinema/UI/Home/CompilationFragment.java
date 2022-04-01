package com.example.worldcinema.UI.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.worldcinema.R;

public class CompilationFragment extends Fragment {
    public CompilationFragment() {
        // Required empty public constructor
    }
    public static CompilationFragment newInstance(String param1, String param2) {
        CompilationFragment fragment = new CompilationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comp, container, false);
    }
}
