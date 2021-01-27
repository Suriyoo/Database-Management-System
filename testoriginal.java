import java.sql.*;
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

        PreparedStatement ps;

        jdbcUtil u = new jdbcUtil();

        String sql = "select count(*) from Employees where id = ? and password = ?";

        System.out.println("**** Welcome to User Management System ****");
        System.out.println("Please input your Employee ID and Password to login");
        System.out.println("ID: ");
        int eid = s.nextInt();
        System.out.println("Password: ");
        int ep = s.nextInt();

        ps = u.createPreparedStatement(sql);
        ps.setInt(1, eid);
        ps.setInt(2, ep);

        int t = 0;

        ResultSet re = ps.executeQuery();
        while(re.next()) {
            t = re.getInt("count(*)");
        }

        u.close();

        if (t == 1) {
            String sqln = "select name from Employees where id =?";
            ps = u.createPreparedStatement(sqln);
            ps.setInt(1,eid);
            re = ps.executeQuery();
            String ename = null;

            while(re.next()) {
                ename = re.getString("name");
            }
            u.close();

            System.out.println("\n"+"Loading..."+"\n"+"\n");
            System.out.println("Dear "+ename+", You are already logged in as admin.");

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

                PreparedStatement p;

                switch (a) {
                    case 1:
                        System.out.println("Please enter the amount of users that you want to add");
                        int b = s.nextInt();
                        s.nextLine();// ref (https://blog.csdn.net/qq_40226526/article/details/104757165)
                        p = u.createPreparedStatement(sql1);
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

                        u.close();

                        System.out.println("Totally adds " + b + " users！");
                        continue;
                    case 2:
                        System.out.println("please enter the amount of users you want to delete");
                        int d = s.nextInt();
                        p = u.createPreparedStatement(sql2);
                        for (int i = 0; i < d; i++) {
                            System.out.println("please input the user's id");
                            id = s.nextInt();

                            p.setInt(1, id);
                            p.executeUpdate();
                            System.out.println("Delete Successful！");
                        }

                        u.close();

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
                                        System.out.println(new testoriginal().changeInfo(id, sql31,"name"));
                                        continue;

                                    case 2:
                                        System.out.println(new testoriginal().changeInfo(id, sql32,"password"));

                                        continue;
                                    case 3:
                                        System.out.println(new testoriginal().changeInfo(id, sql33,"sex"));

                                        continue;

                                    case 4:
                                        System.out.println(new testoriginal().changeInfo(id, sql34,"email"));

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
                                new testoriginal().searchInfo(1,sql41,"id");

                                break;
                            } else if (f == 2) {
                                new testoriginal().searchInfo(2,sql42,"name");

                                break;
                            } else if (f == 3) {
                                new testoriginal().searchInfo(3,sql43,"password");

                                break;
                            } else if (f == 4) {
                                new testoriginal().searchInfo(4,sql44,"sex");

                                break;
                            } else if (f == 5) {
                                new testoriginal().searchInfo(5,sql45,"email");
                                break;
                            } else {
                                System.out.println("Wrong input!");
                                continue;
                            }
                        }

                        continue;
                    case 5:
                        p = u.createPreparedStatement(sql5);
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

                        u.close();

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


    public String changeInfo(int id, String sql, String require) throws Exception {
        Scanner s = new Scanner(System.in);
        jdbcUtil u = new jdbcUtil();
        PreparedStatement p = u.createPreparedStatement(sql);
        System.out.println("please input the new "+require);
        String a = s.nextLine();

        p.setString(1, a);
        p.setInt(2, id);
        p.executeUpdate();

        u.close();

        return "Change Successful！";
    }

    public void searchInfo(int choice, String sql, String require) throws Exception {
        Scanner s = new Scanner(System.in);
        jdbcUtil u = new jdbcUtil();
        PreparedStatement p = u.createPreparedStatement(sql);
        System.out.println("Please input "+require);

        if(choice == 1) {
            int is = s.nextInt();
            p.setInt(1, is);
        }
        else{
            String is = s.nextLine();
            p.setString(1, is);
        }

        ResultSet r1 = p.executeQuery();


        while (r1.next()) {
            String i = r1.getString("id");
            String name = r1.getString("name");
            String password = r1.getString("password");
            String sex = r1.getString("sex");
            String email = r1.getString("email");
            System.out.println("ID: " + i + "  Name: " + name + "  Password: " + password + "  Sex: " + sex + "  Email: " + email);

        }

        u.close();

    }
}
