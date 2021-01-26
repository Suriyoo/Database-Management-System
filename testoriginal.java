import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class testoriginal {
    public static void main(String[] args) throws Exception {

        Scanner s = new Scanner(System.in);
        int id;
        String name;
        String password;
        String sex;
        String email;

        String sql1 = "insert into Users(name, password, sex, email) values (?,?,?,?)";
        String sql2 = "delete from Users where id = ?";
        String sql31 = "update Users set name = ? where id = ?";//pk cannot be changed
        String sql32 = "update Users set password = ? where id = ?";
        String sql33 = "update Users set sex = ? where id = ?";
        String sql34 = "update Users set email = ? where id = ?";
        String sql41 = "select * from  Users where id = ?";
        String sql42 = "select * from  Users where name = ?";
        String sql43 = "select * from  Users where password = ?";
        String sql44 = "select * from  Users where sex = ?";
        String sql45 = "select * from  Users where email = ?";
        String sql5 = "select * from Users";

        int a;
        boolean flag = true;

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys2021", "root", "***");

        PreparedStatement ps;

        String sql = "select count(*) from Employees where id = ? and password = ?";

        System.out.println("**** Welcome to User Management System ****");
        System.out.println("Please input your Employee ID and Password to login");
        System.out.println("ID: ");
        int eid = s.nextInt();
        System.out.println("Password: ");
        int ep = s.nextInt();

        ps = con.prepareStatement(sql);
        ps.setInt(1, eid);
        ps.setInt(2, ep);

        int t = 0;
        ResultSet re = ps.executeQuery();
        while(re.next()) {
            t = re.getInt("count(*)");
        }

        if (re != null) {
            re.close();
        }
        if (ps != null) {
            ps.close();
        }

        if (t == 1) {
            while (flag) {
                System.out.println("————MENU————");
                System.out.println("1. Add User" + "\n" + "2. Delete User" + "\n" + "3. Update User Information" + "\n" + "4. Search User" + "\n" + "5. Show All Users" + "\n" + "6. Quit");
                System.out.println("Please choose an option:");
                do {
                    while (!s.hasNextInt()) {//hasNextInt() determine whether it is a number, ! means opposite
                        System.out.println("Please enter a number!");
                        s.next();//end up while loop
                    }
                    a = s.nextInt();
                    if (a <= 0) {
                        System.out.println("Not a valid number! Please choose an option again:");
                    }
                } while (a <= 0);//firstly go 'do'. Then if a<0, continue. if a> 0, stop the loop

                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys2021", "root", "***");

                PreparedStatement p;

                switch (a) {
                    case 1:
                        System.out.println("Please enter the amount of users that you want to add");
                        int b = s.nextInt();
                        s.nextLine();// ref (https://blog.csdn.net/qq_40226526/article/details/104757165)
                        p = c.prepareStatement(sql1);
                        for (int i = 0; i < b; i++) {
                            System.out.println("please input a name");
                            name = s.nextLine();
                            System.out.println("please input the password");
                            password = s.next();
                            s.nextLine();
                            System.out.println("please input the sex");
                            sex = s.next();
                            s.nextLine();
                            System.out.println("please input an email");
                            email = s.next();

                            p.setString(1, name);
                            p.setString(2, password);
                            p.setString(3, sex);
                            p.setString(4, email);

                            p.executeUpdate();
                            System.out.println("Add Successful！");
                        }
                        if (p != null) {
                            p.close();
                        }
                        if (c != null) {
                            c.close();
                        }

                        System.out.println("Totally adds " + b + " users！");
                        continue;
                    case 2:
                        System.out.println("please enter the amount of users you want to delete");
                        int d = s.nextInt();
                        p = c.prepareStatement(sql2);
                        for (int i = 0; i < d; i++) {
                            System.out.println("please input the user's id");
                            id = s.nextInt();

                            p.setInt(1, id);
                            p.executeUpdate();
                            System.out.println("Delete Successful！");
                        }

                        if (p != null) {
                            p.close();
                        }
                        if (c != null) {
                            c.close();
                        }

                        System.out.println("Totally deletes " + d + " users！");

                        continue;
                    case 3:
                        System.out.println("please enter the amount of users you want to update");
                        int e = s.nextInt();

                        for (int i = 0; i < e; i++) {
                            System.out.println("please input the user's id");
                            id = s.nextInt();
                            boolean fl = true;

                            while (fl) {
                                System.out.println("Please choose the information you want to alter" + "\n" + "1. Change name" + "\n" + "2. Change password" + "\n" + "3. change sex" + "\n" + "4. Change email" + "\n" + "5. End up");
                                int info = s.nextInt();
                                s.nextLine();
                                switch (info) {
                                    case 1:
                                        p = c.prepareStatement(sql31);
                                        System.out.println("please input the new name");
                                        name = s.nextLine();

                                        p.setString(1, name);
                                        p.setInt(2, id);
                                        p.executeUpdate();

                                        System.out.println("Change Successful！");

                                        if (p != null) {
                                            p.close();
                                        }
                                        if (c != null) {
                                            c.close();
                                        }
                                        continue;

                                    case 2:
                                        p = c.prepareStatement(sql32);
                                        System.out.println("please input the new password");
                                        password = s.next();

                                        p.setInt(2, id);
                                        p.setString(1, password);
                                        p.executeUpdate();

                                        System.out.println("Change Successful！");

                                        if (p != null) {
                                            p.close();
                                        }
                                        if (c != null) {
                                            c.close();
                                        }

                                        continue;
                                    case 3:
                                        p = c.prepareStatement(sql33);
                                        System.out.println("please input the new sex");
                                        sex = s.next();

                                        p.setInt(2, id);
                                        p.setString(1, sex);
                                        p.executeUpdate();

                                        System.out.println("Change Successful！");

                                        if (p != null) {
                                            p.close();
                                        }
                                        if (c != null) {
                                            c.close();
                                        }

                                        continue;

                                    case 4:
                                        p = c.prepareStatement(sql34);
                                        System.out.println("please input a new email");
                                        email = s.next();

                                        p.setInt(2, id);
                                        p.setString(1, email);
                                        p.executeUpdate();

                                        System.out.println("Change Successful！");

                                        if (p != null) {
                                            p.close();
                                        }
                                        if (c != null) {
                                            c.close();
                                        }

                                        continue;
                                    case 5:
                                        fl = false;
                                        break;
                                    default:
                                        System.out.println("Wrong input! Please choose an option again!");
                                        continue;
                                }
                            }

                        }

                        System.out.println("Totally updates " + e + " users！");
                        continue;
                    case 4:
                        while (true) {
                            System.out.println("Please enter the keywords you want to search" + "\n" + "1. ID" + "\n" + "2. Name" + "\n" + "3. Password" + "\n" + "4. Sex" + "\n" + "5. Email");
                            int f = s.nextInt();
                            if (f == 1) {
                                p = c.prepareStatement(sql41);
                                System.out.println("Please enter an ID");
                                int is = s.nextInt();
                                p.setInt(1, is);
                                ResultSet r1 = p.executeQuery();
                                while (r1.next()) {
                                    String i = r1.getString("id");
                                    name = r1.getString("name");
                                    password = r1.getString("password");
                                    sex = r1.getString("sex");
                                    email = r1.getString("email");
                                    System.out.println("ID: " + i + "  Name: " + name + "  Password: " + password + "  Sex: " + sex + "  Email: " + email);
                                }

                                if (p != null) {
                                    p.close();
                                }
                                if (c != null) {
                                    c.close();
                                }
                                break;
                            } else if (f == 2) {
                                p = c.prepareStatement(sql42);
                                System.out.println("Please enter a name");
                                String is = s.nextLine();
                                p.setString(1, is);
                                ResultSet r1 = p.executeQuery();
                                while (r1.next()) {
                                    String i = r1.getString("id");
                                    name = r1.getString("name");
                                    password = r1.getString("password");
                                    sex = r1.getString("sex");
                                    email = r1.getString("email");
                                    System.out.println("ID: " + i + "  Name: " + name + "  Password: " + password + "  Sex: " + sex + "  Email: " + email);
                                }

                                if (p != null) {
                                    p.close();
                                }
                                if (c != null) {
                                    c.close();
                                }

                                break;
                            } else if (f == 3) {
                                p = c.prepareStatement(sql43);
                                System.out.println("Please enter a password");
                                String is = s.nextLine();
                                p.setString(1, is);
                                ResultSet r1 = p.executeQuery();
                                while (r1.next()) {
                                    String i = r1.getString("id");
                                    name = r1.getString("name");
                                    password = r1.getString("password");
                                    sex = r1.getString("sex");
                                    email = r1.getString("email");
                                    System.out.println("ID: " + i + "  Name: " + name + "  Password: " + password + "  Sex: " + sex + "  Email: " + email);
                                }

                                if (p != null) {
                                    p.close();
                                }
                                if (c != null) {
                                    c.close();
                                }

                                break;
                            } else if (f == 4) {
                                p = c.prepareStatement(sql44);
                                System.out.println("Please enter a sex");
                                String is = s.nextLine();
                                p.setString(1, is);
                                ResultSet r1 = p.executeQuery();
                                while (r1.next()) {
                                    String i = r1.getString("id");
                                    name = r1.getString("name");
                                    password = r1.getString("password");
                                    sex = r1.getString("sex");
                                    email = r1.getString("email");
                                    System.out.println("ID: " + i + "  Name: " + name + "  Password: " + password + "  Sex: " + sex + "  Email: " + email);
                                }

                                if (p != null) {
                                    p.close();
                                }
                                if (c != null) {
                                    c.close();
                                }

                                break;
                            } else if (f == 5) {
                                p = c.prepareStatement(sql45);
                                System.out.println("Please enter an email");
                                String is = s.nextLine();
                                p.setString(1, is);
                                ResultSet r1 = p.executeQuery();
                                while (r1.next()) {
                                    String i = r1.getString("id");
                                    name = r1.getString("name");
                                    password = r1.getString("password");
                                    sex = r1.getString("sex");
                                    email = r1.getString("email");
                                    System.out.println("ID: " + i + "  Name: " + name + "  Password: " + password + "  Sex: " + sex + "  Email: " + email);
                                }

                                if (p != null) {
                                    p.close();
                                }
                                if (c != null) {
                                    c.close();
                                }
                                break;
                            } else {
                                System.out.println("Wrong input!");
                                continue;
                            }
                        }

                        continue;
                    case 5:
                        p = c.prepareStatement(sql5);
                        ResultSet ro = p.executeQuery();

                        ro.last();
                        int ra = ro.getRow() + 1;
                        String[] A = new String[ra];


                        ResultSet r = p.executeQuery();
                        A[0] = "ID, Name, Password, Sex, Email";
                        int m = 1;

                        while (r.next()) {
                            String i = r.getString("id");
                            name = r.getString("name");
                            password = r.getString("password");
                            sex = r.getString("sex");
                            email = r.getString("email");
                            A[m] = i + "," + name + "," + password + "," + sex + "," + email;
                            m++;
                        }
                        new testoriginal().printResult(A);

                        if (p != null) {
                            p.close();
                        }
                        if (c != null) {
                            c.close();
                        }

                        continue;
                    case 6:
                        flag = false;
                        break;
                    default:
                        System.out.println("Wrong input! Please choose an option again:");
                        continue;
                }


            }


        }
        else{
            System.out.println("Employee not exist!");
        }
    }
    public void printResult(String[] A) {
        String[] tempA = A[0].split(",");
        int maxLen = tempA.length;
        for(int i = 1;i < A.length;i++) {
            tempA = A[i].split(",");
            if(maxLen < tempA.length)
                maxLen = tempA.length;
        }
        String[][] valueA = new String[A.length][maxLen];
        for(int i = 0;i < valueA.length;i++)
            for(int j = 0;j < valueA[0].length;j++)
                valueA[i][j] = "";

        for(int i = 0;i < A.length;i++) {
            tempA = A[i].split(",");
            for(int j = 0;j < tempA.length;j++)
                valueA[i][j] = tempA[j];
        }
        int[] maxJ = new int[maxLen];
        for(int j = 0;j < maxLen;j++) {
            for(int i = 0;i < A.length;i++) {
                if(valueA[i][j].length() > maxJ[j])
                    maxJ[j] = valueA[i][j].length();
            }
        }

        StringBuilder opera = new StringBuilder("+");
        for(int j = 0;j < maxJ.length;j++) {
            for(int k = 0;k < maxJ[j];k++)
                opera.append('-');
            opera.append('+');
        }
        for(int i = 0;i < valueA.length;i++) {
            System.out.println(opera);
            System.out.print("|");
            for(int j = 0;j < valueA[0].length;j++) {
                int len = maxJ[j] - valueA[i][j].length();
                String format = "";
                if(len == 0)
                    format = "" + "%s";
                else
                    format = "%" + len + "s";
                System.out.print(valueA[i][j]);
                System.out.printf(format, "");
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println(opera);
        return;
    }//build table format (https://blog.csdn.net/weixin_44833195/article/details/106371410?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_baidulandingword-6&spm=1001.2101.3001.4242)


}
