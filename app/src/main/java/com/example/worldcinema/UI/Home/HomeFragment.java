//package com.example.worldcinema.UI.Home;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.PagerSnapHelper;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.SnapHelper;
//
//import com.example.worldcinema.Network.Service.IApiService;
//import com.example.worldcinema.R;
//import com.google.android.material.textfield.TextInputEditText;
//
//import java.util.ArrayList;
//
//public class HomeFragment extends Fragment {
//
//    private static final String TAG = "HomeFragment";
//    private TextInputEditText editEmail, editPassword;
//    private ArrayList<MovieCoverResponse> moveCoverResponses;
//    private ArrayList<MovieResponse>movieResponses;
//    private RecyclerView recyclerView;
//    private LinearLayoutManager linearLayoutManager;
//    private CoversAdapter coversAdapter;
//    private MovieAdapter movieAdapter;
//
//    private boolean isSignIn = false;
//    IApiService service = MovieCoverHandler.getInstance().getService();
//    IApiService serviceMovie = MovieHandler.getInstance().getService();
//    public HomeFragment() {
//
//    }
//
//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_fome_page, container, false);
//        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
//        recyclerView = view.findViewById(R.id.recyclerView);
//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
//        getMovies();
//        return view;
//    }
//}
