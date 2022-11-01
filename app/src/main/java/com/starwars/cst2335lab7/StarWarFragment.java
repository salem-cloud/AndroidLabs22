package com.starwars.cst2335lab7;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.ViewGroup;


public class StarWarFragment extends Fragment {

    private static final String ARG_STAR_WARS_LIST_ITEM_OBJ = "star-war-item-obj";
    private List<Result> listResult;

    public StarWarFragment() {
    }

    @SuppressWarnings("unused")
    public static StarWarFragment newInstance(ArrayList<Result> list) {
        StarWarFragment starWarFragment = new StarWarFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_STAR_WARS_LIST_ITEM_OBJ, list);
        starWarFragment.setArguments(args);
        return starWarFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        if (view instanceof RecyclerView) {
            Context ctx = view.getContext();
            RecyclerView view1 = (RecyclerView) view;
            view1.setLayoutManager(new LinearLayoutManager(ctx));
            view1.setAdapter(new MyStarWarRecyclerViewAdapter(listResult));
            view1.addOnItemTouchListener(
                    new RecyclerItemClickListener(ctx, view1 ,new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int pos) {
                            Result res = listResult.get(pos);
                            FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                            ft.replace(R.id.frameLayout, DetailsFragment.newInstance(res));
                            ft.addToBackStack("DetailsFragment");
                            ft.commit();
                        }
                        @Override public void onLongItemClick(View view, int position) {

                        }
                    })
            );
        }
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            listResult = (ArrayList<Result>) getArguments().getSerializable(ARG_STAR_WARS_LIST_ITEM_OBJ);
        }
    }


}