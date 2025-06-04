package bench.benchmark.cpu;

import bench.IBenchmark;
import timing.ITimer;
import timing.Timer;

public class CPUFixedPoint implements IBenchmark {
    public static int workload;
    @Override
    public void cancel() {

    }

    @Override
    public void clean() {

    }

    @Override
    public void initialize(Object parameters) {
        workload = (Integer) parameters;
    }

    @Override
    public void warmup() {

    }

    @Override
    public void warmup(Object parameters) {

    }

    @Override
    public void run() {

    }

    @Override
    public void run(Object parameters) {
        ITimer timer = new Timer();
        switch ((Integer)parameters){
            case 0:
                timer.start();
                arithmetic();
                long t= timer.stop();
                long mops= (long) ((29.0*workload)/t*1e6);
                System.out.println(mops);
                break;
                case 1:
                    timer.start();
                    branchtest();
                    long t1= timer.stop();
                    long mops1= (long) ((11.0*workload)/t1*1e6);
                    System.out.println(mops1);
                    break;
                    case 2:
                        timer.start();
                        arrayacces();
                        long t2= timer.stop();
                        long mops2= (long) ((6.0*workload)/t2*1e6);
                        System.out.println(mops2);
                        break;
            default:
                break;

        }
    }

    @Override
    public String getResult() {
        return "";
    }
    public void arithmetic() {
        int j=1,k=2,l=3;
        int[] res= new int[workload];
        int[] num={0,1,2,3};
        for(int i=0;i<workload;i++) {
            j = num[1] * (k - j) * (l - k);
            k = num[3] * k - (l - j) * k;
            l = (l - k) * (num[2] + j);
            res[l - 2] = j + k + l;
            res[k - 2] = j * k * l;
        }
    }
    public void branchtest(){
        int[] num={0,1,2,3};
        int j=0;
        for(int i=0;i<workload;i++) {
            if(j==1){
                j=num[2];
            }else j=num[3];
            if(j>2){
                j=num[0];
            }else j=num[1];
            if(j<1){
                j=num[1];
            }else j=num[0];
        }
    }
    public void arrayacces(){
        int[] a=new int[workload];
        int[] b=new int[workload];
        int[] c=new int[workload];
        for(int i=0;i<workload;i++) {
            c[i]=a[b[i]];
        }
    }
}
