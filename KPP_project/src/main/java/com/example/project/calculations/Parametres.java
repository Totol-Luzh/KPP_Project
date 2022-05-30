package com.example.project.calculations;

import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class Parametres {
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Parametres params = (Parametres) obj;

        return Objects.equals(start, params.start) &&
                Objects.equals(end, params.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    private @Nullable Double start;

    private @Nullable Double end;

    public Parametres(@Nullable Double start, @Nullable Double end) {
        if (start == null && end == null)
            throw new IllegalArgumentException("No start and end values!");

        if (start == null)
            throw new IllegalArgumentException("No start value!");

        if (end == null)
            throw new IllegalArgumentException("No end value!");

        this.start = start;
        this.end = end;
    }

    @NotNull
    public Double getStartValue() {
        assert start != null;
        return start;
    }

    @NotNull
    public Double getEndValue() {
        assert end != null;
        return end;
    }

    public void setStartValue(@Nullable Double startValue) {
        this.start = startValue;
    }

    public void setEndValue(@Nullable Double endValue) {
        this.end = endValue;
    }
}
