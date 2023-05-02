package com.example.bbc.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.VolleyError;
import com.example.bbc.R;
import com.example.bbc.activity.DetailPageActivity;
import com.example.bbc.application.app;
import com.example.bbc.databinding.ItemBottomSheetFragmentBinding;
import com.example.bbc.db.volley.ApiService;
import com.example.bbc.interfaces.BottomSheetInterfaceCallBack;
import com.example.bbc.model.BottomSheetModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private ItemBottomSheetFragmentBinding binding;
    private Long id;
    private RoundedImageView img;
    private TextView movieName;
    private TextView director;
    private TextView time;
    private TextView rate;
    private TextView description;
    private View gotoDetailBtn;
    private ImageView iv_time;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        id = getArguments().getLong("bottomSheet");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ItemBottomSheetFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        ApiService apiService = new ApiService(getContext(), app.TAG);
        apiService.findById(id, new BottomSheetInterfaceCallBack() {
            @Override
            public void onSuccess(BottomSheetModel item) {
                Picasso.get().load(item.getImg()).into(img);
                movieName.setText(item.getName());
                director.setText(item.getDirector());

                if (item.getCategory_name().equals("series")) {
                    iv_time.setImageResource(R.drawable.ic_baseline_folder_24);
                }

                time.setText(item.getTime());
                rate.setText(item.getRate_imdb());
                description.setText(item.getDescription());

                gotoDetailBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), DetailPageActivity.class);

                        intent = ((item.getCategory_name().equals("series") ?
                                intent.putExtra(app.SINGLE_SERIES, item) :
                                intent.putExtra(app.SINGLE_MOVIE, item)));
                        startActivity(intent);
                        dismiss();
                    }
                });
            }

            @Override
            public void onError(VolleyError error) {
                Log.e("ERROR:", error.getMessage());
            }
        });

    }


    private void init() {
        img = binding.ivBottomSheet;
        movieName = binding.tvBottomSheetName;
        director = binding.tvBottomSheetDirector;
        time = binding.tvBottomSheetTime;
        rate = binding.tvBottomSheetRate;
        description = binding.tvBottomSheetDescription;
        gotoDetailBtn = binding.btnBottomSheetDetail;
        iv_time = binding.ivBottomSheetTime;
    }


}
