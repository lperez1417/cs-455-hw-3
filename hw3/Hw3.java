/*

*/

import renderer.scene.*;
import renderer.models.*;
import renderer.pipeline.*;
import renderer.framebuffer.*;
import renderer.gui.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

/**

*/
public class Hw3
{
   /**
      This constructor instantiates the Scene object
      and initializes it with appropriate geometry.
   */
   public Hw3()
   {
      // Define initial dimensions for a FrameBuffer.
      final int fbWidth  = 1024;
      final int fbHeight = 1024;

      // Create a FrameBufferFrame holding a FrameBufferPanel.
      FrameBufferFrame fbf = new FrameBufferFrame("Renderer 2", fbWidth, fbHeight);
      fbf.setResizable(false);

      // Create the Scene object that we shall render
      Scene scene = new Scene();

      // Create several Model objects.
      scene.addModel(new Square(1));
      scene.addModel(new Square(2));
      scene.addModel(new Square(3));
      scene.addModel(new Circle(3, 4));
      scene.addModel(new Circle(3, 64));

      // Give each model a useful name.
      scene.modelList.get(0).name = "Square_1";
      scene.modelList.get(1).name = "Square_2";
      scene.modelList.get(2).name = "Square_3";
      scene.modelList.get(3).name = "Diamond";
      scene.modelList.get(4).name = "Circle";

      // Push the models away from where the camera is.
      for (Model m : scene.modelList)
      {
         for (Vertex v : m.vertexList)
         {
            v.z -= 10;
         }
      }

      // Give each model an initial position in the scene.
      for (Vertex v : scene.modelList.get(0).vertexList)
      {
         v.x += 0;
         v.y += 0;
      }
      for (Vertex v : scene.modelList.get(1).vertexList)
      {
         v.x -= 5;
         v.y -= 5;
      }
      for (Vertex v : scene.modelList.get(2).vertexList)
      {
         v.x += 5;
         v.y += 5;
      }
      for (Vertex v : scene.modelList.get(3).vertexList)
      {
         v.x += 5;
         v.y -= 5;
      }
      for (Vertex v : scene.modelList.get(4).vertexList)
      {
         v.x -= 5;
         v.y += 5;
      }

      // Render.
      FrameBuffer fb = fbf.fbp.getFrameBuffer();
      fb.clearFB(Color.black);
      Pipeline.render(scene, fb.vp);
      fbf.fbp.update();
      fbf.repaint();
   }


   /**
      Create an instance of this class which has
      the affect of creating the GUI application.
   */
   public static void main(String[] args)
   {
      print_help_message();

      // We need to call the program's constructor in the
      // Java GUI Event Dispatch Thread, otherwise we get a
      // race condition between the constructor (running in
      // the main() thread) and the very first ComponentEvent
      // (running in the EDT).
      javax.swing.SwingUtilities.invokeLater(
         new Runnable() { // an anonymous inner class constructor
            public void run() { // implement the Runnable interface
               new Hw3(); // call the constructor that builds the gui
      }});
   }


   private static void print_help_message()
   {
      System.out.println("Use the 'd' key to toggle debugging information on and off.");
      System.out.println("Use the 'c' key to toggle line clipping on and off.");
      System.out.println("Use the 'h' key to redisplay this help message.");
   }
}
