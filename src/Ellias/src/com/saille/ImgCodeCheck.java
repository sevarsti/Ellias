package com.saille;

import com.GlobalConstant;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImgCodeCheck {
    BufferedImage image;
    private int iw;
    private int ih;
    private int[] pixels;

    public ImgCodeCheck(BufferedImage image) {
        this.image = image;
        this.iw = image.getWidth();
        this.ih = image.getHeight();
        this.pixels = new int[this.iw * this.ih];
    }

    public BufferedImage changeGrey() {
        PixelGrabber pg = new PixelGrabber(this.image.getSource(), 0, 0, this.iw, this.ih, this.pixels, 0, this.iw);
        try {
            pg.grabPixels();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        int grey = 100;

        ColorModel cm = ColorModel.getRGBdefault();
        for(int i = 0; i < this.iw * this.ih; i++) {
            int alpha = cm.getAlpha(this.pixels[i]);
            int red;
            if(cm.getRed(this.pixels[i]) > grey) {
                red = 255;
            } else {
                red = 0;
            }
            int green;
            if(cm.getGreen(this.pixels[i]) > grey) {
                green = 255;
            } else {
                green = 0;
            }
            int blue;
            if(cm.getBlue(this.pixels[i]) > grey) {
                blue = 255;
            } else {
                blue = 0;
            }
            this.pixels[i] = (alpha << 24 | red << 16 | green << 8 | blue);
        }

        Image tempImg = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(this.iw, this.ih, this.pixels, 0, this.iw));
        this.image = new BufferedImage(tempImg.getWidth(null), tempImg.getHeight(null), 4);
        this.image.createGraphics().drawImage(tempImg, 0, 0, null);
        return this.image;
    }

    public BufferedImage getMedian() {
        PixelGrabber pg = new PixelGrabber(this.image.getSource(), 0, 0, this.iw, this.ih, this.pixels, 0, this.iw);
        try {
            pg.grabPixels();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        ColorModel cm = ColorModel.getRGBdefault();
        for(int i = 1; i < this.ih - 1; i++) {
            for(int j = 1; j < this.iw - 1; j++) {
                int alpha = cm.getAlpha(this.pixels[(i * this.iw + j)]);

                int red4 = cm.getRed(this.pixels[(i * this.iw + j - 1)]);
                int red5 = cm.getRed(this.pixels[(i * this.iw + j)]);
                int red6 = cm.getRed(this.pixels[(i * this.iw + j + 1)]);
                int red;
                if(red4 >= red5) {
                    if(red5 >= red6) {
                        red = red5;
                    } else {
                        if(red4 >= red6) {
                            red = red6;
                        } else {
                            red = red4;
                        }
                    }
                } else {
                    if(red4 > red6) {
                        red = red4;
                    } else {
                        if(red5 > red6) {
                            red = red6;
                        } else {
                            red = red5;
                        }
                    }
                }

                int green4 = cm.getGreen(this.pixels[(i * this.iw + j - 1)]);
                int green5 = cm.getGreen(this.pixels[(i * this.iw + j)]);
                int green6 = cm.getGreen(this.pixels[(i * this.iw + j + 1)]);
                int green;
                if(green4 >= green5) {
                    if(green5 >= green6) {
                        green = green5;
                    } else {
                        if(green4 >= green6) {
                            green = green6;
                        } else {
                            green = green4;
                        }
                    }
                } else {
                    if(green4 > green6) {
                        green = green4;
                    } else {
                        if(green5 > green6) {
                            green = green6;
                        } else {
                            green = green5;
                        }
                    }

                }

                int blue4 = cm.getBlue(this.pixels[(i * this.iw + j - 1)]);
                int blue5 = cm.getBlue(this.pixels[(i * this.iw + j)]);
                int blue6 = cm.getBlue(this.pixels[(i * this.iw + j + 1)]);
                int blue;
                if(blue4 >= blue5) {
                    if(blue5 >= blue6) {
                        blue = blue5;
                    } else {
                        if(blue4 >= blue6) {
                            blue = blue6;
                        } else {
                            blue = blue4;
                        }
                    }
                } else {
                    if(blue4 > blue6) {
                        blue = blue4;
                    } else {
                        if(blue5 > blue6) {
                            blue = blue6;
                        } else {
                            blue = blue5;
                        }
                    }
                }
                this.pixels[(i * this.iw + j)] = (alpha << 24 | red << 16 | green << 8 | blue);
            }

        }

        Image tempImg = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(this.iw, this.ih, this.pixels, 0, this.iw));
        this.image = new BufferedImage(tempImg.getWidth(null), tempImg.getHeight(null), 4);
        this.image.createGraphics().drawImage(tempImg, 0, 0, null);
        return this.image;
    }

    public BufferedImage getGrey() {
        ColorConvertOp ccp = new ColorConvertOp(ColorSpace.getInstance(1003), null);
        return this.image = ccp.filter(this.image, null);
    }

    public BufferedImage getBrighten() {
        RescaleOp rop = new RescaleOp(1.25F, 0.0F, null);
        return this.image = rop.filter(this.image, null);
    }

    public BufferedImage getBlur() {
        float[] data = {0.1111F, 0.1111F, 0.1111F, 0.1111F, 0.1111F, 0.1111F, 0.1111F, 0.1111F, 0.1111F};

        ConvolveOp cop = new ConvolveOp(new Kernel(3, 3, data));
        return this.image = cop.filter(this.image, null);
    }

    public BufferedImage getSharpen() {
        float[] data = {0.0F, -0.75F, 0.0F, -0.75F, 4.0F, -0.75F, 0.0F, -0.75F, 0.0F};

        ConvolveOp cop = new ConvolveOp(new Kernel(3, 3, data));
        return this.image = cop.filter(this.image, null);
    }

    public BufferedImage getRotate() {
        AffineTransformOp atop = new AffineTransformOp(AffineTransform.getRotateInstance(3.141592653589793D, this.image.getWidth() / 2, this.image.getHeight() / 2), 1);

        return this.image = atop.filter(this.image, null);
    }

    public BufferedImage getProcessedImg() {
        return this.image;
    }

    public static void main(String[] args) throws IOException {
        try {
            File f = new File(GlobalConstant.DISKPATH + "GetPicCode.png");
            FileInputStream fin = new FileInputStream(f);
            BufferedImage bi = ImageIO.read(fin);
            ImgCodeCheck flt = new ImgCodeCheck(bi);
            flt.changeGrey();
            flt.getGrey();
            flt.getBrighten();
            bi = flt.getProcessedImg();

            String pname = GlobalConstant.DISKPATH + "GetPicCode2.png";
            File file = new File(pname + ".jpg");
            ImageIO.write(bi, "jpg", file);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}