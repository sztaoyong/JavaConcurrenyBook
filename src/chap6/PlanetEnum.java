package chap6;

/**
 * Created by taoyong on 7/2/16.
 *
 * 1. Enum vs Int/String constants: fields & methods & abstractions
 */
public enum PlanetEnum {
    // enum constants: separated by comma
    // the numbers in parathesis are parameters passed to its constructor.
    MERCURY (3.303e+23, 2.4397e6),
    VENUS   (4.869e+24, 6.0518e6),
    EARTH   (5.976e+24, 6.37814e6),
    MARS    (6.421e+23, 3.3972e6),
    JUPITER (1.9e+27,   7.1492e7),
    SATURN  (5.688e+26, 6.0268e7),
    URANUS  (8.686e+25, 2.5559e7),
    NEPTUNE (1.024e+26, 2.4746e7),
    PLUTO   (1.27e+22,  1.137e6);

    // declare field type...
    // To associate data with Enum constants, declare instance fields and write a constructor.
    private final double mass;   // in kilograms
    private final double radius; // in meters
    private final double surfaceGravity;

    // NOTE - this constructor modifier is package-level.
    // cannot use it from other places. (it will show this has a private access or Enum type cannot be instantiated).
    // it is used internally to create enum constants.
    PlanetEnum(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        this.surfaceGravity = G * mass / (radius * radius);
    }
    public double mass()   { return mass; }
    public double radius() { return radius; }

    // universal gravitational constant  (m3 kg-1 s-2)
    public static final double G = 6.67300E-11;

    public double surfaceGravity() {
        return surfaceGravity;
    }
    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
}