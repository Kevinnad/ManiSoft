package tlite.com.manisoft;

import android.graphics.Bitmap;

public class Comman {
    private static Comman ourInstance = new Comman();

    public static Comman getInstance() {
        return ourInstance;
    }

    private Comman() {
    }

    public static Bitmap bim_bitmap;
}
