package kmqc;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include="simulator.h", link="qsimulator")
@Namespace("qsimulator")
public class QsimulatorLib {

  @Name("::std::vector<uint32_t>")
  public static class UInt32Vector extends Pointer {

    static {
      Loader.load();
    }

    public UInt32Vector() {
      allocate();
    }

    public UInt32Vector(long n) {
      allocate(n);
    }

    public UInt32Vector(Pointer p)	{
      super(p);
    }

    private native void allocate();
    private native void allocate(@Cast("size_t") long n);

    public native @Cast("size_t") long size();
    public native void resize(@Cast("size_t") long n);

    @Index @ByRef public native int get(@Cast("size_t") long i);
    public native UInt32Vector put(@Cast("size_t") long i, int value);
  }

  public static class Simulator extends Pointer {

    static {
      Loader.load();
    }

    public Simulator(long nreg, long dim) {
      allocate(nreg, dim);
    }

    private native void allocate(
      @Cast("uint64_t") long nreg, @Cast("uint64_t") long dim);

    public native void measure(
      @Cast("uint64_t") long idx_qreg, @Cast("uint64_t") long idx_creg);
      
    public native @Cast("uint32_t") int get_creg(@Cast("uint64_t") long idx_creg);
  }


  public static void main(String[] args) {
    Simulator qsim = new Simulator(1, 2);
    qsim.measure(0, 0);
    System.out.println("vector result: " + qsim.get_creg(0));

    try {
      qsim.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}