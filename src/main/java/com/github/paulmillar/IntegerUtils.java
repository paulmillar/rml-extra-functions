/*
 *    Copyright 2022 A. Paul Millar
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

/**
 * A utility class containing simple methods for manipulating integer values.
 */
public class IntegerUtils {

    /**
     * Provide a string base-10 representation of an integer number where the number of characters
     * is a minimum number of digits: n.  If the integer is smaller than 10^(n-1) then the string
     * will be zero-padded.
     * <p>
     * The first argument is considered optional.  If it is {@literal null} then {@literal null} is
     * returned.
     * @param in The integer value, may be null.
     * @param minimumLength the minimum length.
     * @return The zero-padded value.
     */
    public static String withLeadingZeros(Integer in, Integer minimumLength) {
        if (in == null) {
            return null;
        }
        if (minimumLength == null) {
            throw new NullPointerException("The second argument (minimumLength) is 'null'");
        }
        String value = Integer.toString(in);
        int count = minimumLength - value.length();
        return count <= 0 ? value : "0".repeat(count) + value;
    }
}
