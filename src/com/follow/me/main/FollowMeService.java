package com.follow.me.main;

import com.follow.me.main.action.Action;
import com.follow.me.main.clip.Clip;
import com.follow.me.main.exception.InvalidInputException;
import com.follow.me.main.ticks.Ticks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class FollowMeService {

    final private static String clipFormat = "clip <name> <ticks to play> <follow chance1> <follow chance2> <action1> <action2>";
    final private static String ticksFormat = "ticks <ticks to play> <optional list for chance for clips to play>";

    public Clip convertToClip (String clipAsString) throws InvalidInputException {
        String[] clip = clipAsString.split(" ");
        if (clip[0].equals("clip") && clip.length == 7) {
            try {
                Clip clipObj = new Clip();
                clipObj.setName(clip[1]);
                clipObj.setTicks(Integer.valueOf(clip[2]));
                clipObj.setChance1(Float.valueOf(clip[3]));
                clipObj.setChance2(Float.valueOf(clip[4]));
                clipObj.setAction1(covertToActionValue(clip[5]));
                clipObj.setAction2(covertToActionValue(clip[6]));
                return clipObj;
            } catch (Exception e) {
                throw new InvalidInputException(e.getMessage());
            }
        }
        else {
            throw new InvalidInputException("Invalid clip command format" +
                    "correct format: " + clipFormat);
        }
    }

    public Ticks convertToTicks (String ticksAsString) throws InvalidInputException {
        String[] ticks = ticksAsString.split(" ");
        Integer length = ticks.length;
        if (ticks[0].equals("ticks") && length > 1) {
            Ticks ticksObj = new Ticks();
            ticksObj.setCount(Integer.valueOf(ticks[1]));
            if (length > 2) {
                List<Float> chance = new ArrayList<>();
                for (int i = 2; i < length - 1; i ++) {
                    chance.add(Float.valueOf(ticks[i]));
                }
            }
            return ticksObj;
        } else {
            throw new InvalidInputException("Invalid ticks command format." +
                    "correct format: " + ticksFormat);
        }
    }

    public void printoutClips (List<Clip> clips, Ticks ticks) {
        int count = 0;     //total output
        if (ticks.getCount() < clips.get(0).getTicks()) {
            for (int i = 1; i < clips.get(0).getTicks(); i ++, count ++) {
                System.out.println(clips.get(0).getName());
            }
        } else {
            Clip currentClip = clips.get(0);
            int countForCurrentClip = currentClip.getTicks();       //count for current clip to play
            while(count < ticks.getCount()) {
                while (countForCurrentClip > 0 && count < ticks.getCount()) {
                    System.out.println(currentClip.getName());
                    count ++;
                    countForCurrentClip --;
                }
                currentClip = nextClip(currentClip, clips);
                countForCurrentClip = currentClip.getTicks();
            }
        }
    }

    public Action covertToActionValue (String action) throws InvalidInputException {
        switch (action) {
            case "none":
                return Action.NONE;
            case "any":
                return Action.ANY;
            case "other":
                return Action.OTHER;
            case "next":
                return Action.NEXT;
            case "previous":
                return Action.PREVIOUS;
            default:
                System.out.println("action " + action + "not found.");
                throw new InvalidInputException("Invalid action.");
        }
    }

    private Clip nextClip(Clip currentClip, List<Clip> clipList) {

        float chance = ThreadLocalRandom.current().nextFloat();
        int length = clipList.size();
        int index = clipList.indexOf(currentClip);
        List<Clip> listForShuffle = new ArrayList<>(clipList);

        //choose action based on chance
        Action next = chance < (currentClip.getChance1()/(currentClip.getChance1() + currentClip.getChance2())) ? currentClip.getAction1() : currentClip.getAction2();
        switch (next) {
            case NONE:
                return clipList.get(index);
            case ANY:
                //shuffle clip list, and pick the first one
                Collections.shuffle(listForShuffle);
                return listForShuffle.get(0);
            case OTHER:
                //shuffle clip list without the current one, and pick the first one
                listForShuffle.remove(index);
                Collections.shuffle(listForShuffle);
                return listForShuffle.get(0);
            case NEXT:
                if (index == length - 1) {
                    return clipList.get(0);
                } else {
                    return clipList.get(index + 1);
                }
            case PREVIOUS:
                if (index == 0) {
                    return clipList.get(length - 1);
                } else {
                    return clipList.get(index - 1);
                }
            default:
                return null;
        }
    }

    public void checkDuplicateAndAddToList(List<Clip> clips, Clip clipToAdd) {
        for(Clip clip:clips) {
            if(clip.getName().equals(clipToAdd.getName())) {
                clips.remove(clip);
                break;
            }
        }
        clips.add(clipToAdd);
    }
}
