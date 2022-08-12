package edu.school21.cinema.utils;

public class SizeConverter {
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
}
