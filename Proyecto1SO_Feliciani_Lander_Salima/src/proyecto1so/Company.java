/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;

import javax.swing.JLabel;

/**
 *
 * @author juani
 */
public class Company {
    private String companyName;
    private Storage storage;
    private int maxWorkers;
    private int computersProduced;
    private int currentComputersProduced;
    private int computersWithGraphics;
    private double grossIncome;
    private double operationalCosts;

    // Arreglos fijos para los trabajadores y ensambladores
    private Worker[] workers;
    private Assembler[] assemblers;
    private int workerCount;
    private int assemblerCount;
    private Manager projectManager;
    private Director director;

    // Constructor
    public Company(String name, Storage storage, int maxWorkers) {
        this.companyName = name;
        this.storage = storage;
        this.maxWorkers = maxWorkers;
        this.computersProduced = 0;
        this.currentComputersProduced = 0;
        this.computersWithGraphics = 0;
        this.grossIncome = 0;
        this.operationalCosts = 0;
        this.workers = new Worker[maxWorkers];  // Inicializar el arreglo de trabajadores
        this.assemblers = new Assembler[maxWorkers];  // Inicializar el arreglo de ensambladores
        this.workerCount = 0;
        this.assemblerCount = 0;
        this.projectManager = null;
        this.director = null;
    }

    // Agregar un trabajador si no se ha alcanzado el límite
    public void addWorker(String component, int productionTime) {
        int sum = workerCount + assemblerCount;
        if (sum < maxWorkers) {
            Worker worker = new Worker(this, component, productionTime, storage);
            workers[workerCount] = worker;
            worker.start();
            workerCount++;
            System.out.println("Nuevo trabajador agregado para " + component);
        } else {
            System.out.println("Límite de trabajadores alcanzado para " + companyName);
        }
    }
    public void selectWorker(String component){
        for (int i = 0; i < workers.length; i++) {
            if(workers[i] != null){
                if(workers[i].getComponent().equals(component)){
                    removeWorker(i);
                }
            }
        }
    }
    public int countWorkers(String component){
        int cont = 0;
        for (int i = 0; i < workers.length; i++) {
            if(workers[i] != null){
                if(workers[i].getComponent().equals(component)){
                    cont++;
                }
            }
        }
        return cont;
    }

    // Eliminar un trabajador
    public void removeWorker(int index) {
        if (index >= 0 && index < workerCount) {
            workers[index].stopWorker();  // Detener el hilo
            System.out.println("Trabajador eliminado.");
            // Mover el último trabajador al lugar del eliminado
            workers[index] = workers[workerCount - 1];
            workers[workerCount - 1] = null;
            workerCount--;
        } else {
            System.out.println("Índice de trabajador no válido.");
        }
    }
    
    

    // Agregar un ensamblador
    public void addAssembler(int assemblyTime) {
        int sum = workerCount + assemblerCount;
        if (sum < maxWorkers) {
            Assembler assembler = new Assembler(this, assemblyTime);
            assemblers[assemblerCount] = assembler;
            assembler.start();
            assemblerCount++;
            System.out.println("Nuevo ensamblador agregado.");
        } else {
            System.out.println("Límite de ensambladores alcanzado para " + companyName);
        }
    }

    // Eliminar un ensamblador
    public void removeAssembler(int index) {
        if (index >= 0 && index < assemblerCount) {
            assemblers[index].stopAssembler();  // Detener el hilo
            System.out.println("Ensamblador eliminado.");
            // Mover el último ensamblador al lugar del eliminado
            assemblers[index] = assemblers[assemblerCount - 1];
            assemblers[assemblerCount - 1] = null;
            assemblerCount--;
        } else {
            System.out.println("Índice de ensamblador no válido.");
        }
    }
    
    public void selectAss(){
        for (int i = 0; i < workers.length; i++) {
            if(workers[i] != null){
                removeAssembler(i);
            }
        }
    }
    public synchronized void resetProduction() {
        // Reiniciar contadores de producción
        computersProduced = 0;
        computersWithGraphics = 0;
        System.out.println("Producción de " + companyName + " ha sido reiniciada.");
    }

