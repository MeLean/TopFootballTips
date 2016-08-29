package com.topfootballtips.topfootballtip.api;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import com.topfootballtips.topfootballtip.api.api_models.TipParseObject;
import com.topfootballtips.topfootballtip.constants.ApiEndPoints;
import com.topfootballtips.topfootballtip.constants.RequestParameters;

public interface ApiService {
    @Headers({
            RequestParameters.HEADER_APP_ID,
            RequestParameters.HEADER_SECRET_KEY,
            RequestParameters.HEADER_ACCEPT_JSON
    })
    @GET(ApiEndPoints.TIPS_ENDPOINT)
    Call<TipParseObject> getAllTMatchTips();
}
