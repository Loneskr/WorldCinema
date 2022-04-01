package com.example.worldcinema.UI.Collections.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinema.Adapter.CollectionAdapter;
import com.example.worldcinema.R;
import com.example.worldcinema.UI.Collections.Collection;
import com.example.worldcinema.UI.Collections.CollectionCreate;

import java.util.ArrayList;

public class CollectionFragment extends Fragment {

    private ImageView imageView;
    private CollectionAdapter collectionsAdapter;
    private ArrayList<Collection> collections = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    public CollectionFragment() {

    }

    public static CollectionFragment newInstance(String param1, String param2) {
        CollectionFragment fragment = new CollectionFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collections, container, false);
        imageView = view.findViewById(R.id.image_add_collection);
        recyclerView = view.findViewById(R.id.recyclerCollections);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), CollectionCreate.class));
            }
        });
        collections.add(new Collection(R.drawable.love, "Избранное"));
        collections.add(new Collection(R.drawable.history_white, "Когда-нибудь"));
        collections.add(new Collection(R.drawable.airplane, "В поездку"));

        collectionsAdapter = new CollectionAdapter(getContext(), collections);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(collectionsAdapter);
        return view;
    }
}
