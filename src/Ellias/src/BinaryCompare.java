import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ellias
 * Date: 2016/03/22 0022
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
public class BinaryCompare {
    public static void main(String[] args) {
        while(true) {
            try {
                System.out.println("文件1:");
                String file1 = readLine();
                System.out.println("文件2:");
                String file2 = readLine();
                File f1 = new File(file1);
                File f2 = new File(file2);
                if(!f1.exists() || !f2.exists()) {
                    System.out.println("有文件不存在");
                    continue;
                }
                if(f1.length() != f2.length()) {
                    System.out.println("文件大小不一致");
                    continue;
                }
                InputStream is1 = new FileInputStream(f1);
                InputStream is2 = new FileInputStream(f2);
                int i1, i2, i = 0;
                while(true) {
                    i1 = is1.read();
                    i2 = is2.read();
                    if(i1 < 0) {
                        break;
                    }
                    if(i1 != i2) {
                        System.out.println("文件不一致:" + i);
                        break;
                    }
                    i++;
                }
                System.out.println("================");
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String readLine() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String ret = br.readLine();
        return ret;
    }

}
