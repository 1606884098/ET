package com.se.java.base.javabase.base3.oop.oop4thread.thread3ThreadStop;

/** ͨ��״̬λ���ж� */
public class ThreadFlagStop extends Thread {
  public volatile static boolean flag = true;

  public static void main(String[] args) throws InterruptedException {
    new Thread(() -> {
      try {
        while (flag) { // �ж��Ƿ�����
          System.out.println("������");
          Thread.sleep(1000L);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    // 3��֮�󣬽�״̬��־��ΪFalse��������������
    Thread.sleep(3000L);
    flag = false;
    System.out.println("�������н���");
  }
}
