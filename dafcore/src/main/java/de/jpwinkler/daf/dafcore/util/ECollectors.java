package de.jpwinkler.daf.dafcore.util;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

public final class ECollectors {

    private static final class ToEListCollector<T> implements Collector<T, EList<T>, EList<T>> {
        @Override
        public Supplier<EList<T>> supplier() {
            return BasicEList::new;
        }

        @Override
        public BiConsumer<EList<T>, T> accumulator() {
            return EList::add;
        }

        @Override
        public BinaryOperator<EList<T>> combiner() {
            return (left, right) -> {
                left.addAll(right);
                return left;
            };
        }

        @Override
        public Function<EList<T>, EList<T>> finisher() {
            return i -> i;
        }

        @Override
        public Set<java.util.stream.Collector.Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Collector.Characteristics.IDENTITY_FINISH));
        }
    }

    private ECollectors() {
    }

    public static <T> Collector<T, ?, EList<T>> toEList() {
        return new ToEListCollector<T>();
    }

}
