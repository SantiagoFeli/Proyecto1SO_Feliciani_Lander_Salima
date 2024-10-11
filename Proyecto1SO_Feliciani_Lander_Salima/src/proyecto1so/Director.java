/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;

import java.util.Random;
import javax.swing.JLabel;

/**
 *
 * @author santi
 */
public class Director extends Thread {
    private Company company;
    private int hourly;
    private int daysToDelivery;
    private int currentDay;
    private boolean running;
    private int dayDuration;
    private int dockedPay;
    private int halfFiveHour;
    private JLabel[] directorJlabels;
    private String status;

    public Director(Company company, int daysToDelivery, int dayDuration) {
        this.company = company;
        this.hourly = 60;
        this.daysToDelivery = daysToDelivery;
        this.currentDay = 0;
        this.running = true;
        this.dayDuration = dayDuration;
        this.halfFiveHour = ((dayDuration * 1000) / 48) + ((dayDuration * 1000) / 192);
        this.status = "";
    }

    public void stopDirector() {
        this.running = false;
    }

    @Override
    public void run() {
        Random rand = new Random();
        while (running) {
            try {
                if(currentDay >= daysToDelivery){
                    status = "Armando Pedidos";
                    updateDirectorJLabels();
                   Thread.sleep(dayDuration * 1000);
                    System.out.println("Director de " + company + ": Se enviaron computadoras al mercado.");
                    company.resetProduction();  // Enviar computadoras
                    currentDay = 0;  // Reiniciar el contador de días
                }else{
                    int hour = rand.nextInt(0, (dayDuration + 1) * 1000);
                    status = "Agarro al manager viendo anime";
                    updateDirectorJLabels();
                    Thread.sleep(hour);
                    if(company.getProjectManager().isAnime()){
                        System.out.println("Director de " + company + "Agarro al manager viendo anime");
                        company.setOperationalCosts(company.getOperationalCosts() - 100);
                        status = "Administrando";
                        updateDirectorJLabels();
                    }
                    Thread.sleep((dayDuration * 1000) - hour);
                    currentDay++;
                    company.setOperationalCosts(company.getOperationalCosts() + 24 *60);
                    System.out.println("Director de " + company + ": Día " + currentDay + " de la entrega.");
                    updateDirectorJLabels();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public JLabel[] getDirectorJlabels() {
        return directorJlabels;
    }

    public void setDirectorJlabels(JLabel[] directorJlabels) {
        this.directorJlabels = directorJlabels;
    }
    
    public void updateDirectorJLabels(){
        directorJlabels[0].setText(status);
        directorJlabels[1].setText(Double.toString(company.getGrossIncome()));
        directorJlabels[2].setText(Double.toString(company.getOperationalCosts()));
        directorJlabels[3].setText(Double.toString(company.getGrossIncome() - company.getOperationalCosts()));
        
    }

}
