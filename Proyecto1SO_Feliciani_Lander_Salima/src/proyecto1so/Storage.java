/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;

/**
 *
 * @author santi
 */
import java.util.concurrent.Semaphore;

public class Storage {
    // Cantidad de cada componente en el almacén
    private int motherboards;
    private int CPUs;
    private int RAMs;
    private int powerSupplies;
    private int graphicCards;

    // Limites máximos para cada componente
    private int MAX_MOTHERBOARDS = 25;  // Límite de placas base
    private int MAX_CPUS = 20;  // Límite de CPUs
    private int MAX_RAMS = 55;  // Límite de memorias RAM
    private int MAX_POWER_SUPPLIES = 35;  // Límite de fuentes de alimentación
    private int MAX_GRAPHIC_CARDS = 10;  // Límite de tarjetas gráficas

    // Semáforos para controlar el acceso a cada tipo de componente
    private final Semaphore motherboardSemaphore = new Semaphore(1);
    private final Semaphore cpuSemaphore = new Semaphore(1);
    private final Semaphore ramSemaphore = new Semaphore(1);
    private final Semaphore powerSupplySemaphore = new Semaphore(1);
    private final Semaphore graphicCardSemaphore = new Semaphore(1);

    // Constructor
    public Storage() {
        this.motherboards = 0;
        this.CPUs = 0;
        this.RAMs = 0;
        this.powerSupplies = 0;
        this.graphicCards = 0;
    }

    // Métodos para agregar componentes al almacén, respetando el límite máximo
    public void addMotherboard() throws InterruptedException {
        motherboardSemaphore.acquire();  // Adquirir el semáforo para acceso exclusivo
        try {
            if (motherboards < MAX_MOTHERBOARDS) {  // Verificar si se ha alcanzado el límite
                motherboards++;
                System.out.println("Placa base agregada. Total: " + motherboards);
            } else {
                System.out.println("Límite de placas base alcanzado. No se pueden agregar más.");
            }
        } finally {
            motherboardSemaphore.release();  // Liberar el semáforo
        }
    }

    public void addCPU() throws InterruptedException {
        cpuSemaphore.acquire();  // Adquirir el semáforo para acceso exclusivo
        try {
            if (CPUs < MAX_CPUS) {  // Verificar si se ha alcanzado el límite
                CPUs++;
                System.out.println("CPU agregada. Total: " + CPUs);
            } else {
                System.out.println("Límite de CPUs alcanzado. No se pueden agregar más.");
            }
        } finally {
            cpuSemaphore.release();  // Liberar el semáforo
        }
    }

    public void addRAM() throws InterruptedException {
        ramSemaphore.acquire();  // Adquirir el semáforo para acceso exclusivo
        try {
            if (RAMs < MAX_RAMS) {  // Verificar si se ha alcanzado el límite
                RAMs++;
                System.out.println("RAM agregada. Total: " + RAMs);
            } else {
                System.out.println("Límite de RAMs alcanzado. No se pueden agregar más.");
            }
        } finally {
            ramSemaphore.release();  // Liberar el semáforo
        }
    }

    public void addPowerSupply() throws InterruptedException {
        powerSupplySemaphore.acquire();  // Adquirir el semáforo para acceso exclusivo
        try {
            if (powerSupplies < MAX_POWER_SUPPLIES) {  // Verificar si se ha alcanzado el límite
                powerSupplies++;
                System.out.println("Fuente de alimentación agregada. Total: " + powerSupplies);
            } else {
                System.out.println("Límite de fuentes de alimentación alcanzado. No se pueden agregar más.");
            }
        } finally {
            powerSupplySemaphore.release();  // Liberar el semáforo
        }
    }

    public void addGraphicCard() throws InterruptedException {
        graphicCardSemaphore.acquire();  // Adquirir el semáforo para acceso exclusivo
        try {
            if (graphicCards < MAX_GRAPHIC_CARDS) {  // Verificar si se ha alcanzado el límite
                graphicCards++;
                System.out.println("Tarjeta gráfica agregada. Total: " + graphicCards);
            } else {
                System.out.println("Límite de tarjetas gráficas alcanzado. No se pueden agregar más.");
            }
        } finally {
            graphicCardSemaphore.release();  // Liberar el semáforo
        }
    }

    // Métodos para verificar si hay suficientes componentes para ensamblar computadoras estándar
    public synchronized boolean hasComponentsForAppleStandard() {
        return motherboards >= 2 && CPUs >= 1 && RAMs >= 4 && powerSupplies >= 4;
    }

    public synchronized boolean hasComponentsForDellStandard() {
        return motherboards >= 1 && CPUs >= 5 && RAMs >= 6 && powerSupplies >= 5;
    }

    public synchronized boolean hasComponentsForHPStandard() {
        return motherboards >= 1 && CPUs >= 1 && RAMs >= 2 && powerSupplies >= 4;
    }

