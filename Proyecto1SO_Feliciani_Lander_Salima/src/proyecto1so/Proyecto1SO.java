/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1so;

import gui.DashboardMenu;

/**
 *
 * @author berna
 */
public class Proyecto1SO {
    public static void main(String[] args) throws InterruptedException {
        // Configurar la simulación
        SimulationController controller = new SimulationController();
        int [][] workersCant = controller.configureSimulation();

        // Crear almacenamiento para las compañías
        Storage hpStorage = new Storage();
        Storage appleStorage = new Storage();
        Storage dellStorage = new Storage();

        // Crear las compañías
        Company hp = new Company("HP", hpStorage, controller.getMaxWorkersPerCompany());
        Company apple = new Company("Apple", appleStorage, controller.getMaxWorkersPerCompany());
        Company dell = new Company("Dell", dellStorage, controller.getMaxWorkersPerCompany());
        
        controller.addPmDirToCompany(apple);
        controller.addPmDirToCompany(hp);
        controller.addPmDirToCompany(dell);
        

        // Agregar trabajadores y ensambladores
        
        for (int i = 0; i < workersCant.length; i++) {
            if(i == 0){
            controller.addWorkersToCompany(workersCant[i], apple);
            }
            else if(i == 1){
            controller.addWorkersToCompany(workersCant[i], dell);
            }
            else if(i == 2){
            controller.addWorkersToCompany(workersCant[i], hp);
            }
        }
        DashboardMenu dashboard = new DashboardMenu(controller, apple, hp, dell);
        dashboard.setVisible(true);

        // Crear el gráfico de utilidad
        UtilityGraph graph = new UtilityGraph("Ganancias de HP, Apple y Dell");
        graph.display();  // Mostrar la gráfica


        // Simulación de los días
        int simulationDays = 100;
        for (int day = 1; day <= simulationDays; day++) {
            // Obtener las ganancias de cada compañía
            double hpProfit = hp.getGrossIncome() - hp.getOperationalCosts();
            double appleProfit = apple.getGrossIncome() - apple.getOperationalCosts();
            double dellProfit = dell.getGrossIncome() - dell.getOperationalCosts();

            // Actualizar el gráfico
            graph.updateGraph(day, hpProfit, appleProfit, dellProfit);

            // Simular un día de espera
            Thread.sleep(controller.getDayDuration() * 1000);
        }
    }
}
