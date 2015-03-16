package com.points.http;

import com.google.common.base.Optional;
import com.points.model.User;
import com.points.repository.JdbiUtil;
import com.points.repository.MembershipRepository;
import com.points.repository.UserRepository;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.auth.basic.BasicCredentials;
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

        environment.jersey().register(new TokenResource());
        environment.jersey().register(new UserResource(userRepository));
        environment.jersey().register(new MembershipResource(userRepository, membershipRepository));

        environment.healthChecks().register(PointsApi.class.getSimpleName(), new ApiHealthCheck());

        environment.jersey().register(AuthFactory.binder(new BasicAuthFactory<>(new SimpleAuthenticator(userRepository),
                "SUPER SECRET STUFF",
                User.class)));
    }

    public class SimpleAuthenticator implements Authenticator<BasicCredentials, User> {
        private final UserRepository userRepository;

        public SimpleAuthenticator(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
            if ("points".equals(credentials.getUsername()) && "letmein".equals(credentials.getPassword())) {
                User user = userRepository.findByName(credentials.getUsername());
                return user == null ? Optional.absent() : Optional.of(user);
            }
            return Optional.absent();
        }
    }

}
