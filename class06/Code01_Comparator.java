package class06;

import java.util.Arrays;
import java.util.Comparator;

public class Code01_Comparator {
    /*
    要求： 编写代码演示比较器及其主要特点，分别实现，对学生id升序排序，对学生id降序排序，对学生先id升序排序，相同id按年龄降序排序
    思路：
        比较器的作用：应用于非常规对象的比较
        比较方法：返回负数则认为第一个数排在前面，返回正数则认为第二个数排在前面，返回零无所谓谁排在前面
        比较器的实现：
            1.定义一个类，然后继承比较器，传入比较类，例如： implements Comparator<Student>
            2.重写比较方法 @Override
            3.编写比较方法，注意不要写反
            4.写好比较器后，用Arrays.sort传入新比较器的方法重新比较
    */

    // 构建学生类，有id,姓名,年龄三个属性
    public static class Student{
        public int id;
        public String name;
        public int age;

        public Student(int id, String name, int age){
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }

    // 创建ID升序比较器
    public static class idAsComparator implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            // id升序，则如果o1<o2,返回o1(负数)
            return o1.id - o2.id;
        }
    }

    // 创建ID降序比较器
    public static class idDesComparator implements Comparator<Student>{
        @Override
        public int compare (Student o1, Student o2) {
            return o2.id - o1.id;
        }
    }

    // 创建id升序，相同id按年龄降序排序的比较器
    public static class idAsAgeDesComparator implements Comparator<Student> {
        @Override
        public int compare (Student o1, Student o2) {
            return o1.id != o2.id ? (o1.id - o2.id) : (o2.age - o1.age);
        }
    }



    public static void main(String[] args) {
        // 创建学生类，打印其id及年龄
        Student student1 = new Student(3, "HanLin", 25);
        Student student2 = new Student(2, "MaQiang", 26);
        Student student3 = new Student(2, "GaoAng", 27);
        Student student4 = new Student(1, "FuLong", 24);
        Student[] students = new Student[] {student1, student2, student3, student4};

        System.out.println("初始状态");
        for (int i = 0; i < students.length; i++) {
            Student s = students[i];
            System.out.println(s.id + " " + s.name + " " + s.age);
        }

        System.out.println("");
        System.out.println("ID升序排序");
        Arrays.sort(students, new idAsComparator());
        for (int i = 0; i < students.length; i++) {
            Student s = students[i];
            System.out.println(s.id + " " + s.name + " " + s.age);
        }

        System.out.println(" ");
        System.out.println("ID降序排序");
        Arrays.sort(students, new idDesComparator());
        for (int i = 0; i < students.length; i++) {
            Student s = students[i];
            System.out.println(s.id + " " + s.name + " " + s.age);
        }

        System.out.println(" ");
        System.out.println("ID升序年龄降序排序");
        Arrays.sort(students, new idAsAgeDesComparator());
        for (int i = 0; i < students.length; i++) {
            Student s = students[i];
            System.out.println(s.id + " " + s.name + " " + s.age);
        }
    }


}
