package com.edutech.javaee.s05.e01;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author nahum
 */
public class Runner {
    public Runner(String OraleNoMeLaSabia) {
        System.out.println("No pos guau!");
    }
    public Runner() {
        this("white");
    }

    private static boolean test(Predicate<Integer> p) {
        return p.test(5);
    }
    
    public static void main(String argv[])  {
        int[] array = {6,9,8};
        List<Integer> list = new ArrayList<Integer>();
        list.add (array[0]);
        list.add (array[2]);
        list.set (1, array[1]);
        list.remove (0);
        System.out.println(list);
        
        //
        int x = 5, j = 0;
        OUTER: for (int i = 0; i < 3;)
            INNER: do {
                i++; x++;
                if (x > 10) break INNER;
                x+=4;
                j++;                
            } while (j <= 2);
        System.out.println(x);        

        //16
        try {
            throw new IllegalArgumentException();            
        }
        catch(RuntimeException ex) {
            System.out.println("Runtime, seriously?");
        }
        
        //17
        System.out.println(LocalDate.of(2015, Calendar.APRIL, 1));
        System.out.println(LocalDate.of(2015, Month.APRIL, 1));
        System.out.println(LocalDate.of(2015, 3, 1));
        System.out.println(LocalDate.of(2015, 3, 1));
        //System.out.println(new LocalDate(2015, 4, 1));
        
        //19
        System.out.println( test(i -> i == 5) );
        //test(i -> {i == 5;});
        System.out.println( test(i -> (i) == 5) );
        //test((int i) -> i == 5);
        //test((int i) -> { return i == 5; } );
        System.out.println( test((i) -> {return i == 5;}) );
        
        // 
        String a_b = "s";
        System.out.println(a_b);
    }
}

class _C {
    private static int $;
}

interface Animal { public default String getName(){ return null; } }; 

interface Animal2 { public String getName(); }; 
