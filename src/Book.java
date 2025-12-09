public class Book extends OfficeSupplies {
    private String publisher;
    private String author; // virgüllü liste gelebilir
    private int pageNumber;

    public Book(double cost, int stock, String releaseDate, String coverTitle,
                String publisher, String author, int pageNumber) {

        super(cost, stock, releaseDate, coverTitle);
        this.publisher = publisher;

        // "A,B,C" → "A, B, C" formatına dönüştür
        this.author = author.replace(",", ", ");
        
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        return "Type: Book\n" +
               super.toString() + "\n" +
               "Publisher: " + publisher + "\n" +
               "Author: " + author + "\n" +
               "Page: " + pageNumber + " pages";
    }
}
