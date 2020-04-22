/*
 * Copyright 2020 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.api.spi;

import java.io.OutputStream;
import javax.ws.rs.core.StreamingOutput;

public interface StreamedEndpointResponse extends EndpointResponse {

  void execute(OutputStream outputStream) throws Exception;

  static StreamedEndpointResponse create(StreamingOutput streamingOutput) {
    return new StreamedEndpointResponse() {
      @Override
      public void execute(final OutputStream outputStream) throws Exception {
        streamingOutput.write(outputStream);
      }

      @Override
      public int getStatusCode() {
        return 200;
      }

      @Override
      public String getStatusMessage() {
        return "OK";
      }

      @Override
      public Object getResponseBody() {
        return null;
      }
    };
  }

}