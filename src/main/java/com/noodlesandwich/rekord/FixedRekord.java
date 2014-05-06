package com.noodlesandwich.rekord;

import com.noodlesandwich.rekord.serialization.RekordSerializer;
import org.pcollections.PSet;

public interface FixedRekord<T> {
    String name();

    <V> V get(Key<? super T, V> key);

    boolean containsKey(Key<T, ?> key);

    PSet<Key<? super T, ?>> keys();

    PSet<Key<? super T, ?>> acceptedKeys();

    <A, R> R serialize(RekordSerializer<A, R> serializer);

    <A> void accumulateIn(RekordSerializer.Serializer<A> serializer);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

    @Override
    String toString();
}