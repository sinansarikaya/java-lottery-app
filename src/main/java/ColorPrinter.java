public class ColorPrinter {
    // Metin Rengi
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String WHITE_CYAN = "\u001b[37;46m";
    public static final String RED_YELLOW = "\u001b[31;43m";
    public static final String PINK = "\u001B[38;5;201m";
    public static final String LAVENDER = "\u001b[38;5;147m";
    public static final String AQUA = "\u001b[38;2;145;231;255m";
    public static final String PENCIL = "\u001b[38;2;253;182;0m";

    // Arka Plan Rengi
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String MAGENTA_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";

    public static final String RESET = "\u001B[0m";

    public static void printColorfulText(String text, String textColor, String backgroundColor) {
        String textColorCode = textColor;
        String backgroundColorCode = backgroundColor;

        System.out.println(textColorCode + backgroundColorCode + text + RESET);
    }
    public static void printColorfulText(String text, String textColor) {
        String textColorCode = textColor;

        System.out.println(textColorCode + text + RESET);
    }
}
