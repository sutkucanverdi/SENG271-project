public abstract class OfficeSupplies extends Item {
    protected String releaseDate;
    protected String coverTitle;

    public OfficeSupplies(double cost, int stock,
                          String releaseDate, String coverTitle) {

        super(cost, stock);
        this.releaseDate = releaseDate;
        this.coverTitle = coverTitle;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "Release Date: " + releaseDate + "\n" +
               "Title: " + coverTitle;
    }
}
