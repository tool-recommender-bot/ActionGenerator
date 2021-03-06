/**
 * Copyright Sematext International
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
package com.sematext.ag.es.util;

import java.util.Map;
import java.util.Map.Entry;

import com.sematext.ag.event.SimpleDataEvent;

/**
 * Utility methods for working with JSON.
 * 
 * @author sematext, http://www.sematext.com/
 */
public final class JSONUtils {
  private JSONUtils() {
  }

  /**
   * Returns ElasticSearch add command.
   * 
   * @param values
   *          values to include
   * @return XML as String
   */
  public static String getElasticSearchAddDocument(Map<String, String> values) {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    boolean first = true;
    for (Map.Entry<String, String> pair : values.entrySet()) {
      if (!first) {
        builder.append(",");
      }
      JSONUtils.addElasticSearchField(builder, pair);
      first = false;
    }
    builder.append("}");
    return builder.toString();
  }

  /**
   * Constructs ES bulk header for the document.
   * 
   * @param event
   *          data event
   * @param index
   *          index name
   * @param type
   *          document type
   * @return ES bulk header
   */
  public static String getElasticSearchBulkHeader(SimpleDataEvent event, String index, String type) {
    StringBuilder builder = new StringBuilder();
    builder.append("{\"index\":{\"_index\":\"");
    builder.append(index);
    builder.append("\",\"_type\":\"");
    builder.append(type);
    builder.append("\",\"_id\":\"");
    builder.append(event.getId());
    builder.append("\"}}");
    return builder.toString();
  }

  private static void addElasticSearchField(StringBuilder builder, Entry<String, String> pair) {
    if (pair.getKey() != null && !pair.getKey().isEmpty() && pair.getValue() != null && !pair.getValue().isEmpty()) {
      builder.append("\"").append(pair.getKey()).append("\":\"").append(pair.getValue()).append("\"");
    }
  }
}
