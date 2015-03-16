package com.points.http;

import com.points.repository.JdbiUtil;
import io.dropwizard.Application;
import io.dropwizard.cli.EnvironmentCommand;
import io.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;

public class ResetDbCommand extends EnvironmentCommand<PointsApiConfiguration> {

    protected ResetDbCommand(Application<PointsApiConfiguration> application, String name, String description) {
        super(application, name, description);
    }

    @Override
    protected void run(Environment environment, Namespace namespace, PointsApiConfiguration pointsApiConfiguration) throws Exception {
        JdbiUtil.bootstrap();

        System.exit(0);
    }
}
