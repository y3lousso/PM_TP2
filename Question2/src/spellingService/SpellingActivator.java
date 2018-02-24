package spellingService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
public class SpellingActivator implements BundleActivator {

    private ServiceRegistration registration;

    @Override
    public void start(BundleContext bundleContext) {

        registration = bundleContext.registerService(
                SpellingActivator.class.getName(),
                new SpellingActivator(),
                null);
        System.out.println("Spelling service active !");
    }

    @Override
    public void stop(BundleContext bundleContext) {
        registration.unregister();
        System.out.println("Spelling service stopped !");
    }
}
