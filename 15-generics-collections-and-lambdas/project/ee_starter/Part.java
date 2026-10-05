/** One line of the parts store. The value is kept as written ("4.7k", "100n", "NE555"). */
public record Part(String partNo, PartType type, String value, String tolerance, int quantity,
                   int reorderLevel, double unitPrice, String location) implements StockItem {

    public static Part fromCsv(String line) {
        String[] f = line.split(",");
        if (f.length != 8) {
            throw new IllegalArgumentException("expected 8 fields, found " + f.length);
        }
        return new Part(f[0], PartType.valueOf(f[1]), f[2], f[3], Integer.parseInt(f[4]),
                Integer.parseInt(f[5]), Double.parseDouble(f[6]), f[7]);
    }

    /** The numeric value in ohms, farads or henries ("4.7k" -> 4700), or NaN for
     *  parts like LEDs and ICs whose "value" is a name. (Your Module 13 SIParser!) */
    public double numericValue() {
        // TODO
        return Double.NaN;
    }
}
