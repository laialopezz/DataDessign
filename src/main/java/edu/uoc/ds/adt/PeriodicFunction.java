package edu.uoc.ds.adt;

public class PeriodicFunction {
    public int mod(int x) {
        int mod = x % 4;  // Mod 4
        return mod * mod;  // ^2
    }
}
