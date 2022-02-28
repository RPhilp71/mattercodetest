package com.matter.test.annotations;

import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;

@NotNull
@Size(min = 1)
public @interface Symbol {
}
