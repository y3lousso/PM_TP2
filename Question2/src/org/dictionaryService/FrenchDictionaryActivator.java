package org.dictionaryService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.io.IOException;

public class FrenchDictionaryActivator implements BundleActivator, IDictionaryActivator {

    private ServiceRegistration registration;
    private IDictionaryService service;

    @Override
    public void start(BundleContext bundleContext) {
        try {
            service = new DictionaryService("/fr.txt", "French");
            registration = bundleContext.registerService(
                    DictionaryService.class.getName(),
                    service,
                    null);
            System.out.println("French dictionary service active !");
        } catch (IOException e) {
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
