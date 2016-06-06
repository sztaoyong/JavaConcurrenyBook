package EffectiveJava.chap3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by taoyong on 5/22/16.
 */
public class OverrideHashCodeWhenOverrideEquals {
    public static class PhoneNumber {
        private final short areacode;
        private final short prefix;
        private final short linenumber;

        public PhoneNumber(short areacode, short prefix, short linenumber) {
            this.areacode = areacode;
            this.prefix = prefix;
            this.linenumber = linenumber;
        }

        // while it is legal to override only equals() method.
        // it is not correct though.
        @Override
        public boolean equals(Object o) {
            if ( this == o ) return true;
            if (!(o instanceof PhoneNumber)) return false;
            PhoneNumber pn = (PhoneNumber) o;
            return this.areacode == pn.areacode && this.prefix == pn.prefix && this.linenumber == pn.linenumber;
        }
    }

    public static void main(String[] args) {
        PhoneNumber number = new PhoneNumber((short)123, (short)456, (short)7890);
        System.out.println("Hash code of number: " + number.hashCode() );
        Map<PhoneNumber, String> phonebook = new HashMap<>();
        phonebook.put(number, "Taoyong");

        PhoneNumber number2 = new PhoneNumber((short)123, (short)456, (short)7890);
        System.out.println("Hash code of number: " + number2.hashCode() );
        System.out.println("Can you find my number again? " + phonebook.containsKey(number2));
    }
}
