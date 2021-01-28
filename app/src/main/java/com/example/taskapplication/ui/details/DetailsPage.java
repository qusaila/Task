package com.example.taskapplication.ui.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.taskapplication.R;
import com.example.taskapplication.base.BaseActivity;
import com.example.taskapplication.ui.user.User;

import java.util.ArrayList;

public class DetailsPage extends BaseActivity {
    User user;
    DetailsViewModel detailsViewModel;
    TextView forks, name, watchers;
    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    ContributorsAdapter contributorsAdapter;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        iniView();
    }

    private void iniView() {
        user = (User) getIntent().getExtras().getSerializable("obj");
        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);

        detailsViewModel.getContributorsUrl(user.getContributors_url());
        watchers = findViewById(R.id.watchers);
        name = findViewById(R.id.name);
        forks = findViewById(R.id.forks);
        loading = findViewById(R.id.loading);

        forks.setText(getResources().getString(R.string.forks) + user.getForks_count());
        name.setText(getResources().getString(R.string.name) + user.getName());
        rv = findViewById(R.id.rv);
        rv.setVisibility(View.GONE);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(linearLayoutManager);

        watchers.setText(getResources().getString(R.string.watchers) + user.getWatchers());


        setAdapter();

        detailsViewModel.data.observe(this, new Observer<ArrayList<Contributors>>() {
            @Override
            public void onChanged(ArrayList<Contributors> contributors) {
                if (contributors != null) {
                    if (contributors.size() > 0) {
                        rv.setVisibility(View.VISIBLE);


                        contributorsAdapter.notifyList(contributors);
                    }
                    loading.setVisibility(View.GONE);
                }
            }
        });
    }

    public void setAdapter() {
        contributorsAdapter = new ContributorsAdapter(detailsViewModel.data.getValue(), context);
        rv.setAdapter(contributorsAdapter);
    }
}