package authority.util;

public class CreateStudentScript {
    public static void main(String[] args) {
        createAdmin();
    }

    private static void createStudent(){
        for (int i=2;i<200;i++){
            System.out.println("INSERT INTO `edu`.`student` (`id`, `studentNo`, `department_id`) VALUES (" +
                    i +
                    ", '20180" +
                    (int)(Math.random()*(1499)+1000) +
                    "', " +
                    (int)(Math.random()*(3)+1) +
                    ");");
        }
    }

    private static void createTeacher(){
        for (int i=1,counter=1000;i<100;i++,counter++){
            System.out.println("call insertTeacherValues(" +
                    "200" +
                    ",'37" +
                    counter +
                    "'," +
                    (int)(Math.random()*(3)+1) +
                    "," +
                    (int)(Math.random()*(3)+1) +
                    "," +
                    (int)(Math.random()*(3)+1) +
                    ");");
        }
    }

    private static void createActor(){
        String xing = "赵钱孙李周吴郑王" +
                "冯陈褚卫蒋沈韩杨" +
                "朱秦尤许何吕施张" +
                "孔曹严华金魏陶姜" +
                "戚谢邹喻柏水窦章" +
                "云苏潘葛奚范彭郎" +
                "鲁韦昌马苗凤花方" +
                "俞任袁柳酆鲍史唐" +
                "费廉岑薛雷贺倪汤";

        int len = xing.length();
        for (int i=0;i<900;i++){
            System.out.println("insert into actor(name,IDCard,phoneNumber) values('" +
                    xing.charAt((int)(Math.random()*(len)+0)) +
                    xing.charAt((int)(Math.random()*(len)+0)) +
                    xing.charAt((int)(Math.random()*(len)+0)) +
                    "','371" +
                    //位数对了就行，而且一般不会重复
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    (int)(Math.random()*(9)+1) +
                    "','" +
                    "15" +
                    (int)(Math.random()*(9)+0) +
                    (int)(Math.random()*(9)+0) +
                    (int)(Math.random()*(9)+0) +
                    (int)(Math.random()*(9)+0) +
                    (int)(Math.random()*(9)+0) +
                    (int)(Math.random()*(9)+0) +
                    (int)(Math.random()*(9)+0) +
                    "" +
                    (int)(Math.random()*(9)+0) +
                    "');");
        }
    }

    public static void createAdmin(){
        for (int i = 0,counter=300;i<20;i++,counter++){
            System.out.println("call insertAdminValues(200,'" +
                    "7" +
                    counter +
                    "'," +
                    (int)(Math.random()*(3)+1) +
                    ");");
        }
    }
}
