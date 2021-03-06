package com.rsm.yuri.projecttaxilivre.historicchatslist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.rsm.yuri.projecttaxilivre.R;
import com.rsm.yuri.projecttaxilivre.TaxiLivreApp;
import com.rsm.yuri.projecttaxilivre.chat.ui.ChatActivity;
import com.rsm.yuri.projecttaxilivre.historicchatslist.HistoricChatsListPresenter;
import com.rsm.yuri.projecttaxilivre.historicchatslist.HistoricChatsListPresenterImpl;
import com.rsm.yuri.projecttaxilivre.historicchatslist.entities.User;
import com.rsm.yuri.projecttaxilivre.historicchatslist.ui.adapters.HistoricChatsListAdapter;
import com.rsm.yuri.projecttaxilivre.lib.GlideImageLoader;
import com.rsm.yuri.projecttaxilivre.map.entities.Driver;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoricChatsListActivity extends AppCompatActivity implements HistoricChatsListView, OnItemClickListener {

    @BindView(R.id.container)
    CoordinatorLayout container;
    @BindView(R.id.recyclerViewContacts)
    RecyclerView recyclerView;

    @Inject
    HistoricChatsListAdapter adapter;
    @Inject
    HistoricChatsListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic_chats_list);
        ButterKnife.bind(this);

        setupInjection();

        presenter.onCreate();

        setupRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void setupInjection() {
        TaxiLivreApp app = (TaxiLivreApp) getApplication();
        app.getHistoricChatsListComponent(this, this, this).inject(this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Driver driver) {
        Intent i = new Intent(this, ChatActivity.class);
        i.putExtra(ChatActivity.EMAIL_KEY, driver.getEmail());
        i.putExtra(ChatActivity.STATUS_KEY, driver.getStatus());
        startActivity(i);
    }

    @Override
    public void onItemLongClick(Driver driver) {
        //presenter.removeHistoricChat(driver.getEmail());
    }

    @Override
    public void onHistoricChatAdded(Driver driver) {
        adapter.add(driver);
    }

    @Override
    public void onHistoricChatChanged(Driver driver) {
        adapter.update(driver);
    }

    @Override
    public void onHistoricChatRemoved(Driver driver) {
        adapter.remove(driver);
    }

    @Override
    public void onHistoricChatError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }


}
