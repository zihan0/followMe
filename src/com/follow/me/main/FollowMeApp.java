package com.follow.me.main;

import com.follow.me.main.clip.Clip;
import com.follow.me.main.exception.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FollowMeApp {

    public static void main ( String [] arguments ) {

//        Console con = System.console();
        Scanner scanner = new Scanner(System.in);
        FollowMeService followMeService = new FollowMeService();
        System.out.println("Welcome to the FollowMe, enjoy the service by inputting your commands now :)");
        List<Clip> clips = new ArrayList<>();
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            if (nextLine.equals("exit")) {
                System.out.println("Bye.");
                System.exit(0);
            } else {
                if (nextLine.contains("clip")) {
                    //read and save the clip inputted
                    try {
                        followMeService.checkDuplicateAndAddToList(clips, followMeService.convertToClip(nextLine));
                    } catch (InvalidInputException e) {
                        System.out.println(e.getErrorMessage());
                    }

                } else if (nextLine.contains("ticks")) {
                    //process the clips read, and output ticks
                    if (clips.isEmpty()) {
                        System.out.println("There's no clips in memory.");
                    } else {
                        try {
                            followMeService.printoutClips(clips, followMeService.convertToTicks(nextLine));
                            clips.clear();
                        } catch (InvalidInputException e) {
                            System.out.println(e.getErrorMessage());
                        }
                    }
                } else {
                    System.out.println("Invalid input, please try again.");
                }
            }
        }
    }
}
