/**
 * 
 * @author jaide_8zxth01
 *
 *This class represents a 2D grid(matrix)
 *
 * @param <T>
 */

public class LinkedGrid<T> { 
	
	
int width,height;


	/**
	 * Assigning values for width and height
	 */
private LinearNode<T>[][] grid;
    public LinkedGrid(int width, int height)
    {
    	this.width = width;
        this.height = height;
       
        grid = new LinearNode[width][height]; 
        for(int r = 0; r < height ; r++ )   
        {
        for(int c = 0; c  < width; c++ )   
        {  
        grid[c][r] = new LinearNode<T>();
        }
        
        }   
    }
    
    /**
     * Sets the element 
     * 
     * @param col
     * @param row
     * @param data
     */
    public void setElement(int col,int row, T data)
    {
     if (col >= width || col < 0 || row >= height || row < 0)
     {
     throw new LinkedListException("Out of bounds");
     }
     else
     {
         grid[col][row].setElement(data);
     }
    }
    
    /**
     * Getting the element
     * 
     * @param col
     * @param row
     * @return
     */
    public T getElement (int col, int row) {
     if (col >= width || col < 0 || row >= height || row < 0)
     {
     throw new LinkedListException("Out of bounds");
     }
     else
     {
      return grid[col][row].getElement();
     }
    }
    
    /**
     * Get height
     * 
     * @return
     */
    public int getHeight()
    {
    return height;
    }
    
    /**
     * Get width
     * 
     * @return
     */
    public int getWidth()
    {
    return width;
    }

    /**
     * Builds a string that represents the grid if elements
     * 
     */
    public String toString()
    {
     String X = "";
     
     for(int r = 0; r < height ; r++ )   
        {
        for(int c = 0; c  < width; c++ )   
        { X += grid[c][r].getElement()+"  ";}
        X += "\n";
        }
    return X;
    }
}
    

