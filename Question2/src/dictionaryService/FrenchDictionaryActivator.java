package dictionaryService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URISyntaxException;

public class FrenchDictionaryActivator implements BundleActivator, IDictionaryActivator {

    private ServiceRegistration registration;
    private IDictionaryService service;

    @Override
    public void start(BundleContext bundleContext) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("dictionary.xml");
            service = new DictionaryService("/fr.txt");
            registration = bundleContext.registerService(
                    DictionaryService.class.getName(),
                    service,
                    null);
            System.out.println("French dictionary service active !");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop(BundleContext bundleContext) {
        registration.unregister();
        System.out.println("French dictionary service stopped !");
    }

    @Override
    public IDictionaryService getDictionaryService() {
        return service;
    }
}
