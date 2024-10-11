/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;

/**
 *
 * @author santi
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class UtilityGraph extends JFrame {
    private DefaultCategoryDataset dataset;
    private JFreeChart lineChart;
    private ChartPanel chartPanel;

    // Constructor
    public UtilityGraph(String title) {
        super(title);

        // Crear un dataset vacío
        dataset = new DefaultCategoryDataset();

        // Crear el gráfico
        lineChart = ChartFactory.createLineChart(
                "Comparación de Ganancias",
                "Días",
                "Ganancias ($)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Crear el panel del gráfico y añadirlo a la ventana
        chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
    }

    // Método para actualizar el gráfico con las ganancias de HP, Apple y Dell
    public void updateGraph(int day, double hpProfit, double appleProfit, double dellProfit) {
        dataset.addValue(hpProfit, "HP", "" + day);
        dataset.addValue(appleProfit, "Apple", "" + day);
        dataset.addValue(dellProfit, "Dell", "" + day);
        lineChart.fireChartChanged();  // Asegurarse de que el gráfico se actualice
    }

    // Mostrar la ventana con la gráfica
    public void display() {
        this.pack();  // Ajustar el tamaño
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Asegurarse de que la ventana se cierre correctamente
        this.setVisible(true);  // Hacer visible la ventana del gráfico
    }
}
