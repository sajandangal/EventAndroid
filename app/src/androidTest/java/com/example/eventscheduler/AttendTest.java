package com.example.eventscheduler;

import androidx.test.rule.ActivityTestRule;

import com.example.eventscheduler.activity.AddEventActivity;
import com.example.eventscheduler.activity.DashboardActivity;
import com.example.eventscheduler.activity.EventDetailActivity;
import com.example.eventscheduler.activity.LoginActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AttendTest {

    @Rule
    public ActivityTestRule<EventDetailActivity> activity_mainActivityMenuTestRule = new ActivityTestRule<>(EventDetailActivity.class);
    @Test
    public void AttendTest() {
        onView(withId(R.id.atndBtn)).perform(click());
    }
}