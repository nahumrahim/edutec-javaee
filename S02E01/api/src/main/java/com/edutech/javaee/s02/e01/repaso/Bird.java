package com.edutech.javaee.s02.e01.repaso;

/**
 *
 * @author nahum
 */
public class Bird implements Flier, Singer {

    @Override
    public void fly() {
        System.out.println("Im a bird and I'm fling");
    }

    @Override
    public void sing() {
        System.out.println("Im a bird and I'm singing");
    }
    
    

}
