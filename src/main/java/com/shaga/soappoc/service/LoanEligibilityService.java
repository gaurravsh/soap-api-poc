package com.shaga.soappoc.service;

import com.shaga.soappoc.loanEligibility.Ack;
import com.shaga.soappoc.loanEligibility.CustomerRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanEligibilityService {

    public Ack checkLoanEligibility(CustomerRequest request){
        Ack ack = new Ack();
        List<String> criteriaMismatchList = ack.getCriteriaMismatch();

        if(!(request.getAge() > 30 && request.getAge()<60)){
            criteriaMismatchList.add("Age should be between 30 to 60");
        }
        if(request.getYearlyIncome()<=2_00_000){
            criteriaMismatchList.add("Yearly Income should be more than 2 lakhs");
        }
        if(request.getCibilScore()<500){
            criteriaMismatchList.add("Cibil Score too low");
        }

        if(criteriaMismatchList.size()>0) {
            ack.setApprovedAmount(0);
            ack.setIsEligible(false);
        }
        else{
            ack.setApprovedAmount(6_00_000);
            ack.setIsEligible(true);
            criteriaMismatchList.clear();
        }

        return ack;

    }
}
