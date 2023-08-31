package com.fast.sns.consumer;

import com.fast.sns.model.event.AlarmEvent;
import com.fast.sns.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlarmConsumer {

    private final AlarmService alarmService;

    @KafkaListener(topics = "${spring.kafka.topic.notification}")
    public void consumeNotification(AlarmEvent event, Acknowledgment ack) {
        log.info("Consume the event {}", event);
        alarmService.send(event.getType(), event.getArgs(), event.getReceiverUserId());
        ack.acknowledge();
    }
}