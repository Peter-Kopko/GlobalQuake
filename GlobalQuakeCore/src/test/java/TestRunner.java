import globalquake.core.analysis.AnalysisTest;
import globalquake.core.analysis.WaveformBufferTest;
import globalquake.core.database.StationDatabaseManagerTest;
import globalquake.core.earthquake.EarthquakeAnalysisTest;
import globalquake.core.geo.taup.DifferenceTest;
import globalquake.core.geo.taup.TauPTravelTimeCalculatorTest;
import globalquake.core.regions.RegionUpdaterTest;
import globalquake.core.regions.RegionsTest;
import globalquake.core.station.AbstractStationTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import junit.framework.*;

public class TestRunner {
    private static void runTest(Object test) {
        Result result = JUnitCore.runClasses((Class<?>) test);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful())
            System.out.println("Test on " + test + " SUCCEEDED.");
        else
            System.out.println("Test on " + test + " FAILED.");

    }

    public static void main(String[] args)
    {
        Object[] tests = new Object[]{
            WaveformBufferTest.class, StationDatabaseManagerTest.class,
            EarthquakeAnalysisTest.class, //DifferenceTest.class,
            TauPTravelTimeCalculatorTest.class, RegionsTest.class,
            RegionUpdaterTest.class, AbstractStationTest.class,
            AnalysisTest.class
        };

        for (Object test : tests) {
            runTest(test);
        }

        System.exit(0);
    }
}
