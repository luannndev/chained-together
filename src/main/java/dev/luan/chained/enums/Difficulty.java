package dev.luan.chained.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.format.TextColor;

import static dev.luan.chained.common.Colors.*;

@Getter
@RequiredArgsConstructor
public enum Difficulty {

    EASY(7, "Leicht", GREEN),
    MEDIUM(5, "Mittel", YELLOW),
    HARD(4, "Schwer", RED),
    EXTREME(3, "Extrem", DARK_PURPLE);

    private final double chainLength;
    private final String displayName;
    private final TextColor textColor;
}
