package com.points.http;

import com.points.repository.JdbiUtil;
import io.dropwizard.Application;
import io.dropwizard.cli.EnvironmentCommand;
import io.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResetDbCommand extends EnvironmentCommand<PointsApiConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetDbCommand.class);

    private static final String CREATE_TABLES = "\nDROP TABLE IF EXISTS user;\n" +
            "DROP TABLE IF EXISTS membership;\n" +
            "CREATE TABLE user (\n" +
            "  id      SERIAL PRIMARY KEY,\n" +
            "  name    VARCHAR(50) NOT NULL\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE membership (\n" +
            "  id           SERIAL PRIMARY KEY,\n" +
            "  name         VARCHAR(50) NOT NULL,\n" +
            "  member_id    INT NOT NULL,\n" +
            "  user_id      INT REFERENCES user(id)\n" +
            ");\n";

    protected ResetDbCommand(Application<PointsApiConfiguration> application, String name, String description) {
        super(application, name, description);
    }

    @Override
    protected void run(Environment environment, Namespace namespace, PointsApiConfiguration pointsApiConfiguration) throws Exception {
        JdbiUtil.getDbi().withHandle(handle -> handle.createStatement(CREATE_TABLES).execute());
        LOGGER.info(CREATE_TABLES);

        System.exit(0);
    }
}
