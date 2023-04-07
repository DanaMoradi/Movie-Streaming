package com.example.bbc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbc.databinding.ItemMainGenreBinding;
import com.example.bbc.model.GenreModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GenreMainAdapter extends RecyclerView.Adapter<GenreMainAdapter.GenreViewHolder> {

    private ItemMainGenreBinding binding;
    List<GenreModel> list;


    public GenreMainAdapter(List<GenreModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemMainGenreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GenreViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        holder.bindData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class GenreViewHolder extends RecyclerView.ViewHolder {

        ItemMainGenreBinding view;
        

        public GenreViewHolder(@NonNull ItemMainGenreBinding v) {
            super(v.getRoot());
            view = v;
        }

        void bindData(GenreModel item) {
            view.tvGenreTittle.setText(item.getName());
            Picasso.get().load(item.getImg()).into(view.ivMainGenre);

            //Click Listener For Genre Items
            view.ivMainGenre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), item.getName() + "", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}
