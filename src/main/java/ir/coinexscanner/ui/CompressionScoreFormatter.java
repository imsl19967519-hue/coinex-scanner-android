package ir.coinexscanner.ui;

import java.util.Locale;

/**
 * فرمت‌کننده امتیاز فشردگی ویر عدد مقادیر عددی برای نمایش خوانا در UI.
 */
public final class CompressionScoreFormatter {

    private CompressionScoreFormatter() {
        // Utility class — جلوگیری از instantiate
    }

    /** امتیاز فشردگی به صورت درصدی با دو رقم اعشار */
    public static String formatScore(double score) {
        return String.format(Locale.US, "%.2f%%", score * 100);
    }

    /** قیمت با حداکثر ۶ رقم اعشار و حذف صفرهای اضافی */
    public static String formatPrice(double price) {
        if (price >= 1000) {
            return String.format(Locale.US, "%,.2f", price);
        }
        return String.format(Locale.US, "%.6f", price).replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    /** برچسب متنی وضعیت بازار بر اساس enum حالت تحلیلگر */
    public static String formatState(String stateName) {
        switch (stateName) {
            case "COMPRESSED":      return "فشرده";
            case "BREAKOUT":        return "شکست";
            case "EXPANDING":       return "در حال بازشدن";
            case "TRENDING":        return "رونددار";
            default:                return stateName;
        }
    }
}
