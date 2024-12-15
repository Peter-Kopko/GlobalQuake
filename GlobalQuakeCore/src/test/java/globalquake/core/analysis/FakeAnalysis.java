package globalquake.core.analysis;

import globalquake.core.GlobalQuake;
import globalquake.core.Settings;
import globalquake.core.station.AbstractStation;

public class FakeAnalysis extends Analysis {
    long gap_threshold = 0;

    public FakeAnalysis(AbstractStation station) { super(station); }

    public void setNumRecords(long num) {
        this.numRecords = num;
    }

    @Override
    public void nextSample(int v, long time, long currentTime) {}

    @Override
    public long getGapThreshold() { return gap_threshold; }
    public void setGapThreshold(long g_t) { this.gap_threshold = g_t; }

    @Override
    public void second(long time) {}
}
