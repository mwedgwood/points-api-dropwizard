package com.points.http;

import com.points.repository.JdbiUtil;
import com.points.repository.MembershipRepository;
import com.points.repository.UserRepository;
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
        UserRepository userRepository = new UserRepository(JdbiUtil.getDbi());
        MembershipRepository membershipRepository = new MembershipRepository(JdbiUtil.getDbi());

        environment.jersey().register(new UserResource(userRepository));
        environment.jersey().register(new MembershipResource(userRepository, membershipRepository));

        environment.healthChecks().register(PointsApi.class.getSimpleName(), new ApiHealthCheck());
    }

}
