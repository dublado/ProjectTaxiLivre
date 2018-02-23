package com.rsm.yuri.projecttaxilivre;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.rsm.yuri.projecttaxilivre.domain.di.DomainModule;
import com.rsm.yuri.projecttaxilivre.historicchatslist.di.DaggerHistoricChatsListComponent;
import com.rsm.yuri.projecttaxilivre.historicchatslist.di.HistoricChatsListComponent;
import com.rsm.yuri.projecttaxilivre.historicchatslist.di.HistoricChatsListModule;
import com.rsm.yuri.projecttaxilivre.historicchatslist.ui.HistoricChatsListActivity;
import com.rsm.yuri.projecttaxilivre.historicchatslist.ui.HistoricChatsListView;
import com.rsm.yuri.projecttaxilivre.historicchatslist.ui.OnItemClickListener;
import com.rsm.yuri.projecttaxilivre.lib.di.LibsModule;
import com.rsm.yuri.projecttaxilivre.login.di.DaggerLoginComponent;
import com.rsm.yuri.projecttaxilivre.login.di.LoginComponent;
import com.rsm.yuri.projecttaxilivre.login.di.LoginModule;
import com.rsm.yuri.projecttaxilivre.login.ui.LoginView;
import com.rsm.yuri.projecttaxilivre.main.di.DaggerMainComponent;
import com.rsm.yuri.projecttaxilivre.main.ui.MainView;
import com.rsm.yuri.projecttaxilivre.main.di.MainComponent;
import com.rsm.yuri.projecttaxilivre.main.di.MainModule;
import com.rsm.yuri.projecttaxilivre.map.di.DaggerMapComponent;
import com.rsm.yuri.projecttaxilivre.map.di.MapComponent;
import com.rsm.yuri.projecttaxilivre.map.di.MapModule;
import com.rsm.yuri.projecttaxilivre.map.ui.MapFragment;
import com.rsm.yuri.projecttaxilivre.main.ui.MainView;
import com.rsm.yuri.projecttaxilivre.map.ui.MapView;

/**
 * Created by yuri_ on 12/01/2018.
 */

public class TaxiLivreApp extends Application{

    private final static String EMAIL_KEY = "email";
    private LibsModule libsModule;
    private DomainModule domainModule;
    private TaxiLivreAppModule taxiLivreAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initModules();
    }

    private void initModules() {
        libsModule = new LibsModule();
        domainModule = new DomainModule();
        taxiLivreAppModule= new TaxiLivreAppModule(this);
    }

    public static String getEmailKey() {
        return EMAIL_KEY;
    }

    public LoginComponent getLoginComponent(LoginView view) {
        return DaggerLoginComponent
                .builder()
                .taxiLivreAppModule(taxiLivreAppModule)
                .domainModule(domainModule)
                .libsModule(libsModule)
                .loginModule(new LoginModule(view))
                .build();
    }

    public MainComponent getMainComponent(MainView view, FragmentManager manager, MapFragment mapFragment) {
        return DaggerMainComponent
                .builder()
                .taxiLivreAppModule(taxiLivreAppModule)
                .domainModule(domainModule)
                .libsModule(libsModule)
                .mainModule(new MainModule(view, manager, mapFragment))
                .build();
    }

    public MapComponent getMapComponent(Fragment fragment, MapView mapView){
        libsModule.setContext(fragment.getContext());//era libsModule.setFragment(fragment);(ainda nao testado alteracao)

        return DaggerMapComponent
                .builder()
                .taxiLivreAppModule(taxiLivreAppModule)
                .domainModule(domainModule)
                .libsModule(libsModule)
                .mapModule(new MapModule(mapView))
                .build();
    }

    public HistoricChatsListComponent getHistoricChatsListComponent(Context context,
                                                                    HistoricChatsListView view,
                                                                    OnItemClickListener onItemClickListener) {
        libsModule.setContext(context);

        return DaggerHistoricChatsListComponent
                .builder()
                .taxiLivreAppModule(taxiLivreAppModule)
                .domainModule(domainModule)
                .libsModule(libsModule)
                .historicChatsListModule(new HistoricChatsListModule(view, onItemClickListener))
                .build();

    }
}
