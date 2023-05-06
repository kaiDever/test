package org.example.designModle;

public class PeopleTwo implements Observer{
    ObserverAble observerAble;

    public PeopleTwo(ObserverAble observerAble) {
        this.observerAble = observerAble;
        observerAble.addRegister(this);
    }

    @Override
    public void updateInformation(String name,String email) {
        perform(name,email);
    }
    public void perform(String name,String email){
        System.out.println("我是peopleTwo，收到了！！！");
        System.out.println("name"+name+","+"email"+email);
    }
}
