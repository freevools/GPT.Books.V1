//не используется в программе
public class Randomizer {
    private long seed;
    public Randomizer() {
        this.seed = System.currentTimeMillis();
    }
    public int nextInt(int min, int max) {
        long a = 1664525;
        long c = 1013904223;
        long m = (long) Math.pow(2, 32);
        seed = (a * seed + c) % m;
        return min + (int) (seed % (max - min + 1));
    }
}
