package com.matter.test.annotations;

import jakarta.validation.constraints.Future;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@NotNull
@Future
@DateTimeFormat(pattern="yyyy-MM-dd")
public @interface ExpirationDate {
}
