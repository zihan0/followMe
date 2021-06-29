package com.follow.me.test;

import com.follow.me.main.FollowMeService;
import com.follow.me.main.clip.Clip;
import com.follow.me.main.exception.InvalidInputException;
import com.follow.me.main.ticks.Ticks;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FollowMeServiceTest {

    private FollowMeService followMeService;

    @Before
    public void setup() {
        followMeService = new FollowMeService();
    }

    @Test(expected = InvalidInputException.class)
    public void testCovertStringToClip_invalidActionInput() throws InvalidInputException {
        String clipString = "clip testClip 2 0.0 1.0 invalid next";
        followMeService.convertToClip(clipString);
    }

    @Test(expected = InvalidInputException.class)
    public void testCovertStringToClip_invalidFormat() throws InvalidInputException {
        String clipString = "clip testClip 2 0.0 none 1.0 next";
        followMeService.convertToClip(clipString);
    }

    @Test
    public void testCovertStringToClip_success() throws InvalidInputException {
        String clipString = "clip testClip 2 0.0 1.0 none next";
        followMeService.convertToClip(clipString);
    }

    @Test(expected = InvalidInputException.class)
    public void testCovertStringToTicks_invalidFormat() throws InvalidInputException {
        String clipString = "ticks";
        followMeService.convertToClip(clipString);
    }

    @Test
    public void testCovertStringToTicks_success() throws InvalidInputException {
        String clipString = "ticks 10";
        followMeService.convertToTicks(clipString);
    }

    @Test
    public void testConvertToAction_success() throws InvalidInputException {
        String action = "none";
        followMeService.covertToActionValue(action);
    }

    @Test(expected = InvalidInputException.class)
    public void testConvertToAction_invalidAction() throws InvalidInputException {
        String action = "123";
        followMeService.covertToActionValue(action);
    }

    @Test
    public void testPrintoutClips_success() throws InvalidInputException {
        List<Clip> clipList = new ArrayList<>();
        clipList.add(followMeService.convertToClip("clip first 2 0.5 0.5 any none"));
        clipList.add(followMeService.convertToClip("clip second 2 1.0 0.0 next none"));
        clipList.add(followMeService.convertToClip("clip third 2 0.8 0.2 previous none"));

        Ticks ticks = new Ticks();
        ticks = followMeService.convertToTicks("ticks 20");
        followMeService.printoutClips(clipList, ticks);
    }

    @Test
    public void testPrintoutClips_oneclip_success() throws InvalidInputException {
        List<Clip> clipList = new ArrayList<>();
        clipList.add(followMeService.convertToClip("clip first 2 0.5 0.5 any none"));

        Ticks ticks = followMeService.convertToTicks("ticks 1");
        followMeService.printoutClips(clipList, ticks);
    }

    @Test
    public void testPrintoutClips_twoclips_success() throws InvalidInputException {
        List<Clip> clipList = new ArrayList<>();
        clipList.add(followMeService.convertToClip("clip hello 2 1.0 0.0 next none"));
        clipList.add(followMeService.convertToClip("clip world 3 1.0 0.0 previous none"));

        Ticks ticks = followMeService.convertToTicks("ticks 10");
        followMeService.printoutClips(clipList, ticks);
    }

    @Test
    public void testPrintoutClips_twoclips_ticks9999_success() throws InvalidInputException {
        List<Clip> clipList = new ArrayList<>();
        clipList.add(followMeService.convertToClip("clip hello 2 1.0 0.0 next none"));
        clipList.add(followMeService.convertToClip("clip world 3 1.0 0.0 previous none"));

        Ticks ticks = followMeService.convertToTicks("ticks 9999");
        followMeService.printoutClips(clipList, ticks);
    }

    @Test
    public void testPrintoutClips_ticksWithFloat_ssuccess() throws InvalidInputException {
        List<Clip> clipList = new ArrayList<>();
        clipList.add(followMeService.convertToClip("clip first 2 0.5 0.5 any none"));
        clipList.add(followMeService.convertToClip("clip second 2 1.0 0.0 next none"));
        clipList.add(followMeService.convertToClip("clip third 2 0.8 0.2 previous none"));

        Ticks ticks = new Ticks();
        ticks = followMeService.convertToTicks("ticks 20 0.5 0.6 0.7 0.2");
        followMeService.printoutClips(clipList, ticks);
    }

    @Test
    public void testPrintoutClips_oneclip_ticksWithFloat_success() throws InvalidInputException {
        List<Clip> clipList = new ArrayList<>();
        clipList.add(followMeService.convertToClip("clip first 2 0.5 0.5 any none"));

        Ticks ticks = followMeService.convertToTicks("ticks 1 0.5 0.6 0.7 0.2");
        followMeService.printoutClips(clipList, ticks);
    }

    @Test
    public void testPrintoutClips_twoclips_ticksWithFloat_success() throws InvalidInputException {
        List<Clip> clipList = new ArrayList<>();
        clipList.add(followMeService.convertToClip("clip hello 2 1.0 0.0 next none"));
        clipList.add(followMeService.convertToClip("clip world 3 1.0 0.0 previous none"));

        Ticks ticks = followMeService.convertToTicks("ticks 10 0.5 0.6 0.7 0.2");
        followMeService.printoutClips(clipList, ticks);
    }

    @Test
    public void testPrintoutClips_twoclips_ticks9999_ticksWithFloat_success() throws InvalidInputException {
        List<Clip> clipList = new ArrayList<>();
        clipList.add(followMeService.convertToClip("clip hello 2 1.0 0.0 next none"));
        clipList.add(followMeService.convertToClip("clip world 3 1.0 0.0 previous none"));

        Ticks ticks = followMeService.convertToTicks("ticks 9999 0.5 0.6 0.7 0.2");
        followMeService.printoutClips(clipList, ticks);
    }

    @Test
    public void testcheckDuplicateAndAddToList_success() throws InvalidInputException {
        List<Clip> clipList = new ArrayList<>();
        clipList.add(followMeService.convertToClip("clip hello 2 1.0 0.0 next none"));
        Clip clipToAdd = followMeService.convertToClip("clip hello 3 1.0 0.0 next none");

        followMeService.checkDuplicateAndAddToList(clipList, clipToAdd);
        assert(clipList.contains(clipToAdd));
        assert(clipList.size() == 1);
    }
}
