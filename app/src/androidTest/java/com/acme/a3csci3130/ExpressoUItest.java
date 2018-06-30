package com.acme.a3csci3130;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasToString;

/**
 * By Arazoo Hoseyni on June 30, 2018.
 */

public class ExpressoUItest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void test(){
        createTest();
        updateTest();
        readtest();
        deletetest();
    }

    /**
     * NOTE: no data should be stored in the firebase at this point in the project.
     */

    /**
     * Checks for the creation of the contact 'arazoo'
     */

    public void createTest() {
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.Name)).perform(typeText("arazoo"),closeSoftKeyboard());
        onView(withId(R.id.businessnumber)).perform(typeText("98765"),closeSoftKeyboard());
        onView(withId(R.id.primarybusiness)).perform(typeText("Fisher"),closeSoftKeyboard());
        onView(withId(R.id.address)).perform(typeText("211 apt halifax"), closeSoftKeyboard());
        onView(withId(R.id.province)).perform(typeText("NS"),closeSoftKeyboard());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.listView)).check(matches(isDisplayed()));


    }

    /**
     * It checks for the update data from 'arazoo' to 'azai'
     */

    public void updateTest(){
        //onView(withId(R.id.listView));
        SystemClock.sleep(2000);// waits for the listview to load
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.Name)).perform(replaceText("azai"),closeSoftKeyboard());
        onView(withId(R.id.updateButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(withText("azai")));


    }

    /**
     * Checks the reading of data from the database
     */


    public void readtest(){
        SystemClock.sleep(2000);// waits for the listview to load
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.Name)).check(matches(withText("azai")));
        onView(withId(R.id.businessnumber)).check(matches(withText("98765")));
        onView(withId(R.id.primarybusiness)).check(matches(withText("Fisher")));
        onView(withId(R.id.address)).check(matches(withText("211 apt halifax")));
        onView(withId(R.id.province)).check(matches(withText("NS")));
        pressBack();
    }

    /**
     * Checks for the deletion of the contact from the listview
     */


    public void deletetest() {
        SystemClock.sleep(2000);
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
        onView(withId(R.id.listView)).check(matches(not(isDisplayed())));
    }


}

