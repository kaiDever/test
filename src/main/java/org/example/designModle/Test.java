package org.example.designModle;

public class Test {
    public static void main(String[] args) {
        WeatherData topic = new WeatherData();
        PeopleOne observer1 = new PeopleOne(topic);
        PeopleTwo observer2 = new PeopleTwo(topic);
        topic.setChangeInformation("fankai","2234");
        observer1.cancel();
        topic.setChangeInformation("liudehua","bbq");
    }
}
