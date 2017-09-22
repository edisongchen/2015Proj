package com.test.java8;


import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Set;

/**
 * Intermediate操作是lazy的
 * 包含以下操作
 * map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
 */
public class IntermediateOp {

    @Test
    public void mapTest() {
        Set<String> set
                = Stream.of("1", "1", "2", "b", "a", "b")
                .map(s->s.toUpperCase()).collect(Collectors.toSet());
        System.out.println(set);
    }
}
