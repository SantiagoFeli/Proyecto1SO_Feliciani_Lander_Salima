/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;

/**
 *
 * @author santi
 */
public class Worker extends Thread {
    private Company company;
    private String component;  // Tipo de componente que produce
    private int productionTime;
    private Storage storage;
    private boolean running;// New variable to store the salary
    private int hourly;
    private int salary;
    private int days;

    // Constructor
    public Worker(Company company, String component, int productionTime, Storage storage) {
        this.company = company;
        this.component = component;
        this.productionTime = productionTime;
        this.storage = storage;
        this.running = true;
    }

    // Método para detener el trabajador
    public void stopWorker() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(productionTime); // Simulate production
                switch (component) {
                    case "Motherboard":
                        storage.addMotherboard();
                        hourly = 20;
                        days = 4;
                        salary = (days*24) * hourly;
                        break;
                    case "CPU":
                        storage.addCPU();
                        hourly = 26;
                        days = 4;
                        salary = (days*24) * hourly;
                        break;
                    case "RAM":
                        storage.addRAM();
                        hourly = 40;
                        days = 1;
                        salary = (days*24) * hourly;
                        break;
                    case "PowerSupply":
                        storage.addPowerSupply();
                        hourly = 16;
                        days = 1;
                        salary = (2*24) * hourly;
                        break;
                    case "GraphicCard":
                        storage.addGraphicCard();
                        hourly = 34;
                        days = 2;
                        salary = (days*24) * hourly;
                        break;
                }
                System.out.println("Trabajador de " + company.getCompanyName() + " produjo " + component);
                payWorker();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void payWorker() {
        company.setOperationalCosts(company.getOperationalCosts() + salary);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(int productionTime) {
        this.productionTime = productionTime;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getHourly() {
        return hourly;
    }

    public void setHourly(int hourly) {
        this.hourly = hourly;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
    
}
