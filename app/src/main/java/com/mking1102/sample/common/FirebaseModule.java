package com.mking1102.sample.common;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class FirebaseModule {

//    @Provides
//    @Singleton
//    FirebaseAuth provideFirebaseAuth() {
//        return FirebaseAuth.getInstance();
//    }

    @Provides
    @Singleton
    FirebaseAnalytics provideFirebaseAnalytics(@ApplicationContext Context context){
        return  FirebaseAnalytics.getInstance(context);
    }
}
