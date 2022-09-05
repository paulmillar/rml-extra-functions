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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class UriUtilsTest
{
    //  TESTS FOR UriUtils.resolve

    @Test
    public void shouldResolveFileUrlWithAFile()
    {
        String resolved = UriUtils.resolve("http://example.org/path/to/a-file", "another-file");

        assertThat(resolved, equalTo("http://example.org/path/to/another-file"));
    }

    @Test
    public void shouldResolvePathUrlWithAFile()
    {
        String resolved = UriUtils.resolve("http://example.org/path/to/a-dir/", "a-file");

        assertThat(resolved, equalTo("http://example.org/path/to/a-dir/a-file"));
    }

    @Test
    public void shouldResolveFileUrlWithAnAbsoluteFile()
    {
        String resolved = UriUtils.resolve("http://example.org/path/to/a-file",
                "/another/path/to/a-file");

        assertThat(resolved, equalTo("http://example.org/another/path/to/a-file"));
    }

    @Test
    public void shouldResolveFileUrlWithAnAbsoluteDirectory()
    {
        String resolved = UriUtils.resolve("http://example.org/path/to/a-file", "/another/path/");

        assertThat(resolved, equalTo("http://example.org/another/path/"));
    }

    @Test
    public void shouldResolveFileUrlWithFileOnADifferentHost()
    {
        String resolved = UriUtils.resolve("http://example.org/path/to/a-file",
                "//example.com/a-file");

        assertThat(resolved, equalTo("http://example.com/a-file"));
    }

    @Test
    public void shouldResolveFileUrlWithFileWithDifferentSchema()
    {
        String resolved = UriUtils.resolve("http://example.org/path/to/a-file",
                "https://example.com/a-file");

        assertThat(resolved, equalTo("https://example.com/a-file"));
    }

    //  TESTS FOR UriUtils.resolveDirectory

    @Test
    public void shouldResolveDirectoryWithRelativePath()
    {
        String resolved = UriUtils.resolveDirectory("https://ror.org/00rqy9422", "address-0");

        assertThat(resolved, equalTo("https://ror.org/00rqy9422/address-0"));
    }

    @Test
    public void shouldResolveDirectoryWithAbsolutePath()
    {
        String resolved = UriUtils.resolveDirectory("https://ror.org/00rqy9422", "/address-0");

        assertThat(resolved, equalTo("https://ror.org/address-0"));
    }

    //  TESTS FOR UriUtils.resolve

    @Test
    public void shouldRelativizeDirectoryWithSubItem()
    {
        String relativePath = UriUtils.relativize("http://example.org/path/",
                "http://example.org/path/to/something");

        assertThat(relativePath, equalTo("to/something"));
    }

    @Test
    public void shouldRelativizeFileWithSubItem()
    {
        String relativePath = UriUtils.relativize("http://example.org/path",
                "http://example.org/path/to/something");

        assertThat(relativePath, equalTo("to/something"));
    }

    @Test
    public void shouldRelativizeCannotBeRelativized()
    {
        String relativePath = UriUtils.relativize("http://example.org/path/to/something",
                "http://example.org/path/");

        assertThat(relativePath, equalTo("http://example.org/path/"));
    }

    //  TESTS FOR UriUtils.encodeUrl

    @Test
    public void shouldReturnNullForNullInputToEncodeUrl()
    {
        String result = UriUtils.encodeUrl(null);

        assertThat(result, is(nullValue()));
    }

    @Test
    public void shouldReturnUnencodedValueForSimpleInputToEncodeUrl()
    {
        String result = UriUtils.encodeUrl("http://example.org/multi/element/path");

        assertThat(result, is(equalTo("http://example.org/multi/element/path")));
    }

    @Test
    public void shouldReturnEncodedValueForInputWithQuotesToEncodeUrl()
    {
        String result = UriUtils.encodeUrl("http://example.org/multi/element/path\"foo\"");

        assertThat(result, is(equalTo("http://example.org/multi/element/path%22foo%22")));
    }

    @Test
    public void shouldReturnEncodedValueForInputWithPercentToEncodeUrl()
    {
        String result = UriUtils.encodeUrl("http://example.org/multi/element/path foo");

        assertThat(result, is(equalTo("http://example.org/multi/element/path%20foo")));
    }

    @Test
    public void shouldReturnEncodedValueForTestInput1ToEncodeUrl()
    {
        String result = UriUtils.encodeUrl("https://en.wikipedia.org/wiki/University_of_Information_Science_and_Technology_\"St._Paul_The_Apostle\"");

        assertThat(result, is(equalTo("https://en.wikipedia.org/wiki/University_of_Information_Science_and_Technology_%22St._Paul_The_Apostle%22")));
    }

    @Test
    public void shouldReturnEncodedValueForTestInput2ToEncodeUrl()
    {
        String result = UriUtils.encodeUrl("https://en.wikipedia.org/wiki/Università_\"Italian_University_Line\"");

        assertThat(result, is(equalTo("https://en.wikipedia.org/wiki/Universit%C3%A0_%22Italian_University_Line%22")));
    }

    @Test
    public void shouldReturnEncodedValueForTestInput3ToEncodeUrl()
    {
        String result = UriUtils.encodeUrl("https://en.wikipedia.org/wiki/University_of_Gjirokastër_\"Eqrem_Çabej\"");

        assertThat(result, is(equalTo("https://en.wikipedia.org/wiki/University_of_Gjirokast%C3%ABr_%22Eqrem_%C3%87abej%22")));
    }
}
