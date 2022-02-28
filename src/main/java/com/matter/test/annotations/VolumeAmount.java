package com.matter.test.annotations;

import jakarta.validation.constraints.PositiveOrZero;
import org.jetbrains.annotations.NotNull;

@NotNull
@PositiveOrZero
public @interface VolumeAmount {
}
