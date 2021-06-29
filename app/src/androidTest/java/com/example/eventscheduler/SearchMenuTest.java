package com.example.eventscheduler;

import androidx.test.rule.ActivityTestRule;

import com.example.eventscheduler.activity.DashboardActivity;


import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class SearchMenuTest {

    @Rule
    public ActivityTestRule<DashboardActivity> activity_mainActivityMenuTestRule = new ActivityTestRule<>(DashboardActivity.class);
    @Test
    public void SearchMenuTest() {
        onView(withId(R.id.action_search)).perform(click());
    }
}

