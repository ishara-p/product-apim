package org.wso2.carbon.apimgt.rest.integration.tests.publisher.api;

import org.wso2.carbon.apimgt.rest.integration.tests.util.ApiClient;
import org.wso2.carbon.apimgt.rest.integration.tests.util.EncodingUtils;

import org.wso2.carbon.apimgt.rest.integration.tests.publisher.model.Comment;
import org.wso2.carbon.apimgt.rest.integration.tests.publisher.model.CommentList;
import org.wso2.carbon.apimgt.rest.integration.tests.publisher.model.Error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import feign.*;


public interface RetrieveApi extends ApiClient.Api {


  /**
   * Get details of an API comment
   * Get the individual comment given by a username for a certain API. 
    * @param commentId Comment Id  (required)
    * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API ID. Should be formatted as **provider-name-version**.  (required)
    * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
    * @param ifModifiedSince Validator for conditional requests; based on Last Modified header of the formerly retrieved variant of the resource.  (optional)
   * @return Comment
   */
  @RequestLine("GET /apis/{apiId}/comments/{commentId}")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
    "If-None-Match: {ifNoneMatch}",
    
    "If-Modified-Since: {ifModifiedSince}"
  })
  Comment apisApiIdCommentsCommentIdGet(@Param("commentId") String commentId, @Param("apiId") String apiId, @Param("ifNoneMatch") String ifNoneMatch, @Param("ifModifiedSince") String ifModifiedSince);

  /**
   * Retrieve API comments
   * Get a list of Comments that are already added to APIs 
    * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API ID. Should be formatted as **provider-name-version**.  (required)
    * @param limit Maximum size of resource array to return.  (optional, default to 25)
    * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
   * @return CommentList
   */
  @RequestLine("GET /apis/{apiId}/comments?limit={limit}&offset={offset}")
  @Headers({
    "Content-Type: application/json",
    "Accept: application/json",
  })
  CommentList apisApiIdCommentsGet(@Param("apiId") String apiId, @Param("limit") Integer limit, @Param("offset") Integer offset);

  /**
   * Retrieve API comments
   * Get a list of Comments that are already added to APIs 
   * Note, this is equivalent to the other <code>apisApiIdCommentsGet</code> method,
   * but with the query parameters collected into a single Map parameter. This
   * is convenient for services with optional query parameters, especially when
   * used with the {@link ApisApiIdCommentsGetQueryParams} class that allows for
   * building up this map in a fluent style.
   * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API ID. Should be formatted as **provider-name-version**.  (required)
   * @param queryParams Map of query parameters as name-value pairs
   *   <p>The following elements may be specified in the query map:</p>
   *   <ul>
   *   <li>limit - Maximum size of resource array to return.  (optional, default to 25)</li>
   *   <li>offset - Starting point within the complete list of items qualified.  (optional, default to 0)</li>
   *   </ul>
   * @return CommentList
   */
  @RequestLine("GET /apis/{apiId}/comments?limit={limit}&offset={offset}")
  @Headers({
  "Content-Type: application/json",
  "Accept: application/json",
  })
  CommentList apisApiIdCommentsGet(@Param("apiId") String apiId, @QueryMap(encoded=true) Map<String, Object> queryParams);

  /**
   * A convenience class for generating query parameters for the
   * <code>apisApiIdCommentsGet</code> method in a fluent style.
   */
  public static class ApisApiIdCommentsGetQueryParams extends HashMap<String, Object> {
    public ApisApiIdCommentsGetQueryParams limit(final Integer value) {
      put("limit", EncodingUtils.encode(value));
      return this;
    }
    public ApisApiIdCommentsGetQueryParams offset(final Integer value) {
      put("offset", EncodingUtils.encode(value));
      return this;
    }
  }
}
