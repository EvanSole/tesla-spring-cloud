package com.tesla.cloud.core.exception.handle;

public abstract interface Handler {
     HandleResponse handle(HandleRequest paramHandleRequest);
}
