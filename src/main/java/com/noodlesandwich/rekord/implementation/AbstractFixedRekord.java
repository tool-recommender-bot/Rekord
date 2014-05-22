package com.noodlesandwich.rekord.implementation;

import java.util.Objects;
import com.noodlesandwich.rekord.FixedRekord;
import com.noodlesandwich.rekord.keys.Key;
import com.noodlesandwich.rekord.keys.Keys;
import com.noodlesandwich.rekord.properties.Properties;
import com.noodlesandwich.rekord.serialization.Serializer;
import com.noodlesandwich.rekord.serialization.StringSerializer;

public abstract class AbstractFixedRekord<T> implements FixedRekord<T> {
    private final String name;
    private final LimitedPropertyMap<T> properties;

    protected AbstractFixedRekord(String name, LimitedPropertyMap<T> properties) {
        this.name = name;
        this.properties = properties;
    }

    @Override
    public final String name() {
        return name;
    }

    @Override
    public final <V> V get(Key<? super T, V> key) {
        return key.get(properties);
    }

    @Override
    public final boolean has(Key<? super T, ?> key) {
        return properties.has(key);
    }

    @Override
    public final Keys<T> keys() {
        return properties.keys();
    }

    @Override
    public final Keys<T> acceptedKeys() {
        return properties.acceptedKeys();
    }

    @Override
    public final Properties<T> properties() {
        return properties;
    }

    @Override
    public final <R, E extends Exception> R serialize(Serializer<R, E> serializer) throws E {
        return serializer.serialize(this);
    }

    protected final boolean abstractEquals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof AbstractFixedRekord)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        AbstractFixedRekord<T> that = (AbstractFixedRekord<T>) other;
        return name.equals(that.name) && properties.equals(that.properties);

    }

    protected final int abstractHashCode() {
        return Objects.hash(name, properties);
    }

    @Override
    public final String toString() {
        return serialize(new StringSerializer());
    }
}
