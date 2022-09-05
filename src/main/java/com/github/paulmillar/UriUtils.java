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

import java.net.URI;
import java.net.URISyntaxException;

/**
 * A utility class containing simple methods for manipulating URIs.
 */
public class UriUtils
{
    /**
     * Resolve a path relative to some URI.  The path may be absolute (starts with a '/') or
     * relative (does not start with a '/').  If the URI has a trailing slash then it is considered
     * a directory, otherwise uri is considered as the location of a file.
     * @param base The URI against which the path is resolved.
     * @param path The location of a resource that should be resolved to a URI.
     * @return The resolved location of the resource.
     */
    public static String resolve(String base, String path)
    {
        return URI.create(base).resolve(path).toString();
    }

    /**
     * Resolve a path relative to a directory URI.  Unlike the {@link #resolve} method, this method
     * treats the URI argument as a directory even if it does not have a trailing slash.
     * @param base The directory URI against which the path is resolved.
     * @param path The location of a resource, relative to {@literal uri}.
     * @return The resolved location of the resource.
     */
    public static String resolveDirectory(String base, String path)
    {
        String dirBase = base.endsWith("/") ? base : (base + "/");
        return URI.create(dirBase).resolve(path).toString();
    }

    /**
     * Try to express the location of {@literal target} as a path relative to {@literal base}.
     * If this is possible then the returned value is a relative path.  If it's not possible then
     * the returned value is the target URI.
     * @param base The URI from which the relative path will be based.
     * @param target The resource to which the path should resolve.
     * @return the relative path, if possible.
     */
    public static String relativize(String base, String target)
    {
        URI firstUri = URI.create(base);
        URI otherUri = URI.create(target);

        return firstUri.relativize(otherUri).toString();
    }

    /**
     * Take a badly written URL and try to make sense of it.  In particular, convert a URL with
     * unsafe characters and encode them.  If URL could not be processed then the input is returned
     * unchanged.
     * <p>
     * The argument is optional.  If the argument is {@literal null} then the method will return
     * {@literal null}.
     * @param targetUrl The URL to format
     * @return The percent-encoded URI.
     */
    public static String encodeUrl(String targetUrl)
    {
        if (targetUrl == null) {
            return null;
        }

        int schemeIndex = targetUrl.indexOf(':');
        if (schemeIndex == -1) {
            return targetUrl;
        }

        String scheme = targetUrl.substring(0, schemeIndex);
        String schemePart = targetUrl.substring(schemeIndex+1);
        if (!schemePart.startsWith("//")) {
            return targetUrl;
        }

        int hierarchyIndex = schemePart.indexOf('/', 2);
        if (hierarchyIndex == -1) {
            return targetUrl;
        }

        String authority = schemePart.substring(2, hierarchyIndex);
        String path = schemePart.substring(hierarchyIndex);

        // REVISIT: we assume that in contains no query or fragment.

        try {
            URI validUrl = new URI(scheme, authority, path, null, null);
            return validUrl.toASCIIString();
        } catch (URISyntaxException e) {
            System.err.println("Bad URI: " + e);
            return targetUrl;
        }
    }
}
