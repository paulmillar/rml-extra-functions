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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class IntegerUtilsTest
{
    //  TESTS FOR IntegerUtils.withLeadingZeros

    @Test
    public void shouldThrowNPEForNullFirstArgument() {
        String result = IntegerUtils.withLeadingZeros(null, 4);

        assertThat(result, is(nullValue()));
    }

    @Test(expected=NullPointerException.class)
    public void shouldThrowNPEForNullSecondArgument() {
        IntegerUtils.withLeadingZeros(1234, null);
    }

    @Test
    public void shouldNotZeroPadValueWithDesiredLength() {
        String result = IntegerUtils.withLeadingZeros(1234, 4);

        assertThat(result, is(equalTo("1234")));
    }

    @Test
    public void shouldZeroPadValueWithOneLessThanDesiredLength() {
        String result = IntegerUtils.withLeadingZeros(123, 4);

        assertThat(result, is(equalTo("0123")));
    }

    @Test
    public void shouldZeroPadValueWithTwoLessThanDesiredLength() {
        String result = IntegerUtils.withLeadingZeros(12, 4);

        assertThat(result, is(equalTo("0012")));
    }

    @Test
    public void shouldNotZeroPadValueWithMoreThanDesiredLength() {
        String result = IntegerUtils.withLeadingZeros(12345, 4);

        assertThat(result, is(equalTo("12345")));
    }
}