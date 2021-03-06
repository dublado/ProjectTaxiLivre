package com.rsm.yuri.projecttaxilivre.domain.di;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rsm.yuri.projecttaxilivre.domain.FirebaseAPI;
import com.rsm.yuri.projecttaxilivre.map.models.AreasHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yuri_ on 12/01/2018.
 */
@Module
public class DomainModule {

    @Provides
    @Singleton
    FirebaseAPI providesFirebaseAPI(DatabaseReference databaseReference, StorageReference storageReference, AreasHelper areasHelper) {
        return new FirebaseAPI(databaseReference, storageReference, areasHelper);
    }

    @Provides
    @Singleton
    DatabaseReference providesFirebase() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Provides
    @Singleton
    StorageReference providesStorageReference(){
        return FirebaseStorage.getInstance().getReference();
    }

    @Provides
    @Singleton
    AreasHelper providesAreasHelper(){
        return new AreasHelper();
    }

}
