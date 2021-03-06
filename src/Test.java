
import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bo = true;
        boolean no = true;
        while (bo) {       //
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
                    bo = false;
                    ReadProductExcel readProductExcel = new ReadProductExcel();
                    Product products[] = readProductExcel.getAllProduct(inPro);
                    System.out.println("商品ID\t\t\t商品名称\t\t\t\t商品价格\t\t\t\t商品描述");
                    for (Product product : products) {
                        System.out.print(product.getpID());
                        System.out.print("\t" + product.getpName());
                        System.out.print("\t\t" + product.getPrice());
                        System.out.println("\t\t" + product.getpDesc());


                    }
                    while (no) {               //如果商品id错误，重新输入
                        int count = 0;

                        Product productes[] = new Product[8];
                        System.out.println("请输入商品Id，将其加入购物车");
                        String pId = sc.next();

                        ReadProductExcel readProductExcel1 = new ReadProductExcel();
                        inPro = null;
                        inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");
                        Product product = readProductExcel.getProductById(pId, inPro);
                        if (product != null) {
                            productes[count++] = product;
                            System.out.println("加入购物车成功！");
                            System.out.println("是否继续购物？0不继续，1继续，2查看购物车");
                            int xz=sc.nextInt();
                            if (xz ==0) {
                                no = false;
                                System.out.println("感谢您的使用！");
                            }else if(xz==1){
                                no =true;

                            }else if (xz==2){
                                no =false;
                                System.out.println("当前购物车商品如下");//".sout"自动打印
                                for (Product product1 : productes) {
                                    if (product1!=null)
                                        System.out.print(product.getpID());
                                    System.out.print("\t" + product.getpName());
                                    System.out.print("\t" + product.getPrice());
                                    System.out.println("\t" + product.getpDesc());
                                    System.out.println("是否继续购物？购物选1，不购物选0");
                                    int xuanz=sc.nextInt();
                                    if (xuanz==0){
                                       no= false;
                                        System.out.println("感谢您的使用！");
                                        break;
                                    }else {

                                        no=true;
                                        break;
                                    }
                                }

                            }
                        } else {
                            System.out.println("加入购物车失败，请检查商品ID！");
                        }
                    }
                    break;

                } else {
                    System.out.println("登录失败！");
                }

            }

        }
    }
}
