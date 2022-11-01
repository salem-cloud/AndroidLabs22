package com.starwars.cst2335lab7;
import android.widget.TextView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private Result ress;

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup con,
                             Bundle state) {
        View fragmentView = inf.inflate(R.layout.fragment_details, con, false);
        TextView tvName = (TextView)fragmentView.findViewById(R.id.name_char);
        TextView tvHeight = (TextView)fragmentView.findViewById(R.id.height_char);
        TextView tvMass = (TextView)fragmentView.findViewById(R.id.mass_char);
        tvName.setText(ress.getName());
        tvHeight.setText(ress.getHeight());
        tvMass.setText(ress.getMass());
        return  fragmentView;
    }

    public static DetailsFragment newInstance() {
        DetailsFragment frg = new DetailsFragment();
        return frg;
    }

    public static DetailsFragment newInstance(Result result) {
        DetailsFragment frg = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, result);
        frg.setArguments(args);
        return frg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ress = (Result) getArguments().getSerializable(ARG_PARAM1);
        }
    }

}