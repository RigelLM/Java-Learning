import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";
        Set<T> result = new Set1L<T>();
        // TODO - fill in body
        for (T e : s) {
            if (this.contains(e)) {
                this.remove(e);
                result.add(e);
            }
        }
        return result;

    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        // TODO - fill in body
        for (T e : s) {
            if (!this.contains(e)) {
                this.add(e);
                s.remove(e);
            }
        }
    }

}