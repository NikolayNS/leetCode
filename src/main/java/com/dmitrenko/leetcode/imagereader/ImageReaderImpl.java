package com.dmitrenko.leetcode.imagereader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ImageReaderImpl {

    public String readImage(String path) throws IOException {
        var image = ImageIO.read(new File(path));
        var width = image.getWidth() - 270;
        var height = image.getHeight() / 10;
        var cards = image.getSubimage(image.getWidth() / 5 + 10, image.getHeight() / 2, width, height);
        var binary = getBinaryCards(width, height, cards);
        return getClearCards(binary);
    }

    private List<String> getBinaryCards(int width, int height, BufferedImage cards) {
        List<String> result = new LinkedList<>();
        for (int y = 1; y < height; y++) {
            var binary = new StringBuilder();
            for (int x = 1; x < width; x++) {
                Color color = new Color(cards.getRGB(x, y));
                binary.append(color.getRed() > 90 && color.getGreen() > 90 && color.getBlue() > 30 ? "*" : " ");
            }
            result.add(binary.toString());
        }
        return result;
    }

    private String getClearCards(List<String> binaryCards) {
        var result = new StringBuilder();
        var first = new StringBuilder();
        var second = new StringBuilder();
        var third = new StringBuilder();
        var fourth = new StringBuilder();
        var fifth = new StringBuilder();
        binaryCards.stream().filter(o -> !o.isBlank()).forEach(o -> {
            first.append(o, 5, 69);
            second.append(o, 76, 140);
            third.append(o, 148, 211);
            fourth.append(o, 220, 284);
            fifth.append(o, 291, 354);
        });
        result.append(first.toString());
        result.append(second.toString());
        result.append(third.toString());
        result.append(fourth.toString());
        result.append(fifth.toString());
        return result.toString();
    }
}
