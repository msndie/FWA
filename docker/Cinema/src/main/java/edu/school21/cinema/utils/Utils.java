package edu.school21.cinema.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
    public static String convert(long size) {
        String cnt_size;
        double size_kb = (double) size / 1000;
        double size_mb = size_kb / 1000;

        if (size_kb > 999) {
            cnt_size = String.format("%.2fMB", size_mb);
        } else {
            cnt_size = String.format("%.2fKB", size_kb);
        }
        return cnt_size;
    }

    public static void copyData(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
    }
}
