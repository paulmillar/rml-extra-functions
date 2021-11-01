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

/**
 * A utility class containing simple methods for manipulating Strings.
 */
public class StringUtils {
    /**
     * Convert empty strings to null values.  An empty string is a string with no characters.  This
     * method returns null if the input is a null value.
     * @param in A string that might be empty.
     * @return A value that is never an empty string.
     */
    public static String emptyToNull(String in) {
        if (in != null && in.isEmpty()) {
            return null;
        }
        return in;
    }

    /**
     * Convert blank strings to null values.  A blank string is any string that contains only
     * white space code points. This method returns null if the input is a null value.
     * @param in A string that might be empty.
     * @return A value that is never an empty string.
     */
    public static String blankToNull(String in) {
        if (in != null && in.isBlank()) {
            return null;
        }
        return in;
    }
}
