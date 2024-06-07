package io.kestra.runner.h2;

import io.kestra.core.exceptions.DeserializationException;
import io.kestra.core.queues.WorkerJobQueueInterface;
import io.kestra.core.runners.WorkerJob;
import io.kestra.core.utils.Either;
import io.kestra.jdbc.JdbcWorkerJobQueueService;
import io.micronaut.context.ApplicationContext;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
public class H2WorkerJobQueue implements WorkerJobQueueInterface {
    private final JdbcWorkerJobQueueService jdbcWorkerJobQueueService;

    public H2WorkerJobQueue(ApplicationContext applicationContext) {
        this.jdbcWorkerJobQueueService = applicationContext.getBean(JdbcWorkerJobQueueService.class);
    }

    @Override
    public Runnable receive(String consumerGroup, Class<?> queueType, Consumer<Either<WorkerJob, DeserializationException>> consumer) {
        return jdbcWorkerJobQueueService.receive(consumerGroup, queueType, consumer);
    }

    @Override
    public void close() {
        jdbcWorkerJobQueueService.close();
    }
}
