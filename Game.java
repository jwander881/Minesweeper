import java.awt.Color;


/**
 * Main class for Minesweeper
 * 
 * @author jaide_8zxth01
 *
 */
public class Game {

	/**
	 * variables
	 */
    public static int height;
    public static int width;
    private LinkedGrid<Character> board;
    private LinkedGrid<GUICell> cells;
    private GUI gui;
    
    /**
     * Main constructor #1- helps in placing bombs along with other things
     * 
     * @param height
     * @param width
     * @param fixedRandom
     * @param showGUI
     */
    public Game(int height, int width, boolean fixedRandom, boolean showGUI) {
       
    	Game.height = height;
        Game.width = width;
        board = new LinkedGrid(width,height);
        for(int r = 0; r < height ; r++ )   
        {
        	
        for(int c = 0; c  < width; c++ )   
        {
        	
         board.setElement(c, r, '_');
        }
        
        }
        
        BombRandomizer.placeBombs(board, fixedRandom);
        cells = new LinkedGrid<GUICell>(width,height);
        
        for(int r = 0; r < height ; r++ )   
        {
        for(int c = 0; c  < width; c++ )   
        {
            GUICell cell = new GUICell('c', r, c);
            cells.setElement(c, r, cell);
        }
        }
       
        determineNumbers();
       
        
        if(showGUI)
        {
        gui = new GUI(this, cells);
        }
    }
    
    /**
     * Main constructor #2
     * 
     * @param board
     * @param showGUI
     */
    public Game(LinkedGrid<Character> board, boolean showGUI)
    {
        this.board = board;
         this.height = board.getHeight();
        this.width = board.getWidth();
        cells = new LinkedGrid(board.getWidth(),board.getHeight());
        
        
        for(int r = 0; r < board.getHeight(); r++ )   
        {
        for(int c = 0; c  < board.getWidth(); c++ )   
        {
            GUICell cell = new GUICell('c', r, c);
            cells.setElement(c, r, cell);
        }
        }
        
        determineNumbers();
     
        if(showGUI)
        {
        gui = new GUI(this, cells);
        }
    }
    
    
    /**
     * returns LinkedGrid cells
     * 
     * @return
     */
    public LinkedGrid<GUICell> getCells()
    {
       
    return cells;
    }
   
    /**
     * getting the height
     * 
     * @return
     */
    public int getHeight()
    {
    return height;
    }
   
    /**
     * getting the width
     * 
     * @return
     */
    public int getWidth()
    {
    return width;
    }

    /**
     * 
     * calculates number of bombs in surrounding cells
     */
    private void determineNumbers(){
    	
    	int boardH=board.getHeight(), boardW=board.getWidth();
        
      for(int r = 0; r < boardH ; r++ )   
        {
        for(int c = 0; c  < boardW; c++ )   
        { Character element = board.getElement(c, r);
           if (element == 'x')
            {
               GUICell cell = cells.getElement(c, r);
               cell.setNumber(-1);
            }
            else
            {
              int nearby = 0;
             
               if(c > 0)
              {
               if(board.getElement(c-1, r) == 'x')
               {nearby++;}
              }
              if(c < board.getWidth()-1)
              {
                if(board.getElement(c+1, r) == 'x')
               {nearby++;}
              }
              if(r > 0 )
              {
              if(board.getElement(c, r-1) == 'x')
               {nearby++;}
              }
              if(r < board.getHeight() - 1)
              {
               if(board.getElement(c, r+1) == 'x')
               {nearby++;}
              }
              if(r > 0 && c > 0)
              {
              if(board.getElement(c-1, r-1) == 'x')
               {nearby++;}
              }
              if(c < board.getWidth()-1 && r > 0 )
              {
               if(board.getElement(c+1, r-1) == 'x')
               {nearby++;}
              }
              if(r < board.getHeight()-1 && c > 0 )
              {
                  
               if(board.getElement(c-1, r+1) == 'x')
               {
                   nearby++;
                   
               }
                }
              if(c < board.getWidth()-1 && r < board.getHeight() - 1) 
              {
               if(board.getElement(c+1,r+1) == 'x')
               {
                   nearby++;
               }
              }  
              GUICell cell = cells.getElement(c, r);
              cell.setNumber(nearby);
            }
          }
       }
    }
    
    
    /**
     * 
     * this method is for when a cell is clicked
     * 
     * @param col
     * @param row
     * @return
     */
     public int processClick(int col, int row) {
        if(cells.getElement(col, row).getNumber() == -1)
        {
        cells.getElement(col, row).setBackground(Color.RED);
        cells.getElement(col, row).reveal();
        return -1;
        }
        if(cells.getElement(col, row).getNumber() == 0)
        {
        return recClear(col, row);
        }
        if(cells.getElement(col, row).getNumber() > 0)
        {
        if(cells.getElement(col, row).isRevealed())
        {
         return 0;
        }
        else
        {
        cells.getElement(col, row).setBackground(Color.white);
        cells.getElement(col, row).reveal();
        return 1;
        }
        }
        return 0;
    }
    
     
     /**
      * method to calculate number of cells from columns and rows
      * 
      * @param col
      * @param row
      * @return
      */
    private int  recClear(int col,int row)
    {
     if(col > width-1|| row > height-1|| row < 0 || col < 0)
     {
     return 0;
     }
     if(cells.getElement(col, row).isRevealed())
     {
     return 0;
     }
     if(cells.getElement(col, row).getNumber() == -1)
     {
     return 0;
     }
     else if(cells.getElement(col, row).getNumber() > 0)
     {
      cells.getElement(col, row).reveal();
      if(gui != null)
      {
          cells.getElement(col, row).setBackground(Color.white);
      }
     return 1;
     }
     else
     {
     cells.getElement(col, row).reveal();
      if(gui != null)
      {
          cells.getElement(col, row).setBackground(Color.white);
      }
      
     int result = 1;
     result += recClear(col - 1,row);
     result += recClear(col + 1,row);
     result += recClear(col,row + 1);
     result += recClear(col,row - 1);
     result += recClear(col - 1,row - 1);
     result += recClear(col + 1,row + 1);
     result += recClear(col - 1,row + 1);
     result += recClear(col + 1,row - 1);
     
     return result;
     }
     }
    
    
}
