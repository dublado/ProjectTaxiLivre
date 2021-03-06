package com.rsm.yuri.projecttaxilivre.map;

import com.rsm.yuri.projecttaxilivre.map.events.MapEvent;

/**
 * Created by yuri_ on 22/01/2018.
 */

public interface MapPresenter {

    void unsubscribe();
    void subscribe();
    void onCreate();
    void onDestroy();

    void onEventMainThread(MapEvent event);

}
