package io.pt.payment.dto;

public class PaymentRequestDto {

    public static final String request = """
            {
              "paymentType": "DEPOSIT",
              "paymentMethod": "BASIC_CARD",
              "amount": %f,
              "currency": "EUR",
              "description": "Deposit 123 via TEST shop",
              "card": {
                "cardNumber": "4000 0000 0000 0002",
                "cardholderName": "John Smith",
                "cardSecurityCode": "010",
                "expiryMonth": "01",
                "expiryYear": "2030"
              },
              "customer": {
                "referenceId": "customer_123",
                "citizenshipCountryCode": "AU",
                "firstName": "John",
                "lastName": "Smith",
                "dateOfBirth": "2001-12-03",
                "email": "my@email.com",
                "phone": "357 123123123",
                "locale": "ru",
                "accountNumber": "string",
                "accountName": "string",
                "bank": "string",
                "bankBranch": "string",
                "documentType": "string",
                "documentNumber": "string",
                "routingGroup": "VIP",
                "kycStatus": true,
                "paymentInstrumentKycStatus": true,
                "dateOfFirstDeposit": "2021-01-01",
                "depositsAmount": 5000,
                "withdrawalsAmount": 1000,
                "depositsCnt": 5000,
                "withdrawalsCnt": 1000
              },
              "billingAddress": {
                "addressLine1": "7, Sunny street",
                "addressLine2": "Office 3",
                "city": "Limassol",
                "countryCode": "CY",
                "postalCode": "4141",
                "state": "CA"
              },
              "returnUrl": "https://mywebsite.com/{id}/{referenceId}/{state}/{type}",
              "webhookUrl": "https://mywebsite.com/webhooks",
              "startRecurring": false,
              "additionalParameters": {
                "bankCode": "ABHY0065032",
                "countryOfBirth": "CY"
              }
            }
            """;

    public static String buildRequest(Double amount) {
        return request.formatted(amount);
    }
}
