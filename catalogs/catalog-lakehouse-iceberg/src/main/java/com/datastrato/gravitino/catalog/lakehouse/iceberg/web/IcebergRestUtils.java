/*
 * Copyright 2023 Datastrato.
 * This software is licensed under the Apache License version 2.
 */
package com.datastrato.gravitino.catalog.lakehouse.iceberg.web;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.iceberg.rest.responses.ErrorResponse;

public class IcebergRestUtils {

  private IcebergRestUtils() {}

  public static <T> Response ok(T t) {
    return Response.status(Response.Status.OK).entity(t).type(MediaType.APPLICATION_JSON).build();
  }

  public static <T> Response okWithoutContent() {
    return Response.status(Response.Status.OK).build();
  }

  public static <T> Response noContent() {
    return Response.status(Status.NO_CONTENT).build();
  }

  public static <T> Response notExists() {
    return Response.status(Status.NOT_FOUND).build();
  }

  public static Response errorResponse(Exception ex, int httpStatus) {
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .responseCode(httpStatus)
            .withType(ex.getClass().getSimpleName())
            .withMessage(ex.getMessage())
            .withStackTrace(ex)
            .build();
    return Response.status(httpStatus)
        .entity(errorResponse)
        .type(MediaType.APPLICATION_JSON)
        .build();
  }
}