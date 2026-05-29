/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package informes;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
/**
 *
 * @author usuario
 */
public class AjusteDeGrafico implements JRChartCustomizer{
    
    @Override
    public void customize(JFreeChart chart, JRChart jasperChart) {
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        
        // Ancho máximo de cada barra (0.1 = 10% del ancho total del gráfico)
        renderer.setMaximumBarWidth(0.10);
        
        // Espacio entre barras del mismo grupo
        renderer.setItemMargin(0.02);
    }
}
