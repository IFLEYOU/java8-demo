package com.beyond.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDemo1 {
    public static void main(String[] args) {
        test1();
    }

    public static void test1 (){
        Sdudent s1 = new Sdudent(1,"A",12);
        Sdudent s2 = new Sdudent(2,"B",21);
        Sdudent s3 = new Sdudent(3,"C",31);
        List<Sdudent> list1 = new ArrayList<>();
        list1.add(s1);
        list1.add(s2);
        list1.add(s3);

        Sdudent s4 = new Sdudent(4,"D",40);
        Sdudent s5 = new Sdudent(5,"E",50);
        List<Sdudent> list2 = new ArrayList<>();
        list2.add(s1);
        list2.add(s4);
        list2.add(s5);

        Sdudent s6 = new Sdudent(6,"F",60);
        Sdudent s7 = new Sdudent(7,"G",70);
        List<Sdudent> list3 = new ArrayList<>();
        list3.add(s1);
        list3.add(s6);
        list3.add(s7);

        Map<Integer, List<Sdudent>> map = new HashMap<>();
        map.put(1,list1);
        map.put(2,list2);
        map.put(3,list3);

        // 交集
        List<Sdudent> outList1 = list1.stream().filter(list -> list2.contains(list)).collect(Collectors.toList());
        System.out.println("交集start");
        outList1.stream().forEach(out -> System.out.println(out.getId() + ";" + out.getName() + ";" + out.getAge()));

        // 差集
        List<Sdudent> outList2 = list1.stream().filter(list -> !list2.contains(list)).collect(Collectors.toList());
        System.out.println("差集start");
        outList2.stream().forEach(out -> System.out.println(out.getId() + ";" + out.getName() + ";" + out.getAge()));

        // 差集
        List<Sdudent> outList3 = list2.stream().filter(list -> !list1.contains(list)).collect(Collectors.toList());
        System.out.println("差集start");
        outList3.stream().forEach(out -> System.out.println(out.getId() + ";" + out.getName() + ";" + out.getAge()));

        // 差集合并
        outList2.addAll(outList3);
        System.out.println("差集合并start");
        outList2.stream().forEach(out -> System.out.println(out.getId() + ";" + out.getName() + ";" + out.getAge()));

        // 并集去重复
        List<Sdudent> outList5 = Stream.of(list1, list2, list3).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        System.out.println("并集start");
        outList5.stream().forEach(out -> System.out.println(out.getId() + ";" + out.getName() + ";" + out.getAge()));

        // 并集
        List<Sdudent> outList6 = map.values().stream().flatMap(Collection::stream).distinct().collect(Collectors.toList());
        System.out.println("并集start");
        outList6.stream().forEach(out -> System.out.println(out.getId() + ";" + out.getName() + ";" + out.getAge()));

    }

    public static void test2 (){
        List<Data> data1 = new ArrayList<>();
        data1.add(new Data(7, 15));
        data1.add(new Data(2, 17));
        data1.add(new Data(2, 17));

        List<Data> data2 = new ArrayList<>();
        data2.add(new Data(1, 11));
        data2.add(new Data(10, 9));
        data2.add(new Data(10, 9));
        data2.add(new Data(7, 15));
        data2.add(new Data(2, 17));
        data2.add(new Data(2, 17));

        List<Data> result = data2.stream()
                .filter(d1 ->
                        data1.stream()
                                .noneMatch(d2 -> Objects.equals(d1.getId(), d2.getId()))
                ).collect(Collectors.toList());
        System.out.println(result);
    }

    public static class Sdudent {
        int id;
        String name;
        int age;

        public Sdudent(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static class Data {
        int id;
        int num2;

        public Data(int id, int num2) {
            this.id = id;
            this.num2 = num2;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNum2() {
            return num2;
        }

        public void setNum2(int num2) {
            this.num2 = num2;
        }
    }
}
