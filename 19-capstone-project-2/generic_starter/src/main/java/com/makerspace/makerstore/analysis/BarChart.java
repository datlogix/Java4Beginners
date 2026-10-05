package com.makerspace.makerstore.analysis;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * Draws a bar chart with Java2D (Module 1's drawing tools, on an image instead
 * of a window) and saves it as a PNG. A working model for your other charts.
 */
public class BarChart {

    public static void save(String title, String yLabel, Map<String, Double> data, Path png) throws IOException {
        int width = 800, height = 500, left = 90, right = 30, top = 60, bottom = 90;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D pen = image.createGraphics();
        pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        pen.setColor(Color.WHITE);
        pen.fillRect(0, 0, width, height);

        pen.setColor(Color.BLACK);
        pen.setFont(new Font("SansSerif", Font.BOLD, 20));
        pen.drawString(title, left, 35);

        double max = data.values().stream().mapToDouble(Double::doubleValue).max().orElse(1);
        int plotW = width - left - right, plotH = height - top - bottom;
        int n = Math.max(1, data.size());
        int slot = plotW / n, barW = (int) (slot * 0.7);

        pen.setFont(new Font("SansSerif", Font.PLAIN, 12));
        for (int i = 0; i <= 4; i++) {                      // y axis grid lines and labels
            int y = top + plotH - plotH * i / 4;
            pen.setColor(new Color(225, 225, 225));
            pen.drawLine(left, y, left + plotW, y);
            pen.setColor(Color.DARK_GRAY);
            pen.drawString(String.format("%.0f", max * i / 4), 10, y + 4);
        }
        int i = 0;
        for (Map.Entry<String, Double> e : data.entrySet()) {
            int barH = (int) (e.getValue() / max * plotH);
            int x = left + i * slot + (slot - barW) / 2;
            pen.setColor(new Color(48, 105, 152));
            pen.fillRect(x, top + plotH - barH, barW, barH);
            pen.setColor(Color.BLACK);
            pen.drawString(e.getKey(), x, top + plotH + 18);
            i++;
        }
        pen.setStroke(new BasicStroke(2));
        pen.drawLine(left, top + plotH, left + plotW, top + plotH);
        pen.drawLine(left, top, left, top + plotH);
        pen.rotate(-Math.PI / 2);
        pen.drawString(yLabel, -(top + plotH / 2 + 40), 25);
        pen.dispose();

        Files.createDirectories(png.toAbsolutePath().getParent());
        ImageIO.write(image, "png", png.toFile());
    }
}
