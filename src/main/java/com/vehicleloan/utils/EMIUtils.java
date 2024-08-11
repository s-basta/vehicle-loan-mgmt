package com.vehicleloan.utils;

public class EMIUtils {
    public static Double calculateEMI(Double principleAmount , Double rateOfInterest , int loanTenure) {
        Double emi = principleAmount * rateOfInterest * (Math.pow(1 + rateOfInterest , loanTenure) / (Math.pow(1 + rateOfInterest , loanTenure) - 1));
        return emi;
    }
}
