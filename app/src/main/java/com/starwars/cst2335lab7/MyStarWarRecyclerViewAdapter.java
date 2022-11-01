package com.starwars.cst2335lab7;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.starwars.cst2335lab7.databinding.FragmentItemBinding;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;


public class MyStarWarRecyclerViewAdapter extends RecyclerView.Adapter<MyStarWarRecyclerViewAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textViewContent;
        public Result resItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            textViewContent = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewContent.getText() + "'";
        }
    }

    private final List<Result> resValues;
    public MyStarWarRecyclerViewAdapter(List<Result> itemResults) {
        resValues = itemResults;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int typeView) {
        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parentViewGroup.getContext()), parentViewGroup, false));
    }

    @Override
    public int getItemCount() {
        return resValues.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int pos) {
        viewHolder.resItem = resValues.get(pos);
        viewHolder.textViewContent.setText(resValues.get(pos).getName());
    }




}