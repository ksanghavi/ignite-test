import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.DataPageEvictionMode;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

public class HelloWorld {
    public static void main(String[] args) throws Exception {

        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        DataStorageConfiguration dataStorageConfiguration = new DataStorageConfiguration();
        DataRegionConfiguration defaultDataRegionConfiguration = defaultDataRegion();
        defaultDataRegionConfiguration.setPersistenceEnabled(true);
        dataStorageConfiguration.setStoragePath("/var/tmp/ignite-cdc/");
        dataStorageConfiguration.setWalPath("/var/tmp/ignite-cdc/wal/");
        dataStorageConfiguration.setWalArchivePath("/var/tmp/ignite-cdc/wal/archive");
        dataStorageConfiguration.setDefaultDataRegionConfiguration(defaultDataRegionConfiguration);
        igniteConfiguration.setDataStorageConfiguration(dataStorageConfiguration);
        igniteConfiguration.setMetricsLogFrequency(0);
        Ignite ignite = Ignition.start(igniteConfiguration);
        ignite.cluster().active(true);
    }
    private static DataRegionConfiguration defaultDataRegion() {
        DataRegionConfiguration dataRegionConfiguration = new DataRegionConfiguration();
        dataRegionConfiguration.setName("DefaultDataRegion");
        dataRegionConfiguration.setInitialSize(512L * 1024L * 1024);
        dataRegionConfiguration.setMaxSize(512L * 1024L * 1024);
        dataRegionConfiguration.setMetricsEnabled(true);
        dataRegionConfiguration.setPageEvictionMode(DataPageEvictionMode.RANDOM_2_LRU);
        return dataRegionConfiguration;
    }
}