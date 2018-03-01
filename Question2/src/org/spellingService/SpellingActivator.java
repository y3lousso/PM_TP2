package org.spellingService;

import org.dictionaryService.DictionaryService;
import org.dictionaryService.able.IDictionaryService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

public class SpellingActivator implements BundleActivator, DictionaryServiceFinder {

    private BundleContext context;

    @Override
    public void start(BundleContext bundleContext)  {
        context = bundleContext;
        new DictionaryActivity(this);
        System.out.println("Spelling service started !");
    }

    @Override
    public void stop(BundleContext bundleContext) {
        System.out.println("Spelling service stopped !");
    }

    @Override
    public IDictionaryService[] findAllDictionaryServices() {
        ServiceReference[] references = new ServiceReference[0];
        try {
            references = context.getAllServiceReferences(DictionaryService.class.getName(), null);
        } catch (InvalidSyntaxException e) {
            e.printStackTrace();
        }
        if (references == null || references.length == 0) {
            return new IDictionaryService[0];
        }
        else {
            IDictionaryService[] services = new IDictionaryService[references.length];
            for (int i = 0; i < references.length; i++) {
                ServiceReference reference = references[i];
                services[i] = (IDictionaryService) context.getService(reference);
            }
            return services;
        }
    }
}
