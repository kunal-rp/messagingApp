  package utils;

import java.util.List;
import java.util.ArrayList;
import io.grpc.ServerBuilder;
import io.grpc.ServerServiceDefinition;
import io.grpc.BindableService;

  public class BaseServerBuilder {
    public int port;
    public String serviceName;
    private List<ServerServiceDefinition> serviceDefinitions = new ArrayList();
    private List<BindableService> bindableServices = new ArrayList();

    public BaseServerBuilder(int port, String serviceName){
      this.port = port;
      this.serviceName = serviceName;
    }

    public BaseServerBuilder addService(ServerServiceDefinition serviceDefinition){
      serviceDefinitions.add(serviceDefinition);
      return this;
    }

    public BaseServerBuilder addService(BindableService bindableService){
      bindableServices.add(bindableService);
      return this;
    }

    public BaseServer build(){
      ServerBuilder serverBuilder = ServerBuilder.forPort(port);
       for(ServerServiceDefinition def : serviceDefinitions){
        serverBuilder.addService(def);
      }
      for(BindableService def : bindableServices){
        serverBuilder.addService(def);
      }
      return new BaseServer(port, serverBuilder.build(), serviceName);
    }

  }