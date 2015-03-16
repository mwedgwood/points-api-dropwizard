package com.points.http;

import com.points.repository.JdbiUserRepository;
import com.points.repository.JdbiUtil;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PointsApi extends Application<PointsApiConfiguration> {

    public static void main(String[] args) throws Exception {
        new PointsApi().run(args);
    }

    @Override
    public void initialize(Bootstrap<PointsApiConfiguration> bootstrap) {
        bootstrap.addCommand(new ResetDbCommand(this, "reset-db", "Initialize the database"));
    }

    @Override
    public void run(PointsApiConfiguration configuration, Environment environment) throws Exception {
        environment.jersey().register(new UserResource(new JdbiUserRepository(JdbiUtil.getDbi())));

        environment.healthChecks().register(PointsApi.class.getSimpleName(), new ApiHealthCheck());
    }

}
