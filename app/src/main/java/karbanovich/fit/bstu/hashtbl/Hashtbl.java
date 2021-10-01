package karbanovich.fit.bstu.hashtbl;

import android.content.Context;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.IntStream;

public class Hashtbl {

    private static final int numBucket = 10;
    private static String delimiter = "#-#";
    private static int size;



    private static int getBucketIndex(String k) {
        return Math.abs(k.hashCode()) % numBucket;
    }

    public static void add(Context context, String key, String value) {
        int index = Hashtbl.getBucketIndex(key);
        int symbolPosition = index * 21 + 2 * index;

        String line = key + delimiter + value + delimiter;
        String resultLine = line + String.join("", Collections.nCopies((21 - line.length()), " "));

        FileHelper.writeLine(context, resultLine, symbolPosition, key, index);
    }

    public static String get(Context context, String key) {
        int index = Hashtbl.getBucketIndex(key);
        int symbolPosition = index * 21 + 2 * index;

        return FileHelper.getLine(context, key, symbolPosition);
    }
}