    // Métodos de ensamblaje (similar a lo visto anteriormente)
    public synchronized void assembleStandardComputer() throws InterruptedException {
        boolean hasComponents = false;
        System.out.println(companyName + "Esta tratando de crear una PC");
        switch (companyName) {
            case "Apple":
                if (storage.hasComponentsForAppleStandard()) {
                    if(checkForGraphicsComputer(5)){
                    return;   
                    }
                    storage.useComponentsForAppleStandard();
                    grossIncome += 100000;  // Ganancia por computadora estándar de Apple
                    hasComponents = true;
                }
                break;
            case "Dell":
                if (storage.hasComponentsForDellStandard()) {
                    if(checkForGraphicsComputer(3)){
                    return;   
                    }
                    storage.useComponentsForDellStandard();
                    grossIncome += 80000;  // Ganancia por computadora estándar de Dell
                    hasComponents = true;
                }
                break;
            case "HP":
                if (storage.hasComponentsForHPStandard()) {
                    if(checkForGraphicsComputer(2)){
                    return;   
                    }
                    storage.useComponentsForHPStandard();
                    grossIncome += 90000;  // Ganancia por computadora estándar de HP
                    hasComponents = true;
                }
                break;
        }

        if (hasComponents) {
            computersProduced++;
            currentComputersProduced++;
            System.out.println(companyName + " ha ensamblado una computadora estándar. Computadoras producidas: " + computersProduced);
        } else {
            System.out.println(companyName + " no tiene suficientes componentes para ensamblar una computadora estándar.");
        }
    }


    public synchronized void assembleGraphicsComputer() throws InterruptedException {
        boolean hasComponents = false;
        switch (companyName) {
            case "Apple":
                if (storage.hasComponentsForAppleGraphics()) {
                    storage.useComponentsForAppleGraphics();
                    grossIncome += 150000;  // Ganancia por computadora con tarjeta gráfica de Apple
                    hasComponents = true;
                }
                break;
            case "Dell":
                if (storage.hasComponentsForDellGraphics()) {
                    storage.useComponentsForDellGraphics();
                    grossIncome += 120000;  // Ganancia por computadora con tarjeta gráfica de Dell
                    hasComponents = true;
                }
                break;
            case "HP":
                if (storage.hasComponentsForHPGraphics()) {
                    storage.useComponentsForHPGraphics();
                    grossIncome += 140000;  // Ganancia por computadora con tarjeta gráfica de HP
                    hasComponents = true;
                }
                break;
        }

        if (hasComponents) {
            computersWithGraphics++;
            System.out.println(companyName + " ha ensamblado una computadora con tarjeta gráfica. Computadoras con tarjeta gráfica: " + computersWithGraphics);
        } else {
            System.out.println(companyName + " no tiene suficientes componentes para ensamblar una computadora con tarjeta gráfica.");
        }
    }


    public double getGrossIncome() {
        return grossIncome;
    }

    public double getOperationalCosts() {
        return operationalCosts;
    }

    public boolean checkForGraphicsComputer(int interval) throws InterruptedException {
        if (currentComputersProduced >= interval) {
            assembleGraphicsComputer();
            currentComputersProduced = 0;  // Reiniciar el contador después de ensamblar la computadora con tarjeta gráfica
            return true;
        }
        return false;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public int getMaxWorkers() {
        return maxWorkers;
    }

    public void setMaxWorkers(int maxWorkers) {
        this.maxWorkers = maxWorkers;
    }

    public int getComputersProduced() {
        return computersProduced;
    }

    public void setComputersProduced(int computersProduced) {
        this.computersProduced = computersProduced;
    }

    public int getCurrentComputersProduced() {
        return currentComputersProduced;
    }

    public void setCurrentComputersProduced(int currentComputersProduced) {
        this.currentComputersProduced = currentComputersProduced;
    }

    public int getComputersWithGraphics() {
        return computersWithGraphics;
    }

    public void setComputersWithGraphics(int computersWithGraphics) {
        this.computersWithGraphics = computersWithGraphics;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public void setWorkers(Worker[] workers) {
        this.workers = workers;
    }

    public Assembler[] getAssemblers() {
        return assemblers;
    }

    public void setAssemblers(Assembler[] assemblers) {
        this.assemblers = assemblers;
    }

    public int getWorkerCount() {
        return workerCount;
    }

    public void setWorkerCount(int workerCount) {
        this.workerCount = workerCount;
    }

    public int getAssemblerCount() {
        return assemblerCount;
    }

    public void setAssemblerCount(int assemblerCount) {
        this.assemblerCount = assemblerCount;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public void setOperationalCosts(double operationalCosts) {
        this.operationalCosts = operationalCosts;
    }

    public Manager getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(Manager projectManager) {
        this.projectManager = projectManager;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

}
