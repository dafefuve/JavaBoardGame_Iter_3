package view;

/**
 * Created by Arnav on 4/22/14.
 */
public class ViewLocation
{
    int viewRow;
    int viewCol;

    public ViewLocation()
    {
        viewRow = 0;
        viewCol = 0;
    }

    public ViewLocation(int newRow, int newCol)
    {
        viewRow = newRow;
        viewCol = newCol;
    }

    public int getViewRow()
    {
        return this.viewRow;
    }

    public int getViewCol()
    {
        return this.viewCol;
    }

    public void setViewRow(int newRow)
    {
        this.viewRow = newRow;
    }

    public void setViewCol(int newCol)
    {
        this.viewCol = newCol;
    }
}
