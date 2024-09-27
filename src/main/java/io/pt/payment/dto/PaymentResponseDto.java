package io.pt.payment.dto;

public class PaymentResponseDto {

    private Result result;

    public PaymentResponseDto() {}

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
        private String redirectUrl;

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }
    }
}
