package com.company;

import java.util.ArrayList;
import java.util.Random;

public class RandomOperations {


    public static Object randomElementFromList(ArrayList objects){
        Random random = new Random();
        return objects.get(random.nextInt(objects.size()));
    }
}
