/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;

/**
 *
 * @author berna
 */

public class SimulationController {
    private int dayDuration;
    private int maxWorkersPerCompany;
    private int daysToDelivery;

    public int[][] configureSimulation() {
        // Leer la duración de un día en la simulación
        dayDuration = 3;

        // Leer la cantidad máxima de trabajadores por compañía
        maxWorkersPerCompany = 21;

        // Leer la cantidad de días para una entrega
        daysToDelivery = 4;

        int applePlate = 1;
        int appleCpu = 1;
        int appleRam = 1;
        int applePsu = 1;
        int appleGpu = 1;
        int appleAss = 1;
        
        int dellPlate = 1;
        int dellCpu = 1;
        int dellRam = 1;
        int dellPsu = 1;
        int dellGpu = 1;
        int dellAss = 1;
        
        int hpPlate = 1;
        int hpCpu = 1;
        int hpRam = 1;
        int hpPsu = 1;
        int hpGpu = 1;
        int hpAss = 1;
        
        int[] workersCantApple = new int[6];
        int[] workersCantHp = new int[6];
        int[] workersCantDell = new int[6];
        
        workersCantApple[0] = applePlate;
        workersCantApple[1] = appleCpu;
        workersCantApple[2] = appleRam;
        workersCantApple[3] = applePsu;
        workersCantApple[4] = appleGpu;
        workersCantApple[5] = appleAss;
        
        workersCantHp[0] = dellPlate;
        workersCantHp[1] = dellCpu;
        workersCantHp[2] = dellRam;
        workersCantHp[3] = dellPsu;
        workersCantHp[4] = dellGpu;
        workersCantHp[5] = dellAss;
        
        workersCantDell[0] = hpPlate;
        workersCantDell[1] = hpCpu;
        workersCantDell[2] = hpRam;
        workersCantDell[3] = hpPsu;
        workersCantDell[4] = hpGpu;
        workersCantDell[5] = hpAss;
        
        int[][] workersCant = {workersCantApple, workersCantDell, workersCantHp};
        // Aquí podrías configurar la simulación
        System.out.println("Configuración completada.");
        return workersCant;
    }
    

    public int getDayDuration() {
        return dayDuration;
    }

    public int getMaxWorkersPerCompany() {
        return maxWorkersPerCompany;
    }

    public int getDaysToDelivery() {
        return daysToDelivery;
    }

    void addWorkersToCompany(int[] workerCantCompany, Company company) {
        String[] workerTags = {"Motherboard", "CPU", "RAM", "PowerSupply", "GraphicCard"};
        int[] workerTimes = {4000, 4000, 1000, 200, 2000 };
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < workerCantCompany[i]; j++) {
                company.addWorker(workerTags[i], workerTimes[i]* dayDuration);
            }
        }
        for (int i = 0; i < workerCantCompany[5]; i++) {
            company.addAssembler(2 * dayDuration);
        }
    }
    void addPmDirToCompany(Company company){
        Manager projectManager = new Manager(company,daysToDelivery, dayDuration);
        company.setProjectManager(projectManager);
        Director director = new Director(company, daysToDelivery, dayDuration);
        company.setDirector(director);
    }
}
