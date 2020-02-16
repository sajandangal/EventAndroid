package com.example.eventscheduler;

import androidx.test.rule.ActivityTestRule;

import com.example.eventscheduler.activity.DashboardActivity;
import com.example.eventscheduler.activity.EventDetailActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class EventClick {
    @Rule
    public ActivityTestRule<EventDetailActivity> activity_productdetailActivityTestRule = new ActivityTestRule<>(EventDetailActivity.class);
    @Test
    public void ProductClickTest() {
        onView(withId(R.id.productimg)).perform(click());
    }
}
