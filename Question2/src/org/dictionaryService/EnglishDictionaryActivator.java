package org.dictionaryService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.io.IOException;

public class EnglishDictionaryActivator implements BundleActivator, IDictionaryActivator {

    private ServiceRegistration registration;
    private IDictionaryService service;

    @Override
    public void start(BundleContext bundleContext) {
        try {
            service = new DictionaryService("/en.txt", "English");
            registration = bundleContext.registerService(
                    DictionaryService.class.getName(),
                    service,
                    null);
            System.out.println("English dictionary service active !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop(BundleContext bundleContext) {
        registration.unregister();
        System.out.println("English dictionary service stopped !");
    }

    @Override
    public IDictionaryService getDictionaryService() {
        return service;
    }
}
