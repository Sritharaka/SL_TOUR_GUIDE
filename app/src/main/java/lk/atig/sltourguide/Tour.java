package lk.atig.sltourguide;

public class Tour {
    public static final String TABLE_NAME = "tours";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_SHORT_DESCRIPTION = "shortdesc";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_IMAGE = "image";

      private int id;
      private String title;
      private String shortdesc;
      private double rating;
      private double price;
      private int image;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_SHORT_DESCRIPTION + " TEXT,"
                    + COLUMN_RATING + " REAL DEFAULT 0,"
                    + COLUMN_PRICE + " REAL DEFAULT 0,"
                    + COLUMN_IMAGE + " INTEGER"
                    + ")";

    public Tour(int id, String title, String shortdesc, double rating, double price, int image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }


    public String getTitle() {
         return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
