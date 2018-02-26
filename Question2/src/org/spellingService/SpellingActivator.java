package org.spellingService;

import org.dictionaryService.DictionaryService;
import org.dictionaryService.IDictionaryService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
public class SpellingActivator implements BundleActivator {

    private ServiceReference reference;
    private SpellingService service;

    @Override
    public void start(BundleContext bundleContext) {
        reference = bundleContext.getServiceReference(DictionaryService.class.getName());
        service = new SpellingService((IDictionaryService) bundleContext.getService(reference));
        String lang = service.getDictionaryService().getLanguage();
        System.out.println("Spelling service active ! (Language : " + lang + ")");
    }

    @Override
    public void stop(BundleContext bundleContext) {
        System.out.println("Spelling service stopped !");
    }

    public SpellingService getSpellingService() {
        return service;
    }
}
