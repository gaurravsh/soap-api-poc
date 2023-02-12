package com.shaga.soappoc.endpoint;

import com.shaga.soappoc.loanEligibility.Ack;
import com.shaga.soappoc.loanEligibility.CustomerRequest;
import com.shaga.soappoc.service.LoanEligibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class LoanEligibilityEndpoint {
    private static final String NAME_SPACE = "http://www.shaga.com/soappoc/loanEligibility";

    @Autowired
    LoanEligibilityService service;

    @PayloadRoot(namespace = NAME_SPACE, localPart = "CustomerRequest")
    @ResponsePayload
    public Ack getLoanAcknowledgment(@RequestPayload CustomerRequest request){
        System.out.printf("request: age=%d, income=%d, cibil-score=%d%n",request.getAge(),request.getYearlyIncome(),request.getCibilScore());
        return service.checkLoanEligibility(request);
    }
}
