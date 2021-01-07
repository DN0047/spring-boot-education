import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class cc{
  private Lock lock=  new ReentrantLock();
  private   Condition condition1 = lock.newCondition();
    private   Condition condition2 = lock.newCondition();
    private   Condition condition3 = lock.newCondition();
  private int w=1;
    public void a(){
        lock.lock();
        try {

        while (w!=1){

                condition1.await();

        }


        for (int i = 0; i <5 ; i++) {
            System.out.println(Thread.currentThread().getName()+"aaaa");
        }
        w=2;
        condition2.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void b(){
        lock.lock();
        try {

        while (w!=2){


                condition2.await();
            }

        for (int i = 0; i <10 ; i++) {
            System.out.println(Thread.currentThread().getName() + "bbb");
        }
        w=3;
        condition3.signal();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void c(){
        lock.lock();
        try {

        while (w!=3){


                condition3.await();

        }
        for (int i = 0; i <10 ; i++) {
            System.out.println(Thread.currentThread().getName() + "ccc");
        }
        w=1;
        condition1.signal();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}



public class ccc {




    public static void main(String[] args) {
        cc cc = new cc();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                cc.a();
            }

        },"a").start();

        new Thread(()->{
            for (int i = 0; i <10; i++) {
                cc.b();
            }

        },"b").start();

        new Thread(()->{

            for (int i = 0; i <10 ; i++) {
                cc.c();
            }
        },"c").start();

    }
}
