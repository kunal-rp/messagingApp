package utils;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * gRPC Base Server Implementation
 */
public class BaseServer{

  private Logger logger;

  private final int port;
  private final Server server;
  private final String serviceName;

  public BaseServer(int port, io.grpc.BindableService serviceImplemenation, String serviceName){
    this.port = port;
    this.serviceName = serviceName;
    server = ServerBuilder.forPort(port).addService(serviceImplemenation).build();
    logger = Logger.getLogger(serviceName);
  }

  /** Start serving requests. */
  public void start() throws IOException {
    server.start();
    logger.info("Server "+serviceName+", listening on " + port);
    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        try {
          BaseServer.this.stop();
        } catch (InterruptedException e) {
          e.printStackTrace(System.err);
        }
        System.err.println("*** server shut down");
      }
    });
  }

  /** Stop serving requests and shutdown resources. */
  public void stop() throws InterruptedException {
    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon threads.
   */
  public void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  //Main method : will need to use to start server in class
  /*
  public static void main(String[] args) throws Exception {
    BaseServer server = new BaseServer(8982);
    server.start();
    server.blockUntilShutdown();
  }
  */

}