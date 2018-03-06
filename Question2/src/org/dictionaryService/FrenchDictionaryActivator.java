package org.dictionaryService;

import org.dictionaryService.able.IDictionaryService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.io.IOException;

/** A bundle to launch a service for English Dictionary
 */
public class FrenchDictionaryActivator implements BundleActivator {

    private ServiceRegistration registration;

    @Override
    public void start(BundleContext bundleContext) {
        try {
            // create the service with the dictionary file
            IDictionaryService service = new DictionaryService("/fr.txt", "French");
            registration = bundleContext.registerService(
                    IDictionaryService.class.getName(),
                    service,
                    null);
            System.out.println("French dictionary service started !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop(BundleContext bundleContext) {
        registration.unregister();
        System.out.println("French dictionary service stopped !");
    }

}
