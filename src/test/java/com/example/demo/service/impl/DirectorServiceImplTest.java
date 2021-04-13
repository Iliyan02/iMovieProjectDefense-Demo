package com.example.demo.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = NONE)
class DirectorServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAllDirectros() {

        List<String> actual = Arrays.asList("a", "b", "c");
        List<String> expected = Arrays.asList("a", "b", "c");


        assertThat(actual, is(expected));

        assertThat(actual, hasItems("b"));

        assertThat(actual, hasSize(3));

        assertThat(actual.size(), is(3));


        assertThat(actual, contains("a", "b", "c"));

        assertThat(actual, containsInAnyOrder("c", "b", "a"));

        assertThat(actual, not(IsEmptyCollection.empty()));

        assertThat(new ArrayList<>(), IsEmptyCollection.empty());

    }

}

