package bench.benchmark.cpu;

import bench.IBenchmark;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class CPUDigitsOfPi implements IBenchmark {
    private static final BigDecimal TWO = new BigDecimal("2");
    private static final BigDecimal FOUR = new BigDecimal("4");
    private static final BigDecimal FIVE = new BigDecimal("5");
    private static final BigDecimal TWO_THIRTY9 = new BigDecimal("239");
    private static final BigDecimal ONE_QUARTER = new BigDecimal("0.25");

    @Override
    public void initialize(Object parameters) {

    }

    @Override
    public void warmup() {
    }

    @Override
    public void warmup(Object parameters) {
        int parameter = (Integer) parameters;
        switch (parameter) {
            case 0:
                computePIMagically(1000000, 10);
                break;
            case 1:
                computePiByGuessing(10000);
                break;
            case 2:
                computePiUsingMaths();
                break;
            default:
                throw new IllegalArgumentException("Option is invalid must be 0,1,2");
        }
    }

    @Override
    public void run() {

    }
    public void run(Object params){
        System.out.println(bd(params));
    }

    public BigDecimal bd(Object parameters) {
        int parameter = (Integer) parameters;
        switch (parameter) {
            case 0:
                BigDecimal bi=computePIMagically(1000000, 10);
                return bi;
            case 1:
                BigDecimal bi1=computePiByGuessing(10000);
                return bi1;
            case 2:
                BigDecimal bi2=computePiUsingMaths();
                return bi2;
            default:
                throw new IllegalArgumentException("Option is invalid must be 0,1,2");
        }
    }

    @Override
    public void cancel() {

    }

    @Override
    public void clean() {

    }

    public static BigDecimal computePIMagically(int terms, int digits) {
        MathContext mc = new MathContext(digits + 5, RoundingMode.HALF_UP);
        BigDecimal sum = BigDecimal.ZERO;

        for (int k = 0; k < terms; k++) {
            BigDecimal term = BigDecimal.ONE.divide(
                    new BigDecimal(2 * k + 1), mc
            );

            if (k % 2 == 0) {
                sum = sum.add(term, mc);
            } else {
                sum = sum.subtract(term, mc);
            }

        }
        BigDecimal pi = sum.multiply(new BigDecimal(4), mc);
        return pi.setScale(digits, RoundingMode.HALF_UP);
    }


    public static BigDecimal computePiByGuessing(int digits) {
        int scale = digits + 10;
        MathContext mc = new MathContext(scale, RoundingMode.HALF_UP);

        BigDecimal a = BigDecimal.ONE;
        BigDecimal b = sqrt(BigDecimal.ONE.divide(TWO, mc), mc);
        BigDecimal t = ONE_QUARTER;
        BigDecimal p = BigDecimal.ONE;

        // Enough iterations for thousands of digits – 7–8 is already plenty.
        for (int i = 0; i < 10; i++) {
            BigDecimal aNext = a.add(b, mc).divide(TWO, mc);
            BigDecimal bNext = sqrt(a.multiply(b, mc), mc);
            BigDecimal diff = a.subtract(aNext, mc);

            t = t.subtract(p.multiply(diff.multiply(diff, mc), mc), mc);
            a = aNext;
            b = bNext;
            p = p.multiply(TWO, mc);
        }

        BigDecimal sum = a.add(b, mc);
        BigDecimal pi = sum.multiply(sum, mc).divide(FOUR.multiply(t, mc), mc);

        return pi.setScale(digits, RoundingMode.HALF_UP);

    }

    private static BigDecimal sqrt(BigDecimal value, MathContext mc) {
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()), mc); // first guess
        BigDecimal two = TWO;
        BigDecimal eps = BigDecimal.ONE.scaleByPowerOfTen(-mc.getPrecision());

        while (true) {
            BigDecimal xNext = x.add(value.divide(x, mc), mc).divide(two, mc);
            if (x.subtract(xNext, mc).abs().compareTo(eps) <= 0) break;
            x = xNext;
        }
        return x;
    }


    public static BigDecimal computePiUsingMaths() {
        BigDecimal pi = new BigDecimal(0);
        for (float i = 0; i < 1000000; i++) {
            pi = pi.add(BigDecimal.valueOf(4 * (1 - (i % 2) * 2) / (2 * i + 1)));
        }
        return pi;
    }

    @Override
    public String getResult() {
        return "";
    }
}
