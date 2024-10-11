/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;

/**
 *
 * @author santi
 */
public class Assembler extends Thread {
    private Company company;
    private int assemblyTime;
    private boolean running;
    private int hourly;
    private int salary;
    private int days;

    public Assembler(Company company, int assemblyTime) {
        this.company = company;
        this.assemblyTime = assemblyTime;
        this.running = true;
    }

    public void stopAssembler() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(assemblyTime * 1000);  // Simular ensamblaje
                company.assembleStandardComputer();
                hourly = 20;
                days = assemblyTime/(assemblyTime/2);
                salary = (days*24) * hourly;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            payAssembler();
        }
    }

    private void payAssembler() {
        company.setOperationalCosts(company.getOperationalCosts() + salary);
    }
}
