package com.pg.commons.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.pg.commons.Constants.DELIMITER_REGEX;

public class StringUtil {
  public static List<String> getWords(String searchString) {
    return Arrays.stream(searchString.toLowerCase().split(DELIMITER_REGEX))
        .filter(s -> !s.isEmpty())
        .collect(Collectors.toList());
  }
}
