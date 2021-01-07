import java.util.Random;

public class cc {



    public static void main(String[] args) {
        String s = cc.nextForsint();
        System.out.println(s);
    }
    public static String nextForsint() {
        StringBuffer sb = new StringBuffer();
        String cc = "";
        Random random = new Random();
        for (int i = 0; i < 4; i++) {



            sb.append(random.nextInt(9));

        }
        return sb.toString();

    }
}
