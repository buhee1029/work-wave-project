package com.wanted.workwave.column.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.wanted.workwave.column.exception.InvalidTagException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Tag {
    FRONTEND,
    BACKEND,
    DESIGN,
    QA,
    PM,
    DOCUMENT;

    Tag() {}

    @JsonCreator
    public static Tag of(String input) {
        return Arrays.stream(Tag.values())
                .filter(tag -> tag.name().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(InvalidTagException::new);
    }

}
