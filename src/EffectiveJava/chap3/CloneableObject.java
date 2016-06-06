package EffectiveJava.chap3;


// Usage of Cloneable interface.

// Use by Object class to determine the behavior of clone() method.
// clone() method provides a shallow copy.
// Need to write a deep copy if necessary.

// WARNING: this is atypical use of interface and should not be followed.
public class CloneableObject implements Cloneable{
    public CloneableObject clone() {
        try {
            return (CloneableObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("will never happen");
        }
    }

    // thing to know when implement clone

    // approach 1: super.clone() + fix deep-copy variables.
    // example HashSet.
    // approach 2: super.clone() + regenerate states using high-level method.
    // approach 3: copy via Constructor. e.g. HashSet : new HashSet(Collection s)

    public static class CustomizedHashSet implements Cloneable{
        private Entry[] buckets;

        public static class Entry {
            final Object key;
            Object value;
            Entry next;

            Entry(Object key, Object value, Entry next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }

            // approach 1.1 Recursive -> may stack overflow
            Entry deepCopy() {
                return new Entry(this.key, this.value, this.next == null ? null : this.next.deepCopy());
            }

            // approach 1.2 Iterative -> Preferred way
            Entry deepCopyIterative() {
                // result -> next...
                // ori    /
                Entry head = new Entry(this.key, this.value, this.next);

                // iterator;
                Entry iter = head;
                while (iter.next != null) {
                    iter.next = new Entry(iter.next.key, iter.next.value, iter.next.next);
                    // update result;
                    iter = iter.next;
                }
                return head;
            }
        }

        @Override
        public CustomizedHashSet clone() {
            try {
                CustomizedHashSet set = (CustomizedHashSet) super.clone();
                set.buckets = new Entry[buckets.length];

                for (int i = 0; i < buckets.length; i++) {
                    if (buckets[i] != null) {
                        set.buckets[i] = buckets[i].deepCopyIterative();
                    }
                }
                return set;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError("will never happen");
            }
        }
    }
}
