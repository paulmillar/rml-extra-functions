/*
 *    Copyright 2021 A. Paul Millar
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.paulmillar;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringUtilsTest {

    // TESTS FOR emptyToNull

    @Test
    public void shouldReturnNullForNullInputToEmptyToNull() {
        String result = StringUtils.emptyToNull(null);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnNullForEmptyInputToEmptyToNull() {
        String result = StringUtils.emptyToNull("");

        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnValueForBlankInputToEmptyToNull() {
        String result = StringUtils.emptyToNull(" ");

        assertThat(result, is(equalTo(" ")));
    }

    @Test
    public void shouldReturnValueForNonEmptyInputToEmptyToNull() {
        String result = StringUtils.emptyToNull("hello, world");

        assertThat(result, is(equalTo("hello, world")));
    }

    // TESTS FOR blankToNull

    @Test
    public void shouldReturnNullForNullInputToBlankToNull() {
        String result = StringUtils.blankToNull(null);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnNullForEmptyInputToBlankToNull() {
        String result = StringUtils.blankToNull("");

        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnNullForBlankInputToBlankToNull() {
        String result = StringUtils.blankToNull(" ");

        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnValueForNonEmptyInputToBlankToNull() {
        String result = StringUtils.blankToNull("hello, world");

        assertThat(result, is(equalTo("hello, world")));
    }

    // TESTS FOR trim

    @Test
    public void shouldReturnValueForNoLeadingOrTrailingWhiteSpaceInputToTrim() {
        String result = StringUtils.trim("hello, world");

        assertThat(result, is(equalTo("hello, world")));
    }

    @Test
    public void shouldReturnTrimmedValueForLeadingWhiteSpaceInputToTrim() {
        String result = StringUtils.trim(" hello, world");

        assertThat(result, is(equalTo("hello, world")));
    }

    @Test
    public void shouldReturnTrimmedValueForTrailingWhiteSpaceInputToTrim() {
        String result = StringUtils.trim("hello, world ");

        assertThat(result, is(equalTo("hello, world")));
    }

    @Test
    public void shouldReturnTrimmedValueForLeadingAndTrailingWhiteSpaceInputToTrim() {
        String result = StringUtils.trim(" hello, world ");

        assertThat(result, is(equalTo("hello, world")));
    }
}