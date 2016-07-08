package com.example.helloworld;

import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by avec on 07/07/16.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration>{

    private static Logger LOG = LoggerFactory.getLogger(HelloWorldApplication.class);

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        LOG.debug("initialize(bootstrap)");
        LOG.debug(bootstrap.toString());
        // nothing to do yet
    }

    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        LOG.debug("run(...)");
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());

        LOG.info("register healthcheck");
        environment.healthChecks().register("template", healthCheck);

        LOG.info("register HelloWorldResource");
        environment.jersey().register(resource);
    }
}
