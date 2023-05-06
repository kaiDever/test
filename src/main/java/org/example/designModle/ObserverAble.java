package org.example.designModle;

/**
 * 主题类
 */
public interface ObserverAble {
    /**
     * 删除观察者
     */
    void removeRegister(Observer observer);

    /**
     * 添加观察者
     */
    void addRegister(Observer observer);

    /**
     * 通知观察者
     */
    void notifyRegister(String name,String email);
}
