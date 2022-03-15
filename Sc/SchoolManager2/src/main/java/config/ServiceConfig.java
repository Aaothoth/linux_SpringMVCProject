package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;
import services.ManagerService;


@Configuration()
@ComponentScan("services")
public class ServiceConfig
{
    @Bean
    public HttpInvokerServiceExporter httpInvokerServiceExporter(ManagerService managerService)
    {
        HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();

        serviceExporter.setService(managerService);
        serviceExporter.setServiceInterface(ManagerService.class);

        return serviceExporter;
    }

    /*@Bean
    public RmiServiceExporter rmiServiceExporter(ManagerService managerService)
    {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();

        rmiServiceExporter.setService(managerService);
        rmiServiceExporter.setServiceName("SchoolManagerService");
        rmiServiceExporter.setServiceInterface(ManagerService.class);
        rmiServiceExporter.setRegistryPort(8084);

        return rmiServiceExporter;
    }*/
}
