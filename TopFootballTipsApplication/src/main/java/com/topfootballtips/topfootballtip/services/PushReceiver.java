package com.topfootballtips.topfootballtip.services;

import com.backendless.push.BackendlessBroadcastReceiver;
import com.backendless.push.BackendlessPushService;

public class PushReceiver extends BackendlessBroadcastReceiver {
    @Override
    public Class<? extends BackendlessPushService> getServiceClass() {
        return PushService.class;
    }
}