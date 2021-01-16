package com.example.projet_3_oc_maru.DI;

import com.example.projet_3_oc_maru.service.DummyMeetingApiService;
import com.example.projet_3_oc_maru.service.MeetingApiService;

public class DI {
    private static MeetingApiService service ;

    static {
        service = new DummyMeetingApiService();
    }

    public static MeetingApiService getMeetingApiService(){
        return service;
    }

    public static MeetingApiService getNewInstanceApiService(){
        return new DummyMeetingApiService();
    }


}
