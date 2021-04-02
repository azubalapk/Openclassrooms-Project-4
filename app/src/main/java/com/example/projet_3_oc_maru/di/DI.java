package com.example.projet_3_oc_maru.di;

import com.example.projet_3_oc_maru.service.DummyMeetingApiService;
import com.example.projet_3_oc_maru.service.MeetingApiService;

public class DI {
    private static MeetingApiService apiService;

    static {
        apiService = new DummyMeetingApiService();
    }

    public static MeetingApiService getMeetingApiService() {
        return apiService;
    }

    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }

}
