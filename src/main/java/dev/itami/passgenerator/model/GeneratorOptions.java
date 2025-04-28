package dev.itami.passgenerator.model;

import lombok.Data;

@Data
public class GeneratorOptions {
    private int length;
    private boolean useNumbers;
    private boolean useUppercase;
    private boolean useSymbols;
}
