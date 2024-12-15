package globalquake.core.analysis;

import edu.sc.seis.seisFile.mseed.Btime;
import edu.sc.seis.seisFile.mseed.DataHeader;
import edu.sc.seis.seisFile.mseed.DataRecord;

import globalquake.core.station.AbstractStation;
import globalquake.core.station.GlobalStation;
import gqserver.api.packets.station.InputType;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AnalysisTest {

    @Test
    public void testStation(){
        AbstractStation absStation = new GlobalStation("", "", "", "", 5, 5, 5, 5, null, -1, InputType.UNKNOWN);
        FakeAnalysis analysis = new FakeAnalysis(absStation);

        AbstractStation absStationTested = analysis.getStation();
        assertEquals(absStation, absStationTested);

        System.out.println("Test testStation passed.");
    }

    @Test
    public void testDecode(){
        AbstractStation absStation = new GlobalStation("", "", "", "", 5, 5, 5, 5, null, -1, InputType.UNKNOWN);

        MockGlobalQuake gq = new MockGlobalQuake();
        FakeAnalysis analysis = new FakeAnalysis(absStation);
        gq.analysis = analysis;

        StubDataRecord sdr = new StubDataRecord(new DataHeader(1, 'A', false));

        System.out.println(sdr);

        // normal run
        sdr.setSampleRate(-1);
        analysis.analyse(sdr);
        assertEquals(analysis.getLastRecord() / 1000, 946684800);

        // gap reset
        sdr.setBtime(new Btime(2000, 1, 0, 0, 1, 0));
        analysis.setGapThreshold(100);
        analysis.analyse(sdr);
        assertEquals(analysis.getLastRecord() / 1000, 946684801);

        // first log
        sdr.setBtime(new Btime(2000, 1, 0, 0, 2, 0));
        sdr.decompressable = false;
        analysis.analyse(sdr);
        assertEquals(analysis.getLastRecord() / 1000, 946684802);

        // second log
        sdr.decompressable = true;
        sdr.setBtime(new Btime(2000, 1, 0, 0, 3, 0));
        sdr.decompressed = null;
        analysis.analyse(sdr);
        assertEquals(analysis.getLastRecord() / 1000, 946684803);

        // for cycle
        sdr.setBtime(new Btime(2000, 1, 0, 0, 4, 0));
        sdr.decompressed = new int[]{1, 2};
        analysis.analyse(sdr);
        assertEquals(analysis.getLastRecord() / 1000, 946684804);

        System.out.println("Test testDecode passed.");
    }

    @Test
    public void testFullReset(){
        AbstractStation absStation = new GlobalStation("", "", "", "", 5, 5, 5, 5, null, -1, InputType.UNKNOWN);
        FakeAnalysis analysis = new FakeAnalysis(absStation);

        analysis.fullReset();
        assertEquals(analysis.getLastRecord(), 0);

        System.out.println("Test testFullReset passed.");
    }

    @Test
    public void testSampleRate(){
        AbstractStation absStation = new GlobalStation("", "", "", "", 5, 5, 5, 5, null, -1, InputType.UNKNOWN);
        FakeAnalysis analysis = new FakeAnalysis(absStation);

        assertEquals(analysis.getSampleRate(), -1, 0.001);

        System.out.println("Test testSampleRate passed.");
    }

    @Test
    public void testLatestEvent(){
        AbstractStation absStation = new GlobalStation("", "", "", "", 5, 5, 5, 5, null, -1, InputType.UNKNOWN);
        FakeAnalysis analysis = new FakeAnalysis(absStation);
        WaveformBuffer waveformBuffer = new WaveformBuffer(20, 20, false);
        Event event = new Event(analysis, 300, waveformBuffer, true);

        assertEquals(analysis.getDetectedEvents().size(), 0);
        assertNull(analysis.getLatestEvent());

        analysis.getDetectedEvents().add(0, event);

        assertEquals(analysis.getDetectedEvents().size(), 1);
        assertNotNull(analysis.getLatestEvent());

        System.out.println("Test testLatestEvent passed.");
    }

    @Test
    public void testNumRecords(){
        AbstractStation absStation = new GlobalStation("", "", "", "", 5, 5, 5, 5, null, -1, InputType.UNKNOWN);
        FakeAnalysis analysis = new FakeAnalysis(absStation);

        analysis.setNumRecords(23);
        assertEquals(analysis.getNumRecords(),23);

        System.out.println("Test testNumRecords passed.");
    }

    @Test
    public void testStatus(){
        AbstractStation absStation = new GlobalStation("", "", "", "", 5, 5, 5, 5, null, -1, InputType.UNKNOWN);
        FakeAnalysis analysis = new FakeAnalysis(absStation);

        analysis.setStatus((byte) 100);
        assertEquals(analysis.getStatus(), (byte) 100);

        System.out.println("Test testStatus passed.");
    }

    @Test
    public void testWaveformBuffer(){
        AbstractStation absStation = new GlobalStation("", "", "", "", 5, 5, 5, 5, null, -1, InputType.UNKNOWN);
        FakeAnalysis analysis = new FakeAnalysis(absStation);

        assertEquals(analysis.getWaveformBuffer(), null);

        System.out.println("Test testWaveformBuffer passed.");
    }
}
