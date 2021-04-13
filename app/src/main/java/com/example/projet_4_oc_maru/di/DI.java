package com.example.projet_4_oc_maru.di;

import com.example.projet_4_oc_maru.service.DummyMeetingApiService;
import com.example.projet_4_oc_maru.service.MeetingApiService;

public class DI {
    private static final MeetingApiService apiService;

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
