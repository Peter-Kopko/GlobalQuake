package globalquake.core.analysis;

import globalquake.core.GlobalQuake;

public class MockGlobalQuake extends GlobalQuake {
    public static GlobalQuake instance;

    public static FakeAnalysis analysis = null;

    public static GlobalQuake getInstance() {
        return instance;
    }

    @Override
    public boolean limitedSettings() {
        return false;
    }

    @Override
    public boolean limitedWaveformBuffers() {
        return false;
    }
}
