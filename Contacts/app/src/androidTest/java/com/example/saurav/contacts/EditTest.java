package com.example.saurav.contacts;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by saurav on 3/4/18.
 */
public class EditTest {
    @Test
    public void setUp1() throws Exception {
    }

    public ActivityTestRule<Edit> testRule = new ActivityTestRule<Edit>(Edit.class);
    private Edit edit=null;
    @Before
    public void setUp() throws Exception {
        edit = testRule.getActivity();
    }

    @Rule
    public void testLaunch(){
        View view = edit.findViewById(R.id.p);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        edit=null;
    }

}