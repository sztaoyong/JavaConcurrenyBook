package chap6;

/**
 * Created by taoyong on 7/2/16.
 */
public enum YongTest {
    MONDAY (1, "MON"),
    TUESDAY(2, "TUE"),;

    private final int idx;
    private final String shorthand;

    YongTest(int idx, String shorthand) {
        this.idx = idx;
        this.shorthand = shorthand;
    }

    public String shortHand() {
        return shorthand;
    }
}
