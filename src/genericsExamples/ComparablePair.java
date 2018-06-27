package genericsExamples;


/*
 * R-Click / Control+Enter
 * generate
 *  - toString
 *  - equals & hashCode
 *  - getter/setter if needed


 */

public class ComparablePair<S extends Comparable<S>, T extends Comparable<T>> implements Comparable<ComparablePair<S, T>>  {
    private S first;
    private T second;

    public ComparablePair() {
        first = null;
        second = null;
    }

    public ComparablePair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    public S getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(S newValue) {
        first = newValue;
    }
    public void setSecond(T newValue) {
        second = newValue;
    }


    // COMPERABLE METHODS
    @Override
    public int compareTo(ComparablePair<S, T> o) {
        int compare_first = this.getFirst().compareTo(o.getFirst());
        if (compare_first != 0){
            return compare_first;
        } else{
            int compare_second = this.getSecond().compareTo(o.getSecond());
            return compare_second;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComparablePair<?, ?> pair = (ComparablePair<?, ?>) o;

        if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
        return second != null ? second.equals(pair.second) : pair.second == null;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComparablePair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    //public static ComparablePair getMin(ComparablePair pair1, ComparablePair pair2){ // Raw-Type solution, could work too
    public static <S extends Comparable<S>, T extends Comparable<T>> ComparablePair<S, T> getMin(ComparablePair<S, T> p1, ComparablePair<S, T> p2) {
        if (p1.compareTo(p2) <= 0){
            return p1;
        }
        return p2;
    }

    // Incorrect solution, does not work on LocalDate because LocalDate does not implement
    // Comparable<LocalDate> but ChronoLocalDate which in turn extends Comparable<ChronoLocalDate>!
    public static <S extends Comparable<S>, T extends Comparable<T>> ComparablePair<S, T> getMax(ComparablePair<S, T> p1, ComparablePair<S, T> p2) {
        if (p1.compareTo(p2) > 0) {
            return p1;
        } else {
            return p2;
        }
    }
}
