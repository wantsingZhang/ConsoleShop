
import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException{
        boolean bo=true;
        while(bo) {
            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();

            System.out.println("请输入密码：");
            String password = sc.next();

           // File file = new File("D:\\IdeaProjects\\ConsoleShop\\src\\users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");
            InputStream inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel();
            User users[] = readExcel.readExcel(in);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    System.out.println("登录成功！");
                    bo= false;
                    ReadProductExcel readProductExcel=new ReadProductExcel();
                    Product products=readProductExcel.getProductById(,inPro);
                    for (Product product:products){
                        System.out.print(product.getpID());
                        System.out.print("\t"+product.getpName());
                        System.out.print("\t\t"+product.getPrice());
                        System.out.println("\t\t"+product.getpDesc());


                    }
                 int count=0;
                  Product productes[]=new Product[4];
                    System.out.println("请输入商品Id，将其加入购物车");
                    String pId=sc.next();
                    ReadProductExcel readProductExcel1=new ReadProductExcel();
                    inPro=null;
                    inPro=Class.forName("Test").getResourceAsStream("/product.xlsx");
                    Product product=readProductExcel.getProductById(pId,inPro);
                    if (product!=null){
                        productes[count++]=product;
                    }
                    break;

                } else {
                    System.out.println("登录失败！");
                }

            }

        }
    }
}
