import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Secret {
    public static String encrypt(String str){
        try{
            char h[] = {'1', '2','3','4','5','6','7','8','9','0','a','b','c','d','e','f'};
            byte b[] = str.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(b);
            b = md.digest();
            int i = b.length;
            char[] c = new char[i*2];
            int j = 0;
            for (byte bt : b) {
                c[j++] = h[bt >>> 4 & 0xf];
                c[j++] = h[bt & 0xf];
            }
            return new String(c);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            throw new RuntimeException("加密错误" + e);
        }
    }
    public static void main(String[] args) {
        String str1 = encrypt("Hello,114514");
        String str2 = encrypt("Hello,114514");
        System.out.println(str2.equals(str1));
    }
}
