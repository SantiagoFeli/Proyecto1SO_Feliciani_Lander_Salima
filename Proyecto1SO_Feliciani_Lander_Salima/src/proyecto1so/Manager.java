/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;

import javax.swing.JLabel;

/**
 *
 * @author santi
 */
public class Manager extends Thread {
    private Company company;
    private int daysToDelivery;
    private int currentDay;
    private boolean running;
    private int dayDuration;
    private int contChangeTime;
    private int semiWorkingTime;
    private boolean anime;
    private JLabel[] managerLabels;

    public Manager(Company company, int daysToDelivery, int dayDuration) {
        this.company = company;
        this.daysToDelivery = daysToDelivery;
        this.currentDay = 0;
        this.running = true;
        this.dayDuration = dayDuration;
        this.contChangeTime = (dayDuration * 1000) / 3;
        this.semiWorkingTime = contChangeTime * 2;
        this.anime = false;
        this.managerLabels = null;
    }

    public void stopManager() {
        this.running = false;
    }

    @Override
    public void run() {
        int timeOfDay = 0;
        while (running) {
            try {
                if(timeOfDay < semiWorkingTime){
                    updateLabels();
                    //simulacion de 30min viendo anime 30min trabajando
                    anime = true;
                    Thread.sleep((dayDuration * 1000) / 48);//viendo anime
                    anime = false;
                    Thread.sleep((dayDuration * 1000) / 48);//trabajando
                    timeOfDay += (dayDuration * 1000) / 24; //Se suma 1 hora de trabajo de dia
                }
                else{
                    Thread.sleep(contChangeTime);
                    updateLabels();
                    currentDay++;
                    System.out.println("Manager de " + company + ": Día " + currentDay + " completado.");
                    timeOfDay = 0;
                    
                }
                company.setOperationalCosts(company.getOperationalCosts() + 24 * 40);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isAnime() {
        return anime;
    }

    public void setAnime(boolean anime) {
        this.anime = anime;
    }

    public JLabel[] getManagerLabels() {
        return managerLabels;
    }

    public void setManagerLabels(JLabel[] managerLabels) {
        this.managerLabels = managerLabels;
    }
    
    public void updateLabels() { 
        if(anime){
           managerLabels[0].setText("Anime"); 
        }
        else{
           managerLabels[0].setText("Revisando Avance");  
        }
        
        managerLabels[1].setText(Integer.toString(daysToDelivery - currentDay));
    }
    
}
