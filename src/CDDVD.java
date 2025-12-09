public class CDDVD extends OfficeSupplies {
    private String composer;
    private String songs; // "A,B,C" şeklinde gelebilir

    public CDDVD(double cost, int stock, String releaseDate, String coverTitle,
                 String composer, String songs) {

        super(cost, stock, releaseDate, coverTitle);
        this.composer = composer;

        // "A,B,C" → "A, B, C" formatına dönüştür
        this.songs = songs.replace(",", ", ");
    }

    @Override
    public String toString() {
        return "Type: CDDVD\n" +
               super.toString() + "\n" +
               "Composer: " + composer + "\n" +
               "Songs: " + songs;
    }
}
