package seungkyu;

import lombok.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {

    public static final int SILVER_LOGIN_COUNT = 50;
    public static final int GOLD_RECOMMEND_COUNT = 30;

    private Level level;
    private Integer login;
    private Integer recommend;
    private String id;
    private String name;
    private String password;
    private String email;

    public void upgradeLevel() {
        this.setLevel(Level.from(this.getLevel().getValue() + 1));
    }

    public boolean isUpgradeLevel() {
        Level level = this.level;
        return switch (level) {
            case BRONZE -> this.login >= SILVER_LOGIN_COUNT;
            case SILVER -> this.recommend >= GOLD_RECOMMEND_COUNT;
            case GOLD -> false;
        };
    }
}