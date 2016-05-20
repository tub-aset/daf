package de.jpwinkler.daf.dafcore.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class WeightedRandomNumberGenerator {

    private final Random r = new Random();

    private final Set<Integer> outputs = new HashSet<>();

    private int sum = 0;
    private int num = 0;
    private float target;

    public void addOutput(final int o) {
        outputs.add(o);
    }

    public void setTarget(final float target) {
        this.target = target;
    }

    private float average() {
        return (float) sum / num;
    }

    public int next() {
        List<Integer> choices;
        if (average() < target) {
            choices = outputs.stream().filter(i -> i > target).collect(Collectors.toList());
        } else {
            choices = outputs.stream().filter(i -> i < target).collect(Collectors.toList());
        }
        if (choices.isEmpty()) {
            choices = new ArrayList<>(outputs);
        }
        final int result = choices.get(r.nextInt(choices.size()));
        sum += result;
        num++;
        return result;
    }

    public static void main(final String[] args) {
        final WeightedRandomNumberGenerator rng = new WeightedRandomNumberGenerator();
        rng.addOutput(1);
        rng.addOutput(2);
        rng.addOutput(3);
        rng.addOutput(4);
        rng.setTarget(3.67f);
        for (int i = 0; i < 1000; i++) {
            final int next = rng.next();
            System.out.println(next);
        }

        System.out.println(rng.average());
    }
}
