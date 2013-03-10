/**
 * Copyright (c) 2012 Edgar Espina
 *
 * This file is part of Handlebars.java.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jknack.handlebars.io;

import static org.apache.commons.lang3.Validate.notNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletContext;

import com.github.jknack.handlebars.TemplateLoader;

/**
 * Load templates from the {@link ServletContext}.
 *
 * @author edgar.espina
 * @since 0.1.0
 */
public class ServletContextTemplateLoader extends TemplateLoader {

  /**
   * The servlet context. Required.
   */
  private final ServletContext servletContext;

  /**
   * Creates a new {@link ServletContextTemplateLoader}.
   *
   * @param servletContext The servlet context. Required.
   * @param prefix The prefix that gets prepended to view names when building a
   *        URI.
   * @param suffix The suffix that gets appended to view names when building a
   *        URI. Required.
   */
  public ServletContextTemplateLoader(final ServletContext servletContext,
      final String prefix, final String suffix) {
    setPrefix(prefix);
    setSuffix(suffix);
    this.servletContext = notNull(servletContext, "The servlet context is required.");
  }

  /**
   * Creates a new {@link ServletContextTemplateLoader}.
   *
   * @param servletContext The servlet context. Required.
   * @param prefix The prefix that gets prepended to view names when building a
   *        URI.
   */
  public ServletContextTemplateLoader(final ServletContext servletContext,
      final String prefix) {
    this(servletContext, prefix, DEFAULT_SUFFIX);
  }

  /**
   * Creates a new {@link ServletContextTemplateLoader}.
   *
   * @param servletContext The servlet context. Required.
   */
  public ServletContextTemplateLoader(final ServletContext servletContext) {
    this(servletContext, "/", DEFAULT_SUFFIX);
  }

  @Override
  protected Reader read(final String path) throws IOException {
    InputStream input = servletContext.getResourceAsStream(path);
    if (input == null) {
      return null;
    }
    return new InputStreamReader(input, "UTF-8");
  }

}
