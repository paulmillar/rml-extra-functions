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
}
