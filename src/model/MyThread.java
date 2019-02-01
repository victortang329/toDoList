package model;

public class MyThread extends Thread {
    public boolean pause = false;

    public void run(){
        while(true){
            synchronized (this){
                if (pause){
                    try {
                        wait();
                    } catch (InterruptedException e){
                        //caught interrupted exception
                    }
                }
            }
        }
    }

    public void pauseProgram(){
        this.pause = true;
    }

    public void resumeProgram(){
        this.pause = false;
    }
}
