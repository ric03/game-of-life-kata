package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CellState {
    ALIVE('*'), DEAD('.');

    private final char sign;
}
