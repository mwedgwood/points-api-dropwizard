package com.points.http;

import com.codahale.metrics.health.HealthCheck;

public class ApiHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
