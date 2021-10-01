package karbanovich.fit.bstu.hashtbl;

import android.content.Context;

import java.io.File;
import java.io.RandomAccessFile;

public class FileHelper {
    private final static String FILE_NAME ="Lab.txt";
    private static String delimiter = "#-#";
    private static String emptyLine = "                     "; //21 символ: 0-4(key), 5-7(разделитель), 8-17(значение), 18-20(разделитель)


    public static void existFile(Context context) {
        File f = new File(context.getFilesDir(), FILE_NAME);
        if(!f.exists()) createFile(f);
    }

    private static void createFile(File f) {
        try {
            f.createNewFile();
            fileEnlargement(f);
        } catch (Exception e) { }
    }

    public static void writeLine(Context context, String line, int symbolPosition, String key, int index) {
        File f = new File(context.getFilesDir(), FILE_NAME);
        String lineBuffer;
        String[] key_value;

        try {
            RandomAccessFile raf = new RandomAccessFile(f, "rw");

            for(;;symbolPosition += 230) {
                raf.seek(symbolPosition);

                if(raf.length() < symbolPosition) {
                    fileEnlargement(f);
                    raf.write(line.getBytes());
                    raf.close();
                    break;
                }

                lineBuffer = raf.readLine();
                key_value = lineBuffer.split(delimiter);

                if(key.equals(key_value[0]) || lineBuffer.equals(emptyLine)) {
                    raf.seek(symbolPosition);
                    raf.write(line.getBytes());
                    raf.close();
                    break;
                }
            }
        } catch (Exception e) { }
    }

    public static String getLine(Context context, String key, int symbolPosition) {
        File f = new File(context.getFilesDir(), FILE_NAME);
        String line;
        String[] key_value;

        try {
            RandomAccessFile raf = new RandomAccessFile(f, "rw");

            for(;;symbolPosition += 230) {
                raf.seek(symbolPosition);
                line = raf.readLine();
                key_value = line.split(delimiter);

                if(key.equals(key_value[0])) {
                    raf.close();
                    return key_value[1];
                }
                else if(raf.length() < symbolPosition) {
                    raf.close();
                    return null;
                }
            }
        } catch (Exception e) { }
        return null;
    }

    private static void fileEnlargement(File f) {
        try {
            RandomAccessFile raf = new RandomAccessFile(f, "rw");
            raf.seek(raf.length());

            for(int i = 0; i < 10; i++)
                raf.write((emptyLine + "\r\n").getBytes());

            raf.close();
        } catch (Exception e) { }
    }
}
