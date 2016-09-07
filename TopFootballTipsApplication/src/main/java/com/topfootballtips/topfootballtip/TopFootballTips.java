package com.topfootballtips.topfootballtip;

import android.app.Application;

import com.backendless.Backendless;
import com.topfootballtips.topfootballtip.constants.RequestParameters;


public class TopFootballTips extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //initialize Backendless
        Backendless.initApp( getBaseContext(),
                RequestParameters.APP_ID,
                RequestParameters.SECRET_KEY,
                RequestParameters.MESSAGING_VERSION);

    }
}
