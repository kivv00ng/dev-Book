package devkdt.devBook.entity;

import java.util.stream.Stream;

public enum Authority {
    ADMIN("admin"), MENTOR("mentor"), GRADUATE("graduate");

    Authority(String checkBoxValue) {
        this.checkBoxValue = checkBoxValue;
    }

    private final String checkBoxValue;


    public static Authority selectAuthority(String checkBoxValue) {
        return Stream.of(values())
                .filter(authority -> authority.checkBoxValue.equals(checkBoxValue))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Authority Not Found 커스텀 에러"));
    }
}
