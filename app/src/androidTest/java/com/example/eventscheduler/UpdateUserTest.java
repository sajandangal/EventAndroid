package com.example.eventscheduler;

import androidx.test.rule.ActivityTestRule;

import com.example.eventscheduler.activity.DashboardActivity;
import com.example.eventscheduler.activity.UserProfileActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class UpdateUserTest {
    @Rule
    public ActivityTestRule<UserProfileActivity> activity_mainActivityMenuTestRule = new ActivityTestRule<>(UserProfileActivity.class);
    @Test
    public void UpdateUserTest() {
        onView(withId(R.id.btnUpdate)).perform(click());
    }
}
