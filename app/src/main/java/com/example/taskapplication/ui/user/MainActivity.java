package com.example.taskapplication.ui.user;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.taskapplication.R;
import com.example.taskapplication.base.BaseActivity;
import com.example.taskapplication.listener.ItemListener;
import com.example.taskapplication.ui.details.DetailsPage;
import com.example.taskapplication.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements ItemListener {
    UserViewModel reposViewModel;
    RecyclerView repoRv;
    LinearLayoutManager linearLayoutManager;
    UsersAdapter usersAdapter;
    ItemListener itemListener;
ProgressBar loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniViews();
        repoRv.setVisibility(View.GONE);
        reposViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        reposViewModel.getUsers();
        itemListener = this;

        setRvAdapter();
        reposViewModel.data.observe(this, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> reposetoryData) {
                if (reposetoryData != null) {
                    if (reposetoryData.size() > 0) {
                        loading.setVisibility(View.GONE);
                        repoRv.setVisibility(View.VISIBLE);
                        usersAdapter.notifyList(reposetoryData);
                    }
                }
            }
        });
        reposViewModel.errorMsg.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                if (!msg.isEmpty())
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void iniViews() {
        repoRv = findViewById(R.id.repoRv);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        repoRv.setHasFixedSize(true);
        repoRv.setLayoutManager(linearLayoutManager);
        loading=findViewById(R.id.loading);
    }

    public void setRvAdapter() {
        usersAdapter = new UsersAdapter(reposViewModel.data.getValue(), this);
        repoRv.setAdapter(usersAdapter);
    }

    @Override
    public void onItemClick(User reposetoryData) {
        reposetoryData.setContributors_url(Utils.replaceString(reposetoryData.getContributors_url()));

        startActivity(new Intent(context, DetailsPage.class)

                .putExtra("obj", reposetoryData));
    }
}