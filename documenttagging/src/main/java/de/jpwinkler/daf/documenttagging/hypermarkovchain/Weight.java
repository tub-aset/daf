package de.jpwinkler.daf.documenttagging.hypermarkovchain;

public class Weight {

    public static final Weight ZERO = new Weight(0, 0);

    private double count;

    private double totalCount;

    public Weight() {
    }

    public Weight(final double count, final double total) {
        super();
        this.count = count;
        totalCount = total;
    }


    public double getCount() {
        return count;
    }

    public void setCount(final double count) {
        this.count = count;
    }

    public double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(final double totalCount) {
        this.totalCount = totalCount;
    }

    public double doubleValue() {
        return count / totalCount;
    }

}
