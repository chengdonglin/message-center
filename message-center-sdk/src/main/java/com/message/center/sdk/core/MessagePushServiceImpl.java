package com.message.center.sdk.core;

import com.message.center.sdk.core.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @Author linchengdong
 * @Date 2024-08-14 14:51
 * @PackageName:com.message.center.sdk.core
 * @ClassName: MessagePushServiceImpl
 * @Description: TODO
 * @Version 1.0
 */
@Slf4j
public class MessagePushServiceImpl implements MessagePushService{

    private static final String APIKEY = "apiKey";

    private static final String TOKEN = "token";
    private final MessagePushProperties messagePushProperties;

    private final RestTemplate restTemplate;

    private final TokenService tokenService;


    public MessagePushServiceImpl(MessagePushProperties messagePushProperties, RestTemplate restTemplate, TokenService tokenService) {
        this.messagePushProperties = messagePushProperties;
        this.restTemplate = restTemplate;
        this.tokenService = tokenService;
    }

    @Override
    public PushResult push(MessagePushDTO messagePushDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(APIKEY, messagePushProperties.getApiKey());
        headers.add(TOKEN, tokenService.generate(messagePushProperties.getApiKey(),messagePushProperties.getSecret()));
        HttpEntity<MessagePushDTO> requestEntity = new HttpEntity<>(messagePushDTO, headers);
        ResponseEntity<PushResultData> response = restTemplate.postForEntity(messagePushProperties.getUrl(), requestEntity, PushResultData.class);
        log.info("send message center response {}",response);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            if (Objects.requireNonNull(response.getBody()).getSuccess()) {
                return response.getBody().getData();
            }
        }
        return null;
    }
}
