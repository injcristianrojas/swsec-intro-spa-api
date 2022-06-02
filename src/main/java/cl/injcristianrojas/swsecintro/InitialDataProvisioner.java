package cl.injcristianrojas.swsecintro;

import cl.injcristianrojas.swsecintro.rest.controller.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class InitialDataProvisioner implements ApplicationListener<ContextRefreshedEvent> {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Yes, it does work.");
    }
}
