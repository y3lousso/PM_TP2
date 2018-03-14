package org.dictionaryActivity;

import org.dictionaryService.able.IDictionaryService;
import org.osgi.framework.*;

import java.util.ArrayList;
import java.util.List;

/** A bundle to launch a Dictionary application
 */
public class DictionaryActivityActivator implements BundleActivator, DictionaryServiceFinder {

    private BundleContext context;

    @Override
    public void start(BundleContext bundleContext)  {
        context = bundleContext;
        // Start the activity
        new DictionaryActivity(this);
        System.out.println("DictionaryActivity started !");
    }

    @Override
    public void stop(BundleContext bundleContext) {
        System.out.println("DictionaryActivity stopped !");
    }

    @Override
    public IDictionaryService[] findAllDictionaryServices() {

        List<ServiceReference<IDictionaryService>> references = null;
        try {
            // Find all the instance of DictionaryActivity started as services
            references = new ArrayList<>(context.getServiceReferences(IDictionaryService.class, null));
        } catch (InvalidSyntaxException e) {
            e.printStackTrace();
        }
        if (references == null || references.size() == 0) {
            return new IDictionaryService[0];
        } else {
            // Extract all the DictionaryService found from the references
            IDictionaryService[] services = new IDictionaryService[references.size()];
            for (int i = 0; i < references.size(); i++) {
                ServiceReference<IDictionaryService> reference = references.get(i);
                IDictionaryService service = context.getService(reference);
                services[i] = service;

            }
            return services;
        }
    }
}
