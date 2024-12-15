package globalquake.core.analysis;

import edu.iris.dmc.seedcodec.CodecException;
import edu.iris.dmc.seedcodec.DecompressedData;
import edu.iris.dmc.seedcodec.UnsupportedCompressionType;
import edu.sc.seis.seisFile.mseed.*;

public class StubDataRecord extends DataRecord {
    float sample_rate = 0;
    Btime time = new Btime(2000, 1, 0, 0, 0, 0);
    boolean decompressable = true;
    int[] decompressed = new int[0];
    public StubDataRecord(DataHeader header) {
        super(header);
        this.data = "".getBytes();
    }

    public Btime getLastSampleBtime() {
        return time;
    }
    public Btime getStartBtime() {
        return this.time;
    }

    public void setBtime(Btime t) {
        this.time = t;
    }

    @Override
    public float getSampleRate() {
        return this.sample_rate;
    }

    public void setSampleRate(float sr) {
        this.sample_rate = sr;
    }

    public String toString() {
        String s = super.toString();
        s = s + "    " + this.data.length + " bytes of data";
        return s;
    }

    public boolean isDecompressable() throws SeedFormatException {
        return decompressable;
    }

    public DecompressedData decompress() throws SeedFormatException, UnsupportedCompressionType, CodecException {
        DecompressedData d = new DecompressedData(decompressed);
        return d;
    }
}
