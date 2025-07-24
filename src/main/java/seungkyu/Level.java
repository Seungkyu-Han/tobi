package seungkyu;

import lombok.Getter;

@Getter
public enum Level {

    BRONZE(1),
    SILVER(2),
    GOLD(3);

    private final int value;

    Level(int value) {
        this.value = value;
    }

    public static Level from(int value) {
        return switch (value) {
            case 1 -> BRONZE;
            case 2 -> SILVER;
            case 3 -> GOLD;
            default -> throw new IllegalArgumentException("Invalid level value: " + value);
        };
    }
}
