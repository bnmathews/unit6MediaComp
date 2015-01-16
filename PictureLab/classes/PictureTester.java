/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test deSize */
  public static void testDeSize()
  {
    Picture isle = new Picture("CumberlandIsland.jpg");
    Picture moto = new Picture("blueMotorcycle.jpg");
    isle.deSize(moto,6,200,70);
    isle.explore();
  }
  
  /** Method to test reSize */
  public static void testReSize()
  {
    Picture beach = new Picture("beach.jpg");
    Picture robo = new Picture("robot.jpg");
    beach.reSize(robo,2,0,0);
    beach.explore();
  }
    
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test a custom horizontal mirror */
  public static void testMirrorCustom()
  {
    Picture snow = new Picture("snowman.jpg");
    snow.mirrorCustom(161,200,107,290,204,2);
    snow.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Method to test keepOnlyBlue */
  public static void testKeepOnlyBlue()
  {
    Picture top = new Picture("topgun.jpg");
    top.keepOnlyBlue();
    top.explore();
  }
  
  /** Method to test negate */
  public static void testNegate()
  {
    Picture gorge = new Picture("gorge.jpg");
    gorge.negate();
    gorge.explore();
  }
  
  /** Method to test greyscale */
  public static void testGreyscale()
  {
    Picture door = new Picture("thrudoor.jpg");
    door.greyscale();
    door.explore();
  }
  
  /** Method to test noise */
  public static void testNoise()
  {
    Picture swan = new Picture("swan.jpg");
    swan.noise(200);
    swan.explore();
  }
  
  /** Method to test bars*/
  public static void testBars()
  {
    Picture flower = new Picture("whiteFlower.jpg");
    flower.bars();
    flower.explore();
  }
  
  public static void testCropAndCopy()
  {
    Picture mark = new Picture("blue-mark.jpg");
    Picture moon = new Picture("moon-surface.jpg");
    moon.cropAndCopy( mark, 160, 290, 260, 400, 60, 140 );
    moon.cropAndCopy( mark, 160, 290, 260, 400, 200, 360 );
    moon.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}