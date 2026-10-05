package com.makerspace.sensorlab.analysis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Draws a line chart of values, with two dashed limit lines, using Java2D, and
 * saves it as a PNG. A working model for your other charts.
 */
public class LineChart {

    public static void save(String title, String yLabel, List<Double> values, double low, double high, Path png)
            throws IOException {
        int width = 900, height = 450, left = 80, right = 30, top = 60, bottom = 60;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D pen = image.createGraphics();
        pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, width, height);
        pen.setColor(Color.BLACK);
        pen.setFont(new Font("SansSerif", Font.BOLD, 20));
        pen.drawString(title, left, 35);

        double min = Math.min(low, values.stream().mapToDouble(Double::doubleValue).min().orElse(low));
        double max = Math.max(high, values.stream().mapToDouble(Double::doubleValue).max().orElse(high));
        double pad = (max - min) * 0.1;
        min -= pad;
        max += pad;
        int plotW = width - left - right, plotH = height - top - bottom;

        pen.setFont(new Font("SansSerif", Font.PLAIN, 12));
        for (int i = 0; i <= 5; i++) {
            double v = min + (max - min) * i / 5;
            int y = top + plotH - (int) ((v - min) / (max - min) * plotH);
            pen.setColor(new Color(230, 230, 230));
            pen.drawLine(left, y, left + plotW, y);
            pen.setColor(Color.DARK_GRAY);
            pen.drawString(String.format("%.2f", v), 15, y + 4);
        }
        pen.setColor(new Color(210, 40, 40));
        pen.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[]{8, 6}, 0));
        for (double limit : new double[]{low, high}) {
            int y = top + plotH - (int) ((limit - min) / (max - min) * plotH);
            pen.drawLine(left, y, left + plotW, y);
        }
        pen.setColor(new Color(48, 105, 152));
        pen.setStroke(new BasicStroke(2));
        for (int i = 1; i < values.size(); i++) {
            int x1 = left + (i - 1) * plotW / Math.max(1, values.size() - 1);
            int x2 = left + i * plotW / Math.max(1, values.size() - 1);
            int y1 = top + plotH - (int) ((values.get(i - 1) - min) / (max - min) * plotH);
            int y2 = top + plotH - (int) ((values.get(i) - min) / (max - min) * plotH);
            pen.drawLine(x1, y1, x2, y2);
        }
        pen.setColor(Color.BLACK);
        pen.setStroke(new BasicStroke(2));
        pen.drawLine(left, top + plotH, left + plotW, top + plotH);
        pen.drawLine(left, top, left, top + plotH);
        pen.drawString("reading number", left + plotW / 2 - 40, height - 20);
        pen.rotate(-Math.PI / 2);
        pen.drawString(yLabel, -(top + plotH / 2 + 30), 12);
        pen.dispose();
        Files.createDirectories(png.toAbsolutePath().getParent());
        ImageIO.write(image, "png", png.toFile());
    }
}
