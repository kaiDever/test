package org.example.designModle;

public class PeopleOne implements Observer{
    private ObserverAble observerAble;

    public PeopleOne(ObserverAble observerAble) {
        this.observerAble = observerAble;
        observerAble.addRegister(this);
    }
    public void cancel(){
        this.observerAble.removeRegister(this);
    }

    public ObserverAble getObserverAble() {
        return observerAble;
    }

    public void remove(){
        this.observerAble.removeRegister(this);
    }

    @Override
    public void updateInformation(String name,String email) {
        perform(name,email);
    }
    public void perform(String name,String email){
        System.out.println("我是peopleOne，收到了！！！");
        System.out.println("name"+name+","+"email"+email);
    }
}
