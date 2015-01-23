import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author bnmathews
 * @date January 22 2015
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D(); //gets an array of all the pixels
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to keep only the blue values in a picture */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
        pixelObj.setRed(0);
      }
    }
  }
  
  /** Method to negate all pixels in a picture */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(255 - pixelObj.getGreen());
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setBlue(255 - pixelObj.getBlue());
      }
    } 
  }
  
  /** Method that stretches out the picture's pixels to the point of appearing like straight lines*/
  public void bars()
  {
    Pixel[][] pixels = this.getPixels2D();
    int height = pixels.length - 1;
    int width = pixels[0].length - 1;
    for(int row = 0; row < pixels.length; row++)
    {
        for (int col = 0; col < pixels[row].length; col++)
        {
            for (int col2 = pixels[row].length - 1 - col; col2 > col; col2--)
            {
                pixels[row][col2].setColor( pixels[row][col].getColor() );
            }
        }
    }
  }
  
  /** Method to distort the image, but in an unusual way (because I'm too lazy to code a uniform distortion method) */
  public void oddDistort(int rowDist, int colDist)
  {
    Pixel[][] pixels = this.getPixels2D();
    Random randRow = new Random();
    Random randCol = new Random();
    int getRow = 0;
    int getCol = 0;
    for(int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < pixels[row].length; col++)
      {
        getRow = randRow.nextInt(rowDist);
        getCol = randCol.nextInt(colDist);
        if (row + getRow > pixels.length - 1)
        {
            getRow = 0;
        }
        if (col + getCol > pixels[row].length - 1)
        {
            getCol = 0;
        }
        pixels[row][col].setColor( pixels[row + getRow][col + getCol].getColor() );
      }
    }
  }
  
  /** Method to add noise */
  public void noise(int noisemult)
  {
    Pixel[][] pixels = this.getPixels2D();
    Random r = new Random();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(pixelObj.getGreen() - (r.nextInt() / (1999999999 / noisemult))); // this has to be a really small number
        pixelObj.setRed(pixelObj.getRed() - (r.nextInt() / (1999999999 / noisemult)));
        pixelObj.setBlue(pixelObj.getBlue() - (r.nextInt() / (1999999999 / noisemult)));
      }
    } 
  }
  
  /** Method to set the picture to greyscale */
  public void greyscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int avg = (pixelObj.getGreen() + pixelObj.getRed() + pixelObj.getBlue()) / 3;
        pixelObj.setGreen(avg);
        pixelObj.setRed(avg);
        pixelObj.setBlue(avg);
      }
    } 
  }
  
  /** Method to posterize the image */
  public void posterize()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
          if ( pixelObj.getRed() > 192)
          {
              pixelObj.setRed(223);
          }
          else if ( pixelObj.getRed() > 128)
          {
              pixelObj.setRed(159);
          }
          else if ( pixelObj.getRed() > 64)
          {
              pixelObj.setRed(95);
          }
          else if ( pixelObj.getRed() > 0)
          {
              pixelObj.setRed(31);
          }
          
          if ( pixelObj.getGreen() > 192)
          {
              pixelObj.setGreen(223);
          }
          else if ( pixelObj.getGreen() > 128)
          {
              pixelObj.setGreen(159);
          }
          else if ( pixelObj.getGreen() > 64)
          {
              pixelObj.setGreen(95);
          }
          else if ( pixelObj.getGreen() > 0)
          {
              pixelObj.setGreen(31);
          }
          
          if ( pixelObj.getBlue() > 192)
          {
              pixelObj.setBlue(223);
          }
          else if ( pixelObj.getBlue() > 128)
          {
              pixelObj.setBlue(159);
          }
          else if ( pixelObj.getBlue() > 64)
          {
              pixelObj.setBlue(95);
          }
          else if ( pixelObj.getBlue() > 0)
          {
              pixelObj.setBlue(31);
          }
      }
    } 
  }
  
  /** Method that mirrors the picture vertically */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    int width = pixels[0].length - 1;
    for(int row = 0; row < pixels.length; row++)
    {
        for (int col = 0; col < pixels[row].length; col++)
        {
            pixels[row][width - col].setColor( pixels[row][col].getColor() );
        }
    }
  }
  
  /** Method that mirrors the picture horizontally */
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    int height = pixels.length - 1;
    for(int row = 0; row < pixels.length; row++)
    {
        for (int col = 0; col < pixels[row].length; col++)
        {
            pixels[height - row][col].setColor( pixels[row][col].getColor() );
        }
    }
  }
  
  /** Method that mirrors a specific part of the picture */
  public void mirrorCustom(int rowStart, int rowStop, int colStart, int colStop, int mirrorPoint, int mirrorType)
  {
    Pixel[][] pixels = this.getPixels2D();
    int height = pixels.length - 1;
    int width = pixels[0].length - 1;
    if (mirrorType == 1) // this mirrors a specific part vertically
    {
        for(int row = rowStart; row < rowStop; row++)
        {
            for (int col = colStart; col < mirrorPoint; col++)
            {
                pixels[row][mirrorPoint - col + mirrorPoint].setColor( pixels[row][col].getColor() );
            }
        }
    }
    else
    {
        for(int row = rowStart; row < mirrorPoint; row++)
        {
            for (int col = colStart; col < colStop; col++)
            {
                pixels[mirrorPoint - row + mirrorPoint][col].setColor( pixels[row][col].getColor() );
            }
        }
    }
  }
 
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }
  
  public void cropAndCopy(Picture sourcePicture, int startSourceRow, int endSourceRow, int startSourceCol, int endSourceCol,
         int startDestRow, int startDestCol)
  {
    Picture newPic = new Picture(sourcePicture);
    Pixel[][] pixels = newPic.getPixels2D();
    Pixel[][] ourpixels = this.getPixels2D();
    int placeRow = startDestRow;
    int placeCol = startDestCol;
    for(int row = startSourceRow; row < endSourceRow; row++)
    {
        for (int col = startSourceCol; col < endSourceCol; col++)
        {
            ourpixels[placeRow][placeCol].setColor( pixels[row][col].getColor() );
            placeCol++;
        }
        placeRow++;
        placeCol = startDestCol;
    }
  }
  
  public void deSize(Picture sourcePicture, int sizeDivider, int destRow, int destCol)
  {
    Picture newPic = new Picture(sourcePicture);
    Pixel[][] pixels = newPic.getPixels2D();
    Pixel[][] ourpixels = this.getPixels2D();
    int placeRow = destRow;
    int placeCol = destCol;
    for(int row = 0; row < pixels.length; row++)
    {
        for (int col = 0; col < pixels[row].length; col++)
        {
            if (row % sizeDivider == 0 && col % sizeDivider == 0)
            {
                ourpixels[placeRow][placeCol].setColor( pixels[row][col].getColor() );
                placeCol++;
            }
        }
        if (row % sizeDivider == 0)
        {
            placeRow++;
        }
        placeCol = destCol;
    }
  }
  
  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture naut = new Picture("naut.png");
    Picture naut2 = new Picture("naut.png");
    Picture naut3 = new Picture("naut.png");
    Picture naut4 = new Picture("naut.png");
    Picture naut5 = new Picture("naut.png");
    Picture naut6 = new Picture("naut.png");
    this.deSize(naut,3,0,0);
    naut2.negate();
    this.deSize(naut2,3,0,223);
    naut3.mirrorVertical();
    naut3.mirrorHorizontal();
    this.deSize(naut3,3,0,446);
    naut4.noise(200);
    this.deSize(naut4,3,210,0);
    naut5.posterize();
    this.deSize(naut5,3,210,223);
    naut6.oddDistort(1,140);
    this.deSize(naut6,3,210,446);
    this.write("collage.jpg");
  }
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
