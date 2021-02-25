package com.example.projet_3_oc_maru.di;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.projet_3_oc_maru.service.DummyMeetingApiService;
import com.example.projet_3_oc_maru.service.MeetingApiService;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DI {
    private static MeetingApiService service ;

    static { service = new DummyMeetingApiService(); }

    public static MeetingApiService getMeetingApiService(){
        return service;
    }

    public static MeetingApiService getNewInstanceApiService(){
        return new DummyMeetingApiService();
    }

}
