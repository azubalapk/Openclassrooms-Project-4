package com.example.projet_4_oc_maru;


import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.projet_4_oc_maru.activities.DetailActivity;
import com.example.projet_4_oc_maru.activities.MainActivity;
import com.example.projet_4_oc_maru.di.DI;
import com.example.projet_4_oc_maru.models.RoomMeeting;
import com.example.projet_4_oc_maru.service.MeetingApiService;
import com.example.projet_4_oc_maru.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    MeetingApiService apiService;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setUp() {
        Intents.init();
        activityRule.getScenario();
        apiService = DI.getMeetingApiService();
    }

    @After
    public void cleanUp() {
        Intents.release();
    }

    @Test
    public void listMeetingContainsAllMeetingInitial() {
        onView(withId(R.id.list_meetings)).check(matches(hasChildCount(apiService.getMeetings().size())));
    }

    @Test
    public void deleteMeeting() {
        final int sizeDelete = apiService.getMeetings().size();
        onView(ViewMatchers.withId(R.id.list_meetings))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(ViewMatchers.withId(R.id.list_meetings)).check(matches(hasChildCount(sizeDelete - 1)));

    }

    @Test
    public void addMeeting() {
        final int sizeAdd = apiService.getMeetings().size();
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.subjectMeeting)).perform(typeText("Logistique")).check(matches(isDisplayed()));

        onView(withId(R.id.btn_time_begin)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.timeBeginMeeting)).check(matches(withText("14:30")));
        onView(withId(R.id.btn_time_end)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(15, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.timeEndMeeting)).check(matches(withText("15:30")));
        onView(withId(R.id.btn_date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 3, 23));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.dateMeeting)).check(matches(withText("2021-03-23")));

        onView(withId(R.id.roomMeetingSpinner)).perform(click());
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String[] myArray = context.getResources().getStringArray(R.array.roomsMeeting_array);
        onData(is(myArray[2])).perform(click());

        onView(withId(R.id.editTextParticipants)).perform(typeText("clovis@gmail.com"), closeSoftKeyboard()).check(matches(isDisplayed()));
        onView(withId(R.id.addParticipant)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.editTextParticipants)).perform(typeText("ana@gmail.com"), closeSoftKeyboard()).check(matches(isDisplayed()));
        onView(withId(R.id.addParticipant)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.chipGroup)).check(matches(hasChildCount(2)));
        onView(withId(R.id.create)).perform(click());
        onView(withId(R.id.list_meetings)).check(matches(hasChildCount(sizeAdd + 1)));

    }

    @Test
    public void filterRoomIsWorking() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Filtrer")).perform(click());
        onView(withText("Sélectionner salle")).perform(click());
        onView(withText("Mario")).perform(click());
        onView(ViewMatchers.withId(R.id.list_meetings)).check(matches(hasChildCount(1)));


    }

    @Test
    public void filterDateIsWorking() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Filtrer")).perform(click());
        onView(withText("Sélectionner date")).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 2, 14));
        onView(withId(android.R.id.button1)).perform(click());
        onView(ViewMatchers.withId(R.id.list_meetings)).check(matches(hasChildCount(1)));

    }

    @Test
    public void filterAllMeetingIsWorking() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        onView(withText("Toutes les réunions")).perform(click());
        onView(ViewMatchers.withId(R.id.list_meetings)).check(matches(hasChildCount(apiService.getMeetings().size())));
    }

    @Test
    public void theRoomIsNotAvailable() {
        final int sizeAdd = apiService.getMeetings().size();
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.subjectMeeting)).perform(typeText("Logistique")).check(matches(isDisplayed()));

        onView(withId(R.id.btn_time_begin)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(13, 30));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.btn_time_end)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(15, 30));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.btn_date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 2, 14));
        onView(withId(android.R.id.button1)).perform(click());


        onView(withId(R.id.roomMeetingSpinner)).perform(click());
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String[] myArray = context.getResources().getStringArray(R.array.roomsMeeting_array);
        onData(is(myArray[6])).perform(click());

        onView(withId(R.id.editTextParticipants)).perform(typeText("clovis@gmail.com"), closeSoftKeyboard()).check(matches(isDisplayed()));
        onView(withId(R.id.addParticipant)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.editTextParticipants)).perform(typeText("ana@gmail.com"), closeSoftKeyboard()).check(matches(isDisplayed()));
        onView(withId(R.id.addParticipant)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.chipGroup)).check(matches(hasChildCount(2)));
        onView(withId(R.id.create)).perform(click());

        assertEquals(apiService.getMeetings().size(), sizeAdd);

        onView(withId(R.id.btn_time_begin)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14, 15));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.btn_time_end)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14, 45));
        onView(withId(android.R.id.button1)).perform(click());

        assertEquals(apiService.getMeetings().size(), sizeAdd);

        onView(withId(R.id.btn_time_begin)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14, 00));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.btn_time_end)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(15, 00));
        onView(withId(android.R.id.button1)).perform(click());

        assertEquals(apiService.getMeetings().size(), sizeAdd);

        onView(withId(R.id.btn_time_begin)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(13, 30));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.btn_time_end)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14, 30));
        onView(withId(android.R.id.button1)).perform(click());

        assertEquals(apiService.getMeetings().size(), sizeAdd);

        onView(withId(R.id.btn_time_begin)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(14, 30));
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.btn_time_end)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(15, 30));
        onView(withId(android.R.id.button1)).perform(click());

        assertEquals(apiService.getMeetings().size(), sizeAdd);

    }

    @Test
    public void launchDetailActivityAndContainMeetingClicked() {
        onView(ViewMatchers.withId(R.id.list_meetings)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        intended(hasComponent(DetailActivity.class.getName()));
        onView(withId(R.id.textViewDetailIdMeet)).check(matches(withText("Numéro : 1")));
        onView(withId(R.id.textViewDetailRoomMeet)).check(matches(withText("Salle : Peach")));
        onView(withId(R.id.textViewDetailDateMeet)).check(matches(withText("Date : 2021-02-14")));
        onView(withId(R.id.textViewDetailSubjectMeet)).check(matches(withText("A propos de : Logistique")));
        onView(withId(R.id.textViewDetailHourMeetBegin)).check(matches(withText("Début :14:00")));
        onView(withId(R.id.textViewDetailHourMeetEnd)).check(matches(withText("Fin :15:00")));
        onView(ViewMatchers.withId(R.id.list_participants)).check(matches(hasChildCount(2)));

    }
}
