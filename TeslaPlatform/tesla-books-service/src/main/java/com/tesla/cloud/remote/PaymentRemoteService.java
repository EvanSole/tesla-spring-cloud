package com.tesla.cloud.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "tesla-account-server")
public interface PaymentRemoteService {

    @RequestMapping(value = "/payment",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void orderPayment(@PathVariable("orderId") String orderId);

}

