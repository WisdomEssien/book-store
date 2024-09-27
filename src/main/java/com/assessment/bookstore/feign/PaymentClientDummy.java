package com.assessment.bookstore.feign;

import com.assessment.bookstore.dto.request.PaymentRequest;
import com.assessment.bookstore.dto.response.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "spring.cloud.openfeign.client.config.checkout.mock-payment",
        havingValue = "true")
public class PaymentClientDummy implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof PaymentClient) {
            log.warn("{} IS BEING MOCKED. To disable this behaviour, change mock-url to false in the config", beanName);
            return new PaymentClient() {
                @Override
                public PaymentResponse pay(PaymentRequest paymentRequest) {
                    PaymentResponse paymentResponse = new PaymentResponse();
                    paymentResponse.setTransactionId(UUID.randomUUID().toString());
                    paymentResponse.setAmount(paymentRequest.getAmount());
                    paymentResponse.setPaymentComleted(true);
                    return paymentResponse;
                }
            };
        }
        return bean;
    }
}