    // Métodos para verificar si hay suficientes componentes para ensamblar computadoras con tarjeta gráfica
    public synchronized boolean hasComponentsForAppleGraphics() {
        return hasComponentsForAppleStandard() && graphicCards >= 2;
    }

    public synchronized boolean hasComponentsForDellGraphics() {
        return hasComponentsForDellStandard() && graphicCards >= 1;
    }

    public synchronized boolean hasComponentsForHPGraphics() {
        return hasComponentsForHPStandard() && graphicCards >= 3;
    }

    // Métodos para usar componentes al ensamblar computadoras estándar
    public synchronized void useComponentsForAppleStandard() throws InterruptedException {
        motherboardSemaphore.acquire();
        cpuSemaphore.acquire();
        ramSemaphore.acquire();
        powerSupplySemaphore.acquire();
        try {
            motherboards -= 2;
            CPUs--;
            RAMs -= 4;
            powerSupplies -= 4;
            System.out.println("Componentes utilizados para computadora Apple estándar.");
        } finally {
            motherboardSemaphore.release();
            cpuSemaphore.release();
            ramSemaphore.release();
            powerSupplySemaphore.release();
        }
    }

    public synchronized void useComponentsForDellStandard() throws InterruptedException {
        motherboardSemaphore.acquire();
        cpuSemaphore.acquire();
        ramSemaphore.acquire();
        powerSupplySemaphore.acquire();
        try {
            motherboards--;
            CPUs -= 5;
            RAMs -= 6;
            powerSupplies -= 5;
            System.out.println("Componentes utilizados para computadora Dell estándar.");
        } finally {
            motherboardSemaphore.release();
            cpuSemaphore.release();
            ramSemaphore.release();
            powerSupplySemaphore.release();
        }
    }

    public synchronized void useComponentsForHPStandard() throws InterruptedException {
        motherboardSemaphore.acquire();
        cpuSemaphore.acquire();
        ramSemaphore.acquire();
        powerSupplySemaphore.acquire();
        try {
            motherboards--;
            CPUs--;
            RAMs -= 2;
            powerSupplies -= 4;
            System.out.println("Componentes utilizados para computadora HP estándar.");
        } finally {
            motherboardSemaphore.release();
            cpuSemaphore.release();
            ramSemaphore.release();
            powerSupplySemaphore.release();
        }
    }

    // Métodos para usar componentes al ensamblar computadoras con tarjeta gráfica
    public synchronized void useComponentsForAppleGraphics() throws InterruptedException {
        useComponentsForAppleStandard();
        graphicCardSemaphore.acquire();
        try {
            graphicCards -= 2;
            System.out.println("Componentes utilizados para computadora Apple con tarjeta gráfica.");
        } finally {
            graphicCardSemaphore.release();
        }
    }

    public synchronized void useComponentsForDellGraphics() throws InterruptedException {
        useComponentsForDellStandard();
        graphicCardSemaphore.acquire();
        try {
            graphicCards--;
            System.out.println("Componentes utilizados para computadora Dell con tarjeta gráfica.");
        } finally {
            graphicCardSemaphore.release();
        }
    }

    public synchronized void useComponentsForHPGraphics() throws InterruptedException {
        useComponentsForHPStandard();
        graphicCardSemaphore.acquire();
        try {
            graphicCards -= 3;
            System.out.println("Componentes utilizados para computadora HP con tarjeta gráfica.");
        } finally {
            graphicCardSemaphore.release();
        }
    }

    public int getMAX_MOTHERBOARDS() {
        return MAX_MOTHERBOARDS;
    }

    public void setMAX_MOTHERBOARDS(int MAX_MOTHERBOARDS) {
        this.MAX_MOTHERBOARDS = MAX_MOTHERBOARDS;
    }

    public int getMAX_CPUS() {
        return MAX_CPUS;
    }

    public void setMAX_CPUS(int MAX_CPUS) {
        this.MAX_CPUS = MAX_CPUS;
    }

    public int getMAX_RAMS() {
        return MAX_RAMS;
    }

    public void setMAX_RAMS(int MAX_RAMS) {
        this.MAX_RAMS = MAX_RAMS;
    }

    public int getMAX_POWER_SUPPLIES() {
        return MAX_POWER_SUPPLIES;
    }

    public void setMAX_POWER_SUPPLIES(int MAX_POWER_SUPPLIES) {
        this.MAX_POWER_SUPPLIES = MAX_POWER_SUPPLIES;
    }

    public int getMAX_GRAPHIC_CARDS() {
        return MAX_GRAPHIC_CARDS;
    }

    public void setMAX_GRAPHIC_CARDS(int MAX_GRAPHIC_CARDS) {
        this.MAX_GRAPHIC_CARDS = MAX_GRAPHIC_CARDS;
    }
    
}

